package ftnhps.movieenthusiasts.users;

public class EditDTO {
	
	private User user;
	
	private String oldPassword;

	public EditDTO() {}

	public EditDTO(User user, String oldPassword) {
		this.user = user;
		this.oldPassword = oldPassword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
