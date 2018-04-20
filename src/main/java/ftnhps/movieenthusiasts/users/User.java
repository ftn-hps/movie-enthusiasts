package ftnhps.movieenthusiasts.users;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class User {

	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@Version
	private Long version;

	@Email
	@NotBlank
	private String email;

	@Size(min = 6)
	@NotEmpty
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@JsonProperty(access = Access.READ_ONLY)
	private boolean isEmailConfirmed = false;
	@JsonProperty(access = Access.READ_ONLY)
	private String token = UUID.randomUUID().toString();

	@Pattern(regexp = "(?U)\\p{Alpha}*")
	private String name;

	@Pattern(regexp = "(?U)\\p{Alpha}*")
	private String lastName;

	@Pattern(regexp = "(?U)[\\p{Alpha}\\h]*")
	private String city;

	@Pattern(regexp = "(\\d{9,10})|(^$)")
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private UserType userType = UserType.VISITOR;
	
	@Min(0)
	@JsonProperty(access = Access.READ_ONLY)
	private int points = 0;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private MedalType medal = MedalType.BRONZE;

	public User() {}

	public User(String email,
			String password,
			String name,
			String lastName,
			String city,
			String phoneNumber) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.city = city;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEmailConfirmed() {
		return isEmailConfirmed;
	}

	public void setEmailConfirmed(boolean isEmailConfirmed) {
		this.isEmailConfirmed = isEmailConfirmed;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void incrementPoints(int increment) {
		this.points += increment;
	}

	public MedalType getMedal() {
		return medal;
	}

	public void setMedal(MedalType medal) {
		this.medal = medal;
	}

}
