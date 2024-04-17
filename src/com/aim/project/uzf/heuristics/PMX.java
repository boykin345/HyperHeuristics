package com.aim.project.uzf.heuristics;

import java.util.Random;

import com.aim.project.uzf.interfaces.ObjectiveFunctionInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;
import com.aim.project.uzf.interfaces.XOHeuristicInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class PMX implements XOHeuristicInterface {

	private final Random random;

	public PMX(Random random) {
		
		this.random = random;
	}

	@Override
	public int apply(UAVSolutionInterface solution, double depthOfSearch, double intensityOfMutation) {

		// TODO
		return -1;
	}

	@Override
	public double apply(UAVSolutionInterface p1, UAVSolutionInterface p2, UAVSolutionInterface c, double depthOfSearch, double intensityOfMutation) {

		// TODO
		return -1;
	}

	@Override
	public void setObjectiveFunction(ObjectiveFunctionInterface f) {

		// TODO
	}

	@Override
	public boolean isCrossover() {

		// TODO
		return random.nextBoolean();
	}

	@Override
	public boolean usesIntensityOfMutation() {

		// TODO
		return random.nextBoolean();
	}

	@Override
	public boolean usesDepthOfSearch() {

		// TODO
		return random.nextBoolean();
	}
}
