package com.aim.project.uzf.heuristics;

import java.util.Random;

import com.aim.project.uzf.interfaces.HeuristicInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class NextDescent extends HeuristicOperators implements HeuristicInterface {

	public NextDescent(Random random) {

		super(random);
	}

	@Override
	public int apply(UAVSolutionInterface solution, double dos, double iom) {
		int iterations = 0;
		if (dos >= 0.0 && dos < 0.2) {
			iterations = 1;
		} else if (dos >= 0.2 && dos < 0.4) {
			iterations = 2;
		} else if (dos >= 0.4 && dos < 0.6) {
			iterations = 3;
		} else if (dos >= 0.6 && dos < 0.8) {
			iterations = 4;
		} else if (dos >= 0.8 && dos <= 1.0) {
			iterations = 5;
		}

		int totalSwaps = 0;
		for (int i = 0; i < iterations; i++) {
			int start = random.nextInt(solution.getNumberOfLocations());
			for (int j = 0; j < solution.getNumberOfLocations(); j++) {
				int index = (start + j) % solution.getNumberOfLocations();
				double currentObjectiveValue = solution.getObjectiveFunctionValue();
				swapLocations(solution.getSolutionRepresentation().getSolutionRepresentation(), index,
						(index + 1) % solution.getNumberOfLocations());
				double newObjectiveValue = solution.getObjectiveFunctionValue();
				if (newObjectiveValue < currentObjectiveValue) {
					totalSwaps++;
					break;
				} else {
					swapLocations(solution.getSolutionRepresentation().getSolutionRepresentation(), index,
							(index + 1) % solution.getNumberOfLocations());
				}
			}
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
