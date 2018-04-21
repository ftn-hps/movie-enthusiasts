package ftnhps.movieenthusiasts.users;


public class ChangeRoleDTO {
	
	private Long id;
	
	private String userType;
	
	public ChangeRoleDTO() {}

	public ChangeRoleDTO(Long id, String userType) {
		this.id = id;
		this.userType = userType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
