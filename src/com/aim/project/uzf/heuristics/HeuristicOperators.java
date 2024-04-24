package com.aim.project.uzf.heuristics;

import java.util.Arrays;
import java.util.Random;

import com.aim.project.uzf.interfaces.ObjectiveFunctionInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 * <br>
 * This class is included (and all non-crossover heuristics subclass this class) to simplify your implementation and it
 * is intended that you include any common operations in this class to simplify your implementation of the other heuristics.
 * Furthermore, if you implement and test common functionality here, it is less likely that you introduce a bug elsewhere!
 * <br>
 * For example, think about common neighbourhood operators and any other incremental changes that you might perform
 * while applying low-level heuristics.
 */
public class HeuristicOperators {

	protected ObjectiveFunctionInterface f;

	protected final Random random;

	public int[] createRandomPermutation (int length) {
		int[] permutation = new int[length];
		for (int i = 0; i < length; i++) {
			permutation[i] = i;
		}
		java.util.Collections.shuffle(Arrays.asList(permutation));
		return permutation;
	}

	protected int calculateReinsertions(double intensityOfMutation) {
        if (intensityOfMutation < 0.2) return 1;
        else if (intensityOfMutation < 0.4) return 2;
        else if (intensityOfMutation < 0.6) return 3;
        else if (intensityOfMutation < 0.8) return 4;
        else return 5;
    }
	

	public HeuristicOperators(Random random) {

		this.random = random;
	}

	public void swapLocations(int[] solution, int i, int j) {
		int temp = solution[i];
		solution[i] = solution[j];
		solution[j] = temp;
	}

	public void setObjectiveFunction(ObjectiveFunctionInterface f) {

		this.f = f;
	}
}
