package com.kverchi.diary.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Liudmyla Melnychuk on 14.1.2019.
 */
public class XSRFTokenFilter extends OncePerRequestFilter {
    private final static String XSRF_TOKEN_COOKIE = "XSRF-TOKEN";
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        // The CSRF token stored in session by Spring
        CsrfToken csrfToken = (CsrfToken)httpServletRequest.getAttribute(CsrfToken.class.getName());
        if(csrfToken != null) {
            //Look for a cookie in the request
            Cookie cookie = WebUtils.getCookie(httpServletRequest, XSRF_TOKEN_COOKIE);
            String token = csrfToken.getToken();
            //If cookie not found or token was changed, add the cookie with actual token to the server response
            if(cookie == null || token != null && !token.equals(cookie.getValue())) {
                cookie = new Cookie(XSRF_TOKEN_COOKIE, token);
                cookie.setMaxAge(-1);
                cookie.setPath("/");
                httpServletResponse.addCookie(cookie);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
