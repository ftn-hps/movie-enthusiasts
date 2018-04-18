package ftnhps.movieenthusiasts.fanzone.propused;

import java.time.LocalDateTime;

public class PropUsedDTO {
	
	private String name;
	
	private String description;
	
	private LocalDateTime date;
	
	public PropUsedDTO() {}

	public PropUsedDTO(String name, String description, LocalDateTime date) {
		this.name = name;
		this.description = description;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
}
