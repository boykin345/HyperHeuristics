package com.aim.project.uzf;

import com.aim.project.uzf.instance.Location;
import com.aim.project.uzf.interfaces.ObjectiveFunctionInterface;
import com.aim.project.uzf.interfaces.UZFInstanceInterface;
import com.aim.project.uzf.interfaces.SolutionRepresentationInterface;

public class UZFObjectiveFunction implements ObjectiveFunctionInterface {
	private final UZFInstanceInterface oInstance;

	public UZFObjectiveFunction(UZFInstanceInterface oInstance) {
		this.oInstance = oInstance;
	}

	@Override
	public int getObjectiveFunctionValue(SolutionRepresentationInterface oSolution) {
		int[] locations = oSolution.getSolutionRepresentation();
		int totalDistance = 0;

		totalDistance += getCostBetweenFoodPreparationAreaAnd(locations[0]);

		for (int i = 1; i < locations.length; i++) {
			totalDistance += getCost(locations[i - 1], locations[i]);
		}

		totalDistance += getCostBetweenFoodPreparationAreaAnd(locations[locations.length - 1]);

		return totalDistance;
	}

	public double getCost(Location oLocationA, Location oLocationB) {
		int a1x = oLocationA.x();
		int a1y = oLocationA.y();
		int a2x = oLocationB.x();
		int a2y = oLocationB.y();
		return Math.sqrt(Math.pow(a1x - a2x, 2) + Math.pow(a1y - a2y, 2));
	}

	@Override
	public int getCost(int iLocationA, int iLocationB) {
		Location locationA = oInstance.getLocationForEnclosure(iLocationA);
		Location locationB = oInstance.getLocationForEnclosure(iLocationB);
		return (int) getCost(locationA, locationB);
	}

	@Override
	public int getCostBetweenFoodPreparationAreaAnd(int iLocation) {
		Location foodPreparationArea = oInstance.getLocationOfFoodPreparationArea();
		int a1x = foodPreparationArea.x();
		int a1y = foodPreparationArea.y();
		Location location = oInstance.getLocationForEnclosure(iLocation);
		int a2x = location.x();
		int a2y = location.y();
		return (int) Math.sqrt(Math.pow(a1x - a2x, 2) + Math.pow(a1y - a2y, 2));
	}

}
