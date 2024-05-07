package com.aim.project.uzf.heuristics;

import com.aim.project.uzf.interfaces.HeuristicInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;

import java.util.Random;

public class SteepestDescent extends HeuristicOperators implements HeuristicInterface {
    public SteepestDescent(Random random) {
        super(random);
    }

    @Override
    public int apply(UAVSolutionInterface solution, double dos, double iom) {
        int numberOfIterations = getNumberOfIterations(dos);
        for (int i = 0; i < numberOfIterations; i++) {
            int[] bestRepresentation = solution.getSolutionRepresentation().getSolutionRepresentation();
            int[] curRepresentation = solution.getSolutionRepresentation().getSolutionRepresentation();
            int[] tmpRepresentation = solution.getSolutionRepresentation().getSolutionRepresentation();
            int bestEval = solution.getObjectiveFunctionValue();
            boolean improved = false;
            int startPosition = random.nextInt(solution.getNumberOfLocations());
            for (int j = startPosition; j < solution.getNumberOfLocations() + startPosition; j++) {
                int index1 = j % solution.getNumberOfLocations();
                int index2 = (index1 + 1) % solution.getNumberOfLocations();
                int temp = tmpRepresentation[index1];
                tmpRepresentation[index1] = tmpRepresentation[index2];
                tmpRepresentation[index2] = temp;
                solution.getSolutionRepresentation().setSolutionRepresentation(tmpRepresentation);
                int tmpEval = solution.getObjectiveFunctionValue();
                if (tmpEval < bestEval) {
                    bestRepresentation = solution.getSolutionRepresentation().getSolutionRepresentation();
                    bestEval = tmpEval;
                    improved = true;
                }
                solution.getSolutionRepresentation().setSolutionRepresentation(curRepresentation);
            }
            if (improved)
                solution.getSolutionRepresentation().setSolutionRepresentation(bestRepresentation);
        }
        return solution.getObjectiveFunctionValue();
    }

    private int getNumberOfIterations(double dos) {
        if (dos < 0.2) {
            return 1;
        } else if (dos < 0.4) {
            return 2;
        } else if (dos < 0.6) {
            return 3;
        } else if (dos < 0.8) {
            return 4;
        } else {
            return 5;
        }
    }

    @Override
    public boolean isCrossover() {

        return false;
    }

    @Override
    public boolean usesIntensityOfMutation() {

        return random.nextBoolean();
    }

    @Override
    public boolean usesDepthOfSearch() {

        return random.nextBoolean();
    }
}