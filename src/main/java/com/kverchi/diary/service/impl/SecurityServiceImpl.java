package com.kverchi.diary.service.impl;

import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Liudmyla Melnychuk on 25.3.2019.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String generateSecurityToken() {
        String token = new String();
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            token = new Integer(sr.nextInt(Integer.MAX_VALUE)).toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error while generating security token.");
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public User getUserFromSession() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        User currentUser = new User();
        if (principal instanceof User) {
            currentUser = (User) principal;
            return currentUser;
        }
        return currentUser;
    }
}
