package ftnhps.movieenthusiasts.users.scale;

public class ScaleDTO {
	
	private Integer silver;
	
	private Integer gold;

	public ScaleDTO() {}

	public ScaleDTO(Integer silver, Integer gold) {
		this.silver = silver;
		this.gold = gold;
	}

	public Integer getSilver() {
		return silver;
	}

	public void setSilver(Integer silver) {
		this.silver = silver;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}
	
}
