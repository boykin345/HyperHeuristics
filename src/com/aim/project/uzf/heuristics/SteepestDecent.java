package com.aim.project.uzf.heuristics;

import com.aim.project.uzf.interfaces.HeuristicInterface;
import com.aim.project.uzf.interfaces.ObjectiveFunctionInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;

public class SteepestDecent implements HeuristicInterface{

    @Override
    public int apply(UAVSolutionInterface oSolution, double dDepthOfSearch, double dIntensityOfMutation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
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

    @Override
    public void setObjectiveFunction(ObjectiveFunctionInterface oObjectiveFunction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setObjectiveFunction'");
    }
    
}
