package com.pension.authorization.model;

import javax.validation.constraints.NotEmpty;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRequest(@NotEmpty(message = "{username.blank}") String username,
			@NotEmpty(message = "{password.blank}") String password) {
		super();
		this.username = username;
		this.password = password;
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
	@NotEmpty(message = "{username.blank}")
	private String username;
	@NotEmpty(message = "{password.blank}")
	private String password;
}
