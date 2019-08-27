package com.kverchi.diary.service.security.impl;

import com.kverchi.diary.service.security.SecurityService;
import com.kverchi.diary.service.security.impl.SecurityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 25.3.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    SecurityService securityService;

    //@Repeat(30)
    @Test
    public void testGenerateSecurityToken() throws Exception {
        String token = securityService.generateSecurityToken();
        logger.info("Token: {}", token);
        assertNotEquals(token, "");
    }

    @Test
    @WithMockUser
    public void testGetUserFromSession() throws Exception {
        UserDetails user = (UserDetails) securityService.getUserFromSession();
        assertEquals(user.getUsername(), "user");
    }
}