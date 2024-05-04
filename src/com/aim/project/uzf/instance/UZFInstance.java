package com.aim.project.uzf.instance;

import java.util.ArrayList;
import java.util.Random;

import com.aim.project.uzf.UZFObjectiveFunction;
import com.aim.project.uzf.interfaces.ObjectiveFunctionInterface;
import com.aim.project.uzf.interfaces.SolutionRepresentationInterface;
import com.aim.project.uzf.interfaces.UZFInstanceInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;
import com.aim.project.uzf.solution.SolutionRepresentation;
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
		this.oObjectiveFunction = new UZFObjectiveFunction(UZFInstance.this);
	}

	@Override
	public UZFSolution createSolution(InitialisationMode mode) {
		if (mode == InitialisationMode.RANDOM) {
			int[] solutionRepresentation = new int[numberOfLocations];
			SolutionRepresentationInterface sol = new SolutionRepresentation(solutionRepresentation, numberOfLocations);
			for (int i = 0; i < numberOfLocations; i++) {
				solutionRepresentation[i] = i;
			}
			for (int i = 0; i < numberOfLocations; i++) {
				int j = random.nextInt(numberOfLocations);
				int temp = solutionRepresentation[i];
				solutionRepresentation[i] = solutionRepresentation[j];
				solutionRepresentation[j] = temp;
			}
			return new UZFSolution(sol, oObjectiveFunction.getObjectiveFunctionValue(sol));

		} else if (mode == InitialisationMode.CONSTRUCTIVE) {
			int[] solutionRepresentation = new int[numberOfLocations];
			SolutionRepresentationInterface sol = new SolutionRepresentation(solutionRepresentation, numberOfLocations);
			for (int i = 0; i < numberOfLocations; i++) {
				solutionRepresentation[i] = i;
			}
			for (int i = 0; i < numberOfLocations - 1; i++) {
				int minIndex = i + 1;
				double minDistance = oObjectiveFunction.getCost(solutionRepresentation[i],
						solutionRepresentation[minIndex]);
				for (int j = i + 2; j < numberOfLocations; j++) {
					double distance = oObjectiveFunction.getCost(solutionRepresentation[i], solutionRepresentation[j]);
					if (distance < minDistance) {
						minDistance = distance;
						minIndex = j;
					}
				}

				int temp = solutionRepresentation[i + 1];
				solutionRepresentation[i + 1] = solutionRepresentation[minIndex];
				solutionRepresentation[minIndex] = temp;
			}
			return new UZFSolution(sol, oObjectiveFunction.getObjectiveFunctionValue(sol));
		} else {
			throw new IllegalArgumentException("Invalid initialisation mode: " + mode);
		}
	}

	@Override
	public ObjectiveFunctionInterface getUZFObjectiveFunction() {
		return this.oObjectiveFunction;
	}

	@Override
	public int getNumberOfLocations() {
		return this.numberOfLocations;
	}

	@Override
	public Location getLocationForEnclosure(int iEnclosureId) {
		if (iEnclosureId >= 0 && iEnclosureId < aoLocations.length) {
			return aoLocations[iEnclosureId];
		} else {
			throw new IndexOutOfBoundsException("Invalid enclosure ID: " + iEnclosureId);
		}
	}

	@Override
	public Location getLocationOfFoodPreparationArea() {
		return this.foodPreparationLocation;
	}

	@Override
	public ArrayList<Location> getSolutionAsListOfLocations(UAVSolutionInterface oSolution) {
		int[] solutionRepresentation = oSolution.getSolutionRepresentation().getSolutionRepresentation();
		ArrayList<Location> locations = new ArrayList<>(solutionRepresentation.length);
		for (int i = 0; i < solutionRepresentation.length; i++) {
			locations.add(aoLocations[solutionRepresentation[i]]);
		}
		return locations;
	}

}
