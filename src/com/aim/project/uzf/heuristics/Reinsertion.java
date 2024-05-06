package com.aim.project.uzf.heuristics;

import java.util.Random;

import com.aim.project.uzf.interfaces.HeuristicInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class Reinsertion extends HeuristicOperators implements HeuristicInterface {

	public Reinsertion(Random random) {

		super(random);
	}

	@Override
	public int apply(UAVSolutionInterface solution, double searchDepth, double intensityOfMutation) {

		// int reinsertions = 0;
		int[] solutions_array = solution.getSolutionRepresentation().getSolutionRepresentation();

		int numOfMaxReinsertions = numberIterations(intensityOfMutation);

		for (int x = 0; x < numOfMaxReinsertions; x++) {
			int removeIndex = random.nextInt(solutions_array.length);
			int removedElement = solutions_array[removeIndex];

			for (int y = removeIndex; y < solutions_array.length - 1; y++) {
				solutions_array[y] = solutions_array[y + 1];
			}
			solutions_array[solutions_array.length - 1] = -1; // Set the last element to -1

			// Select a random index to reinsert the removed element
			int insertIndex = random.nextInt(solutions_array.length);
			// Shift elements to make space for reinsertion
			for (int j = solutions_array.length - 1; j > insertIndex; j--) {
				solutions_array[j] = solutions_array[j - 1];
			}
			solutions_array[insertIndex] = removedElement; // Reinsert the removed element
			// reinsertions++;
		}

		// Update the solution representation with the modified array
		solution.getSolutionRepresentation().setSolutionRepresentation(solutions_array);

		return solution.getObjectiveFunctionValue();
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

	@Override
	public boolean isCrossover() {

		return false;
	}

	@Override
	public boolean usesIntensityOfMutation() {

		return true;
	}

	@Override
	public boolean usesDepthOfSearch() {

		return false;
	}

}