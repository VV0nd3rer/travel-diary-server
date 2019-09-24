package com.kverchi.diary.model.form;

import com.kverchi.diary.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationForm {
	private String username;
	private String password;
	private String confirmPassword;
	private String email;

	public RegistrationForm() {
	}

	public RegistrationForm(String username, String password, String matchingPassword, String email) {
		this.username = username;
		this.password = password;
		this.confirmPassword = matchingPassword;
		this.email = email;
	}

	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(username, passwordEncoder.encode(password), email);
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
