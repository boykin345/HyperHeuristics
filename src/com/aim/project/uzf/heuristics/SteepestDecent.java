package com.aim.project.uzf.heuristics;

import java.util.Random;

import com.aim.project.uzf.interfaces.HeuristicInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;

public class SteepestDecent extends HeuristicOperators implements HeuristicInterface {

    public SteepestDecent(Random random) {
        super(random);
    }

    @Override
    public int apply(UAVSolutionInterface oSolution, double dDepthOfSearch, double dIntensityOfMutation) {
        int bestEval = oSolution.getObjectiveFunctionValue();
        boolean improved = false;
        int length = oSolution.getNumberOfLocations();
        int bestI = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                swapLocations(oSolution.getSolutionRepresentation().getSolutionRepresentation(), i, j);
                int eval = oSolution.getObjectiveFunctionValue();
                if (eval < bestEval) {
                    bestEval = eval;
                    bestI = i;
                    improved = true;
                }
                swapLocations(oSolution.getSolutionRepresentation().getSolutionRepresentation(), i, j);
                if (improved) {
                    swapLocations(oSolution.getSolutionRepresentation().getSolutionRepresentation(), i, bestI);
                    improved = false;
                }
            }
        }
        return oSolution.getObjectiveFunctionValue();
    }

    @Override
    public boolean isCrossover() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCrossover'");
    }

    @Override
    public boolean usesIntensityOfMutation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usesIntensityOfMutation'");
    }

    @Override
    public boolean usesDepthOfSearch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usesDepthOfSearch'");
    }

}
