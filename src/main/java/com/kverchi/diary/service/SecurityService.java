package com.kverchi.diary.service;

import com.kverchi.diary.model.entity.User;

/**
 * Created by Liudmyla Melnychuk on 25.3.2019.
 */
public interface SecurityService {
    String generateSecurityToken();
    Object getUserFromSession();
}
