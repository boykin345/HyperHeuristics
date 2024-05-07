package com.aim.project.uzf.heuristics;

import java.util.Arrays;
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
		int numberOfIterations = numberIterations(dos);
		for (int i = 0; i < numberOfIterations; i++) {
			double bestEval = solution.getObjectiveFunctionValue();
			int[] bestRepresentation = solution.getSolutionRepresentation().getSolutionRepresentation();
			int[] tmpRepresentation = solution.getSolutionRepresentation().getSolutionRepresentation();
			int[] perm = randomPermutation(solution.getNumberOfLocations());
			for (int j = 0; j < solution.getNumberOfLocations(); j++) {
				int index1 = perm[j];
				int index2 = (index1 + 1) % solution.getNumberOfLocations();
				int temp = tmpRepresentation[index1];
				tmpRepresentation[index1] = tmpRepresentation[index2];
				tmpRepresentation[index2] = temp;
				solution.getSolutionRepresentation().setSolutionRepresentation(tmpRepresentation);
				double tmpEval = solution.getObjectiveFunctionValue();
				if (tmpEval > bestEval) {
					solution.getSolutionRepresentation().setSolutionRepresentation(bestRepresentation);
				} else {
					bestEval = tmpEval;
					bestRepresentation = tmpRepresentation;
				}
			}
		}
		return solution.getObjectiveFunctionValue();
	}

	public int[] randomPermutation(int length) {
		int[] permutation = new int[length];
		for (int i = 0; i < length; i++) {
			permutation[i] = i;
		}
		java.util.Collections.shuffle(Arrays.asList(permutation));
		return permutation;
	}

	public int numberIterations(double searchDepth) {
		if (searchDepth < 0.2) {
			return 1;
		} else if (searchDepth < 0.4) {
			return 2;
		} else if (searchDepth < 0.6) {
			return 3;
		} else if (searchDepth < 0.8) {
			return 4;
		} else if (searchDepth < 1) {
			return 5;
		}
		return 0; // depth of search always between 0 and 1.
	}

	public boolean isCrossover() {

		// return random.nextBoolean();
		return false;
	}

	@Override
	public boolean usesIntensityOfMutation() {

		// return random.nextBoolean();
		return false;
	}

	@Override
	public boolean usesDepthOfSearch() {

		// return random.nextBoolean();
		return true;
	}
}