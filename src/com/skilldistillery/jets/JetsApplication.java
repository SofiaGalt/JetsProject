package com.skilldistillery.jets;

import java.util.Scanner;

public class JetsApplication {

	private Scanner in = new Scanner(System.in);
	private AirField airField = new AirField();

	public JetsApplication() {
	}

	public static void main(String[] args) {

		JetsApplication app = new JetsApplication();

		app.run();
	}

	private void run() {

		welcome();

		boolean keepRunning = true;

		while (keepRunning) {
			showMenu();
			keepRunning = executeMenuOption(userInputPrompt());
		}

	}

	private void welcome() {
		System.out.println("__________________________________________________________________________\n");
		System.out.println("Welcome to the Singapore Changi Airport!");
		System.out.println("We're very happy to have you coming on as our chief operating officer.");
		System.out.println("__________________________________________________________________________\n");
		pressAnyKeyToContinue();
	}

	private void showMenu() {
		System.out.println("_________________________________________________\n\n"
				+ "         	 -{ Options }-                  \n"
				+ "_________________________________________________\n\n" + "1 : List fleet\n" + "2 : Fly all jets\n"
				+ "3 : View fastest jet\n" + "4 : View jet with longest range\n" + "5 : Load all Cargo Jets\n"
				+ "6 : Board passengers on all commercial airliners\n" + "7 : Add a jet to the fleet\n"
				+ "8 : Remove a jet from the fleet\n" + "9 : Quit\n"
				+ "_________________________________________________\n\n"
				+ "    Enter the number of your selection.            \n"
				+ "_________________________________________________\n");
	}

	private String userInputPrompt() {
		System.out.print("\n : ");
		return in.nextLine();
	}
	
	private void pressAnyKeyToContinue() {
		System.out.println("__________________________________________________________________________\n");
		System.out.println("\n\t - Enter any key to continue - ");
		System.out.print("\n > ");
		in.nextLine();
	}

	/*
	 * @return boolean - returns false if the the String "9" is passed as a
	 * parameter. otherwise returns true. (executeMenuOption returns a boolean
	 * because it was designed to be used in tandem with the keepRunning boolean in
	 * run().)
	 */
	private boolean executeMenuOption(String choice) {

		switch (choice) {

		case "1":
			airField.listFleet();
			pressAnyKeyToContinue();
			return true;
		case "2":
			airField.launchEntireFleet();
			pressAnyKeyToContinue();
			return true;
		case "3":
			airField.displayFastestJet();
			pressAnyKeyToContinue();
			return true;
		case "4":
			airField.displayJetWithLongestRange();
			pressAnyKeyToContinue();
			return true;
		case "5":
			airField.loadAllCargoJets();
			pressAnyKeyToContinue();
			return true;
		case "6":
			airField.boardPassengersOnAllCommercialAirliners();
			pressAnyKeyToContinue();
			return true;
		case "7":
			addJet();
			pressAnyKeyToContinue();
			return true;
		case "8":
			removeJet();
			pressAnyKeyToContinue();
			return true;
		case "9":
			terminatingProgramMessage();
			return false;
		default:
			System.out.println("\nThat is not a recognized option.\n");
			pressAnyKeyToContinue();
			return true;
		}
	}
	
	private void addJet() {
		
		String jetType = jetTypeToCreatePrompt();
		
		if(jetType.equals("cancel")) {
			System.out.println("\nLeaving the add jet process.\n");
			return;
		}
		airField.addJet(jetType, jetModelPrompt(), jetSpeedPrompt(), jetRangePrompt(), jetPricePrompt() );
	}
	
	private void removeJet() {
		
		boolean userInputValid = false;
		while(!userInputValid) {
			System.out.println("\nWhich jet would you like to remove?");
			System.out.println("______________________________________________________________________________\n\n");
			int nextListNumber = airField.jetMenu();
			System.out.println(" "+ nextListNumber + " : Cancel remove jet process" );
			System.out.println("______________________________________________________________________________\n\n");
			
			userInputValid = airField.removeJet(userInputPrompt());
		}
	}

	private String jetTypeToCreatePrompt() {

		while (true) {
			System.out.println("\nWhich kind of jet would you like to add to the fleet?");
			System.out.println("_________________________________________________\n\n" + "1 : Airbus Cargo\n"
					+ "2 : Airbus Passenger\n" + "3 : Boeing Passenger\n" + "4 : JetImpl (the base jet model)\n"
					+ "5 : cancel add jet process.\n"
					+ "_________________________________________________\n");

			String a = userInputPrompt();

			switch (a) {

			case "1":
				return "Airbus Cargo";
			case "2":
				return "Airbus Passenger";
			case "3":
				return "Boeing Passenger";
			case "4":
				return "JetImpl";
			case "5":
				return "cancel";
			default:
				System.out.println("That is not a recognized option.\n");
			}
		}
	}

	private String jetModelPrompt() {

		System.out.println("\nWhich model would you like?");

		return userInputPrompt();
	}

	private double jetSpeedPrompt() {

		while (true) {
			System.out.println("\nHow fast should this jet be in MPH?");

			String a = userInputPrompt();

			try {
				double r = Double.parseDouble(a);
				return r;
			} catch (Exception exc) {
				System.out.println(
						"\nWe didn't get that.\nPlease enter the numerical speed without the units.  We will assume MPH as the units.");
			}
		}
	}

	private int jetRangePrompt() {

		while (true) {
			System.out.println("\nWhat should this jet's range be in miles?");

			String a = userInputPrompt();

			try {
				int range = Integer.parseInt(a);
				return range;
			} catch (Exception exc) {
				System.out.println(
						"\nWe didn't get that.\nPlease enter the numerical range without the units and without a decimal.  We will assume the distance you give to be in miles.");
			}
		}
	}

	private double jetPricePrompt() {

		while (true) {
			System.out.println("\nHow expensive is this jet?");

			String a = userInputPrompt();

			try {
				Double range = Double.parseDouble(a);
				return range;
			} catch (Exception exc) {
				System.out.println(
						"\nWe didn't get that.\nPlease enter the numerical price without the units.  We will assume the price to be in USD.");
			}
		}
	}
	
	private void terminatingProgramMessage() {
		System.out.println("\n__________________________________________________________________________\n\n");
		System.out.println("Thank you!  Have a relaxing evening."
				+ "");
		System.out.println("__________________________________________________________________________\n\n");
		
	}

}
