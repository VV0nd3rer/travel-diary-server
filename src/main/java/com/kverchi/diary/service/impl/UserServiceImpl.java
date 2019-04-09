package com.kverchi.diary.service.impl;

import com.kverchi.diary.model.Email;
import com.kverchi.diary.model.ServiceResponse;
import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.model.enums.EmailType;
import com.kverchi.diary.model.enums.ServiceMessageResponse;
import com.kverchi.diary.model.form.RegistrationForm;
import com.kverchi.diary.repository.UserRepository;
import com.kverchi.diary.service.EmailMessagingProducerService;
import com.kverchi.diary.service.SecurityService;
import com.kverchi.diary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kverchi on 20.7.2018.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    SecurityService securityService;

    @Autowired
    EmailMessagingProducerService emailMessagingProducerService;

    @Override
    public ServiceResponse login(User requestUser) {
        Authentication authentication = null;
        UsernamePasswordAuthenticationToken token = new
                UsernamePasswordAuthenticationToken(requestUser.getUsername(), requestUser.getPassword());
        try {
            authentication = this.authenticationProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            logger.info("Logged in user: {}", user);
            user.setPassword(null);

            return new ServiceResponse(HttpStatus.OK, ServiceMessageResponse.OK, user);

        } catch (BadCredentialsException ex) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, ServiceMessageResponse.NO_USER_WITH_USERNAME);
        }
    }

    @Override
    public ServiceResponse logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return new ServiceResponse(HttpStatus.OK, ServiceMessageResponse.OK);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public ServiceResponse register(RegistrationForm form) {
        ServiceResponse response = new ServiceResponse();
        if(!form.getPassword().equals(form.getMatchingPassword())) {
            response.setResponseMessage(ServiceMessageResponse.NEW_PASSWORD_MISMATCHED);
            response.setResponseCode(HttpStatus.BAD_REQUEST);
            return response;
        }

        User user = form.toUser(bCryptPasswordEncoder);
        if(userRepository.findByUsername(user.getUsername()) != null) {
            response.setResponseMessage(ServiceMessageResponse.USER_USERNAME_ALREADY_EXIST);
            response.setResponseCode(HttpStatus.BAD_REQUEST);
            return response;
        }
        if(userRepository.findByEmail(user.getEmail()) != null) {
            response.setResponseMessage(ServiceMessageResponse.USER_EMAIL_ALREADY_EXIST);
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
                new Email(EmailType.REGISTRATION_EMAIL,
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
    public User getUserFromSession() {
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
