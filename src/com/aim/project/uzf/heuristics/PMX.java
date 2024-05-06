package com.aim.project.uzf.heuristics;

import java.util.HashMap;
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
		return 0;
	}

	@Override
	public double apply(UAVSolutionInterface firstP, UAVSolutionInterface secondP, UAVSolutionInterface offspring,
			double depthOfSearch, double intensityOfMutation) {

		int[] firstParent = firstP.getSolutionRepresentation().getSolutionRepresentation();
		int[] secondParent = secondP.getSolutionRepresentation().getSolutionRepresentation();
		int[] child = new int[firstParent.length];

		int crossPoint1 = (int) (Math.random() * firstParent.length);
		int crossPoint2 = (int) (Math.random() * firstParent.length);

		if (crossPoint1 > crossPoint2) {
			int temp = crossPoint1;
			crossPoint1 = crossPoint2;
			crossPoint2 = temp;
		}

		System.arraycopy(firstParent, crossPoint1, child, crossPoint1, crossPoint2 - crossPoint1 + 1);

		HashMap<Integer, Integer> mapvalue = new HashMap<>();
		for (int x = crossPoint1; x <= crossPoint2; x++) {
			mapvalue.put(firstParent[x], secondParent[x]);
		}

		for (int x = 0; x < firstParent.length; x++) {
			if (x < crossPoint1 || x > crossPoint2) {
				int candidate = secondParent[x];
				while (mapvalue.containsKey(candidate)) {
					candidate = mapvalue.get(candidate);
				}
				child[x] = candidate;
			}
		}

		offspring.getSolutionRepresentation().setSolutionRepresentation(child);

		return offspring.getObjectiveFunctionValue();

	}

	@Override
	public void setObjectiveFunction(ObjectiveFunctionInterface f) {

		// TODO
	}

	@Override
	public boolean isCrossover() {

		return true;
	}

	@Override
	public boolean usesIntensityOfMutation() {

		return false;
	}

	@Override
	public boolean usesDepthOfSearch() {

		return false;
	}
}