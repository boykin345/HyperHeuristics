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
	public int apply(UAVSolutionInterface solution, double depthOfSearch, double intensityOfMutation) {

		// TODO
		return -1;
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
