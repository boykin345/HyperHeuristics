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
		int[] solutionArray = new int[numberOfLocations];
		for (int i = 0; i < numberOfLocations; i++) {
			solutionArray[i] = i;
		}
		if (mode == InitialisationMode.RANDOM) {
			for (int i = 0; i < numberOfLocations; i++) {
				int swapIndex = random.nextInt(solutionArray.length);
				int temp = solutionArray[i];
				solutionArray[i] = solutionArray[swapIndex];
				solutionArray[swapIndex] = temp;
			}
		} else if (mode == InitialisationMode.CONSTRUCTIVE) {
			int firstIndex = random.nextInt(numberOfLocations - 1);
			solutionArray[0] = firstIndex;
			solutionArray[firstIndex] = 0;
			for (int i = 1; i < numberOfLocations; i++) {
				double minDistance = oObjectiveFunction.getCost(solutionArray[i - 1], solutionArray[i]);
				int minIndex = i;
				for (int j = i + 1; j < numberOfLocations; j++) {
					double distance = oObjectiveFunction.getCost(solutionArray[i - 1], solutionArray[j]);
					if (distance < minDistance) {
						minDistance = distance;
						minIndex = j;
					}
				}
				int temp = solutionArray[i];
				solutionArray[i] = solutionArray[minIndex];
				solutionArray[minIndex] = temp;
			}
		}
		SolutionRepresentationInterface solutionRepresentation = new SolutionRepresentation(solutionArray);
		return new UZFSolution(solutionRepresentation,
				oObjectiveFunction.getObjectiveFunctionValue(solutionRepresentation));
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
