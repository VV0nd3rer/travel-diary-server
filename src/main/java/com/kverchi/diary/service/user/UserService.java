package com.kverchi.diary.service.user;


import com.kverchi.diary.service.user.impl.ServiceResponse;
import com.kverchi.diary.model.entity.User;
import com.kverchi.diary.model.form.RegistrationForm;
import com.kverchi.diary.service.user.impl.UserSearchingCriteria;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public interface UserService {
	ServiceResponse login(User requestUser);
	ServiceResponse logout();
	Page<User> getAllUsers();
	Page<User> getAllUsers(String searchLikeAttr);
	Page<User> getUsers(Predicate predicate, int currentPage, int pageSize, String sorting);
	Page<User> getUsers(Predicate predicate, String searchLikeAttr,
						int currentPage, int pageSize, String sorting);
	ServiceResponse register(RegistrationForm form);
	void activateAccount(User user);
	boolean updatePassword(User user);
	boolean createAndSendResetPasswordToken(String email);
	User getResetPasswordToken(String token);
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
