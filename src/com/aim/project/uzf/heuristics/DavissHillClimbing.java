package com.aim.project.uzf.heuristics;

import java.util.Random;

import com.aim.project.uzf.interfaces.HeuristicInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class DavissHillClimbing extends HeuristicOperators implements HeuristicInterface {

	public DavissHillClimbing(Random random) {

		super(random);
	}

	@Override
	public int apply(UAVSolutionInterface solution, double dos, double iom) {
		int iterations;
		if (iom >= 0.0 && iom < 0.2) {
			iterations = 1;
		} else if (iom >= 0.2 && iom < 0.4) {
			iterations = 2;
		} else if (iom >= 0.4 && iom < 0.6) {
			iterations = 3;
		} else if (iom >= 0.6 && iom < 0.8) {
			iterations = 4;
		} else {
			iterations = 5;
		}

		for (int i = 0; i < iterations; i++) {
			int index = random.nextInt(solution.getNumberOfLocations());
			int adjacentIndex = (index + 1) % solution.getNumberOfLocations();
			swapLocations(solution.getSolutionRepresentation().getSolutionRepresentation(), index, adjacentIndex);
		}

		return solution.getObjectiveFunctionValue();
	}

	@Override
	public boolean isCrossover() {
		return false;
	}

	@Override
	public boolean usesIntensityOfMutation() {
		return false;
	}

	@Override
	public boolean usesDepthOfSearch() {
		return true;
	}
}
