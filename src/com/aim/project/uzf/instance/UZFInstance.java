package com.aim.project.uzf.instance;


import java.util.ArrayList;
import java.util.Random;

import com.aim.project.uzf.interfaces.ObjectiveFunctionInterface;
import com.aim.project.uzf.interfaces.UZFInstanceInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;
import com.aim.project.uzf.solution.UZFSolution;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */

public class UZFInstance implements UZFInstanceInterface {

	private final Random random;
	private Location[] aoLocations;
	private Location foodPreparationLocation;
	private int numberOfLocations;
	private ObjectiveFunctionInterface oObjectiveFunction;
	
	public UZFInstance(int numberOfLocations, Location[] aoLocations, Location foodPreparationLocation, Random random) {

		this.random = random;
		this.aoLocations = aoLocations;
		this.foodPreparationLocation = foodPreparationLocation;
		this.numberOfLocations = numberOfLocations;
	}

	@Override
	public UZFSolution createSolution(InitialisationMode mode) {
		
		// TODO
		return null;
		
	}

	@Override
	public ObjectiveFunctionInterface getUZFObjectiveFunction() {

		// TODO
		return null;
	}

	@Override
	public int getNumberOfLocations() {

		// TODO
		return -1;
	}

	@Override
	public Location getLocationForEnclosure(int iEnclosureId) {

		// TODO
		return null;
	}

	@Override
	public Location getLocationOfFoodPreparationArea() {

		// TODO
		return null;
	}

	@Override
	public ArrayList<Location> getSolutionAsListOfLocations(UAVSolutionInterface oSolution) {

		// TODO
		return null;
	}

}
