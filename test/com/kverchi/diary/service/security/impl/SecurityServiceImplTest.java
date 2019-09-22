package com.kverchi.diary.service.security.impl;

import com.kverchi.diary.service.security.SecurityService;
import com.kverchi.diary.service.security.impl.SecurityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 25.3.2019.
 */
@RunWith(SpringRunner.class)
public class SecurityServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @TestConfiguration
    static class SecurityServiceImplTestContextConfig {
        @Bean
        public SecurityService securityService() {
            return new SecurityServiceImpl();
        }
    }

    @Autowired
    SecurityService securityService;

    //@Repeat(30)
    @Test
    public void testGenerateSecurityToken() {
        String token = securityService.generateSecurityToken();
        logger.info("Token: {}", token);
        assertNotEquals(token, "");
    }
    @Test
    public void testSanitizeHtmlText() {
        final String htmlText1 = "<p>The greatest story. Cyberpank is alive <img src=\"http://cloud.tinymce.com/stable/plugins/emoticons/img/smiley-wink.gif\" alt=\"wink\" /></p>";
        final String htmlText2 = "<p><iframe src=\"//www.youtube.com/embed/jIM4-HLtUM0\" width=\"560\" height=\"314\" allowfullscreen=\"allowfullscreen\"></iframe></p>\n" +
                "<p>It&rsquo;s no secret that&nbsp;<em>King Arthur: Legend of the Sword</em>&nbsp;is going to include several big fantasy and magic elements, and previous videos have shown huge monsters as well as magic figures. This is a great look at the power of the sword itself, though, and goes a long way to show how Hunnam&rsquo;s Arthur and his small band of reprobates is going to be able to battle Vortigern and his vast army. Both promo and banner emphasize the core conflict between these two kings, as well, and now it looks like there is going to be a lot of magic at the center of that final throwdown. We&rsquo;ll be interested to see what else WB has in store for us with this very risky fantasy epic.</p>\n" +
                "<p><img src=\"http://static.srcdn.com/wp-content/uploads/king-arthur-legend-sword-charlie-hunnam.jpg\" alt=\"\" width=\"558\" height=\"279\" /></p>";
        String sanitizedHtml1 = securityService.sanitizeHtmlText(htmlText1);
        String sanitizedHtml2 = securityService.sanitizeHtmlText(htmlText2);
        logger.info("Input text1: " + htmlText1);
        logger.info("Sanitized text1: " + sanitizedHtml1);
        logger.info("Input text2: " + htmlText2);
        logger.info("Sanitized text1: " + sanitizedHtml2);
        assertEquals(htmlText1, sanitizedHtml1);

    }
}