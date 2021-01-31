package com.skilldistillery.jets;

public class AirbusCargo extends Jet implements CargoAircraft{

	public AirbusCargo(String model, double speed, int range, double price) {
		super(model, speed, range, price);
	}

	@Override
	public void loadCargo() {

		System.out.println(getClass().getSimpleName() + " " + getModel() + " is loading cargo . . . loading successful.");
	}


}
