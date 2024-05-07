package com.aim.project.uzf.heuristics;

import java.util.Random;

import com.aim.project.uzf.interfaces.HeuristicInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class ExchangeSwap extends HeuristicOperators implements HeuristicInterface {

    public ExchangeSwap(Random random) {

        super(random);
    }

    @Override
    public int apply(UAVSolutionInterface solution, double depthOfSearch, double intensityOfMutation) {
        int numberOfSwaps;

        if (intensityOfMutation >= 0.0 && intensityOfMutation < 0.2) {
            numberOfSwaps = 1;
        } else if (intensityOfMutation >= 0.2 && intensityOfMutation < 0.4) {
            numberOfSwaps = 2;
        } else if (intensityOfMutation >= 0.4 && intensityOfMutation < 0.6) {
            numberOfSwaps = 4;
        } else if (intensityOfMutation >= 0.6 && intensityOfMutation < 0.8) {
            numberOfSwaps = 8;
        } else if (intensityOfMutation >= 0.8 && intensityOfMutation < 1.0) {
            numberOfSwaps = 16;
        } else if (intensityOfMutation == 1.0) {
            numberOfSwaps = 32;
        } else {
            throw new IllegalArgumentException("Intensity of mutation must be between 0.0 and 1.0");
        }
        for (int i = 0; i < numberOfSwaps; i++) {
            int index1 = random.nextInt(solution.getSolutionRepresentation().getNumberOfLocations());
            int index2 = random.nextInt(solution.getSolutionRepresentation().getNumberOfLocations());

            // Ensure we have two distinct indices
            while (index1 == index2) {
                index2 = random.nextInt(solution.getSolutionRepresentation().getNumberOfLocations());
            }

            swapLocations(solution.getSolutionRepresentation().getSolutionRepresentation(), index1, index2);
        }
        return solution.getObjectiveFunctionValue();
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
