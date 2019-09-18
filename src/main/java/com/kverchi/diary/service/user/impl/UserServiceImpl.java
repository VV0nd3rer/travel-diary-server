package com.kverchi.diary.service.user.impl;

import com.kverchi.diary.model.Email;
import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.repository.predicates.UserPredicates;
import com.kverchi.diary.service.email.impl.EmailTemplate;
import com.kverchi.diary.model.form.RegistrationForm;
import com.kverchi.diary.repository.UserRepository;
import com.kverchi.diary.service.email.EmailMessagingProducerService;
import com.kverchi.diary.service.security.SecurityService;
import com.kverchi.diary.service.user.UserService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Kverchi on 20.7.2018.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    SecurityService securityService;

    @Autowired
    EmailMessagingProducerService emailMessagingProducerService;

    @Override
    public ServiceResponse login(User requestUser) {
        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
                UsernamePasswordAuthenticationToken(requestUser.getUsername(), requestUser.getPassword());
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationTokenRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            /*HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);*/

            User user = (User) authentication.getPrincipal();
            logger.info("Logged in user: {}", user);
            return new ServiceResponse(HttpStatus.OK, MsgServiceResponse.OK, user);

        } catch (BadCredentialsException ex) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, MsgServiceResponse.NO_USER_WITH_USERNAME);
        }
    }

    @Override
    public ServiceResponse logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(
                    httpServletRequest,
                    httpServletResponse,
                    authentication);
        }
        return new ServiceResponse(HttpStatus.OK, MsgServiceResponse.OK);
    }

    @Override
    public Page<User> getAllUsers() {
        Pageable pageable = Pageable.unpaged();
        Page<User> page = userRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<User> getAllUsers(String searchLikeAttr) {
        Pageable pageable = Pageable.unpaged();
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(UserPredicates.searchLikeUsername(searchLikeAttr));
        Page<User> page = userRepository.findAll(builder, pageable);
        return page;
    }

    @Override
    public Page<User> getUsers(Predicate predicate, int currentPage, int pageSize, String sorting) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<User> page = userRepository.findAll(predicate, pageable);
        return page;
    }

    @Override
    public Page<User> getUsers(Predicate predicate, String searchLikeAttr, int currentPage, int pageSize, String sorting) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(UserPredicates.searchLikeUsername(searchLikeAttr)).and(predicate);
        Page<User> page = userRepository.findAll(builder, pageable);
        return page;
    }


    @Override
    public ServiceResponse register(RegistrationForm form) {
        ServiceResponse response = new ServiceResponse();
        if (!form.getPassword().equals(form.getMatchingPassword())) {
            response.setResponseMessage(MsgServiceResponse.NEW_PASSWORD_MISMATCHED);
            response.setResponseCode(HttpStatus.BAD_REQUEST);
            return response;
        }

        User user = form.toUser(bCryptPasswordEncoder);
        if (userRepository.findByUsername(user.getUsername()) != null) {
            response.setResponseMessage(MsgServiceResponse.USER_USERNAME_ALREADY_EXIST);
            response.setResponseCode(HttpStatus.BAD_REQUEST);
            return response;
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            response.setResponseMessage(MsgServiceResponse.USER_EMAIL_ALREADY_EXIST);
            response.setResponseCode(HttpStatus.BAD_REQUEST);
            return response;
        }
        String securityToken = securityService.generateSecurityToken();

        String baseUrl = UserService.generateServerBaseUrl(httpServletRequest);
        String confirmLink = baseUrl + "/user/confirm/" + securityToken;

        List<String> recipientsAddress = Arrays.asList(user.getEmail());
        Map<String, Object> textVariables = new HashMap<>();
        textVariables.put("confirmEmailLink", confirmLink);
        Email registrationEmail =
                new Email(EmailTemplate.REGISTRATION_EMAIL,
                        recipientsAddress,
                        textVariables);
        emailMessagingProducerService.sendEmail(registrationEmail);

        try {
            userRepository.save(user);
            response.setSuccessResponse();
        } catch (UnexpectedRollbackException e) {
            e.printStackTrace();
            response.setInternalServerErrorResponse();
        } catch (Exception e) {
            response.setInternalServerErrorResponse();
        }
        return response;
    }


    @Override
    public void activateAccount(User user) {

    }

    @Override
    public boolean updatePassword(User user) {
        return false;
    }

    @Override
    public boolean createAndSendResetPasswordToken(String email) {
        return false;
    }

    @Override
    public User getResetPasswordToken(String token) {
        return null;
    }

    @Override
    public boolean isValuePresent(String key, Object value) {
        return false;
    }

    @Override
    public void saveUserInfo(int userId, String info) {

    }

    @Override
    public boolean verifyPassword(String rawPass, String encodedPass) {
        return false;
    }

}
