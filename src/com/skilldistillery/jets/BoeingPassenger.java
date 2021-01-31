package com.skilldistillery.jets;

public class BoeingPassenger extends Jet implements CommercialAircraft{
	
	public BoeingPassenger(String model, double speed, int range, double price) {
		super(model, speed, range, price);
		
	}

	@Override
	public void boardPassengers() {
		
		System.out.println("Passengers boarding on " + getClass().getSimpleName() + " " + getModel() + " . . . on boarding successful." );
	}
}

