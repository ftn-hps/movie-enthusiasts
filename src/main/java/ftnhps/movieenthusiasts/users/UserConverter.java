package ftnhps.movieenthusiasts.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
	
	@Autowired
	UserService userService;
	
	public User fromChangeRoleDTO(ChangeRoleDTO dto) {
		
		if(dto.getId() == null)
			return null;
		
		if(dto.getUserType() == null)
			return null;
		
		dto.setUserType(dto.getUserType().toUpperCase());
		
		try {
			UserType.valueOf(dto.getUserType());
		}catch (IllegalArgumentException e) {
			return null;
		}
		
		User user = userService.findOne(dto.getId());
		if(user == null || user.getUserType().equals(UserType.SYSADMIN))
			return null;
		
		if(user.getUserType().equals(UserType.valueOf(dto.getUserType())))
			return user;
		
		if(UserType.VISITOR == UserType.valueOf(dto.getUserType()))
			user.setPasswordChanged(null);
		else
			user.setPasswordChanged(false);
		
		user.setUserType(UserType.valueOf(dto.getUserType()));
		return user;
	}
	
	public User fromEditDTO(EditDTO dto, User user) {
		User userDTO = dto.getUser();
		String oldPassword = dto.getOldPassword();
		
		if(userDTO == null)
			return null;
		
		if(oldPassword != null && !user.getPassword().equals(oldPassword))
			return null;
		
		if(oldPassword == null && userDTO.getPassword() != null)
			userDTO.setPassword(null);
		
		
		
		return userDTO;
	}
	
}
