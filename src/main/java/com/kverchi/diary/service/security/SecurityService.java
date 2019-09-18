package com.kverchi.diary.service.security;
import org.springframework.security.core.Authentication;

/**
 * Created by Liudmyla Melnychuk on 25.3.2019.
 */
public interface SecurityService {
    String generateSecurityToken();
}
