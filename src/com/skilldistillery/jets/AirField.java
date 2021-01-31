package com.skilldistillery.jets;

import java.io.*;
import java.util.*;

public class AirField {
	private List<Jet> jets;
	private static String jetsDatabaseFile = "jets.txt";

	public AirField() {
		loadJetsFromDatabase();
	}

	private void loadJetsFromDatabase() {
		jets = getJetsFromFile(jetsDatabaseFile);
	}

	private List<Jet> getJetsFromFile(String filename) {

		List<Jet> jetsFromFile = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(jetsDatabaseFile))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] parameters = line.split(", ");
				
				Jet toAdd = createJetOfType(parameters[0], parameters[1], Double.parseDouble(parameters[2]),
						Integer.parseInt(parameters[3]), Long.parseLong(parameters[4]));

				if(toAdd != null) jetsFromFile.add(toAdd);

			}
		} catch (FileNotFoundException e) {

			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {

			System.out.println(e);
			e.printStackTrace();
		}

		return jetsFromFile;
	}

	private Jet createJetOfType(String typeOfJet, String model, double speed, int range, double price) {

		Jet j;
		

		switch (typeOfJet) {

		case "Airbus Cargo":
			j = new AirbusCargo(model, speed, range, price);
			return j;
		case "Airbus Passenger":
			j = new AirbusPassenger(model, speed, range, price);
			return j;
		case "Boeing Passenger":
			j = new BoeingPassenger(model, speed, range, price);
			return j;
		case "JetImpl":
			j = new JetImpl(model, speed, range, price);
			return j;
		}

		return null;
	}

	public void listFleet() {

		if (jets.size() == 0) {
			System.out.println("\nNo jets to display.\n");
			return;
		}

		System.out.println("\nThis is our fleet:\n");
		

		for (int i = 0; i < jets.size(); i++) {
			System.out.println(jets.get(i));
		}
	}

	public void launchEntireFleet() {

		if (jets.size() == 0) {
			System.out.println("There are currently no jets in the airfield.");
			return;
		}

		for (int i = 0; i < jets.size(); i++) {

			jets.get(i).fly();
		}
	}

	public void displayFastestJet() {

		if (jets.size() == 0) {
			System.out.println("There are currently no jets in the airfield.");
			return;
		}

		Jet fastest = jets.get(0);

		for (int i = 1; i < jets.size(); i++) {

			if (jets.get(i).getSpeed() > fastest.getSpeed()) {
				fastest = jets.get(i);
			}
		}

		System.out.println("\n" + fastest + "\n\t\t|^^^ is our fastest jet.\n");
	}

	public void displayJetWithLongestRange() {

		if (jets.size() == 0) {
			System.out.println("There are currently no jets in the airfield.");
			return;
		}

		Jet longRange = jets.get(0);

		for (int i = 1; i < jets.size(); i++) {

			if (jets.get(i).getRangeInMiles() > longRange.getRangeInMiles()) {
				longRange = jets.get(i);
			}
		}

		System.out.println("\n" + longRange + "\n\t\t|^^^ is our jet with the longest range.");
	}

	public void loadAllCargoJets() {

		if (jets.size() == 0) {
			System.out.println("\nThere are currently no jets in the airfield.\n");
			return;
		}
		
		System.out.println("\nPreparing to load all cargo jets - \n");
		
		boolean atLeastOneCargo = false;
		
		for (int i = 0; i < jets.size(); i++) {

			if (jets.get(i) instanceof CargoAircraft) {
				((CargoAircraft) jets.get(i)).loadCargo();
				atLeastOneCargo = true;
			}
		}
		
		if(!atLeastOneCargo) System.out.println("There are no cargo carriers to load.");
		
		System.out.println();
	}

	public void boardPassengersOnAllCommercialAirliners() {
		
		if (jets.size() == 0) {
			System.out.println("\nThere are currently no jets in the airfield.\n");
			return;
		}
		
		System.out.println("\nPreparing to board all passenger planes - \n");
		
		boolean atLeastOneCommercial = false;
		
		for (int i = 0; i < jets.size(); i++) {

			if (jets.get(i) instanceof CommercialAircraft) {
				((CommercialAircraft) jets.get(i)).boardPassengers();
				atLeastOneCommercial = true;
			}
		}
		
		if(!atLeastOneCommercial) System.out.println("There are no commercial aircraft to board.");
		
		System.out.println();
	}

	public void addJet(String typeOfJet, String model, double speed, int range, double price) {
		
		if(typeOfJet.equals("cancel")) return;
		
		Jet j = createJetOfType(typeOfJet, model, speed, range, price);
		jets.add(j);
		
		System.out.println(j + "\n\n\t\tHas been successfully added to the fleet.");
	}
	
	/*
	 * Outputs a numbered list of jets to the user. 
	 * @return int - post printing the list of jets,
	 * 				 	jetMenu() returns the next number of the list so that additional list items may be added by the caller.
	 */
	public int jetMenu() {
		
		int index = 0;
		
		for (; index < jets.size(); index++) {
			System.out.println(" " + (index + 1) + " : " + jets.get(index).toString() );
		}
		
		return index + 1;
	}
	
	/*
	 * @param String indexToRemovePlusOne - This should be either index to remove plus one; or jets.size() + 2 (the cancel option)
	 * 									If an exception is thrown, "That is not a recognized option." will be output to the screen.
	 * 
	 * @return boolean - returns true if either a jet is successfully removed, or the 'remove jet process' is successfully canceled.
	 * 						returns false if any exception is thrown.
	 * 
	 * 					Designed to be used directly and in tandem with [JetsApplication]'s removeJet(),
	 * 					and indirectly used with jetMenu().
	 */
	public boolean removeJet(String indexToRemovePlusOne) {
		
		try {
			int a = Integer.parseInt(indexToRemovePlusOne);
			
			if(a == jets.size() + 1) {
				System.out.println("\nLeaving jet removal process.\n");
				return true;
			}
			
			Jet toRemove = jets.get(a - 1);
			System.out.println("\nRemoving " + toRemove + " . . . \n");
			jets.remove(a - 1);
			System.out.println("Removal successful.\n");
			return true;
			
		} catch(Exception exc) {
			System.out.println("That is not a recognized option.");
			return false;
		}
		
	}

	
}
