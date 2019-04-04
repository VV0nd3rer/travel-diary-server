package com.kverchi.diary.service;


import com.kverchi.diary.model.ServiceResponse;
import com.kverchi.diary.model.customexception.ServiceException;
import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.model.form.RegistrationForm;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
	ServiceResponse login(User requestUser);
	ServiceResponse logout(HttpServletRequest request,
						   HttpServletResponse response);
	List<User> findAll();
	ServiceResponse register(RegistrationForm form);
	void activateAccount(User user);
	boolean updatePassword(User user);
	boolean createAndSendResetPasswordToken(String email);
	User getResetPasswordToken(String token);
	User getUserFromSession();
	boolean isValuePresent(String key, Object value);
	void saveUserInfo(int userId, String info);
	//TODO is it correct place for these two methods? Or would it be better to use them in Sight layer?
	/*List getUserWishedSights(int userId);
	List getUserVisitedSights(int userId);*/
	boolean verifyPassword(String rawPass, String encodedPass);

	static String generateServerBaseUrl(HttpServletRequest request) {
		int port = request.getServerPort();
		StringBuilder baseUrl = new StringBuilder();
		baseUrl.append(request.getScheme())
				.append("://")
				.append(request.getServerName());
		if((request.getScheme().equals("http") && port != 80) || (request.getScheme().equals("https") && port != 443)) {
			baseUrl.append(":")
					.append(port);
		}
		return baseUrl.toString();
	}
}
