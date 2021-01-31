package com.skilldistillery.jets;

public abstract class Jet {
	
	private String model;
	private Double speedInMPH;
	private Integer rangeInMiles;
	private double price;
	private double maxDurationInHours;
	
	public Jet() {
		
	}
	
	public Jet(String model, double speed, int range, double price) {
		
		setModel(model);
		setSpeed(speed);
		setRangeInMiles(range);
		setPrice(price);
	}
	
	public void fly() {
		System.out.print("\nFlying " + toString() + "\n\t\t|^^^ can fly for ");
		System.out.printf("%.2f hours at this speed.\n", maxDurationInHours);
	};
	
	public double getSpeedInMach() {
		return speedInMPH / 767.269;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speedInMPH;
	}

	public void setSpeed(double speed) {
		
		if(speed < 0 ) speed = 0;
		this.speedInMPH = speed;
		
		if(rangeInMiles != null) setMaxDuration();
	}

	public int getRangeInMiles() {
		return rangeInMiles;
	}

	public void setRangeInMiles(int range) {
		
		if(range < 0 ) range = 0;
		this.rangeInMiles = range;
		
		if(speedInMPH != null) setMaxDuration();
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if(price < 0 ) price = 0;
		
		this.price = price;
	}
	
	
	private void setMaxDuration() {
		maxDurationInHours = rangeInMiles / speedInMPH;
	}
	
	public double getMaxDuration() {
		return maxDurationInHours;
	}

	@Override
	public String toString() {
		
		String priceToString = String.format("%.2f", price);
		
		StringBuilder builder = new StringBuilder();
		builder.append("Jet [class = " + getClass().getSimpleName() );
		builder.append(", model = " + model);
		builder.append(", speed = " + speedInMPH + "MPH");
		builder.append(", range = " + rangeInMiles + "miles");
		builder.append(", price = $" + priceToString);
		builder.append("]");
		return builder.toString();
	}
	
	
}
