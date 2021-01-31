package com.skilldistillery.jets;

public class AirbusPassenger extends Jet implements CommercialAircraft{
	

	public AirbusPassenger(String model, double speed, int range, double price) {
		super(model, speed, range, price);
		
	}

	@Override
	public void boardPassengers() {
		
		System.out.println("Passengers boarding on " + getClass().getSimpleName() + " " + getModel() + " . . . on boarding successful." );
	}

}
