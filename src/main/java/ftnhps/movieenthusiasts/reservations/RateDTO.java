package ftnhps.movieenthusiasts.reservations;

public class RateDTO {

	int rateAmbient;
	
	int rateProjection;

	public RateDTO() {}
	public RateDTO(int rateAmbient, int rateProjection) {
		super();
		this.rateAmbient = rateAmbient;
		this.rateProjection = rateProjection;
	}

	public int getRateAmbient() {
		return rateAmbient;
	}

	public void setRateAmbient(int rateAmbient) {
		this.rateAmbient = rateAmbient;
	}

	public int getRateProjection() {
		return rateProjection;
	}

	public void setRateProjection(int rateProjection) {
		this.rateProjection = rateProjection;
	}
	
	
}
