package com.aim.project.uzf.solution;

import com.aim.project.uzf.interfaces.UAVSolutionInterface;
import com.aim.project.uzf.interfaces.SolutionRepresentationInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class UZFSolution implements UAVSolutionInterface {
	
	public UZFSolution(SolutionRepresentationInterface representation, int objectiveFunctionValue) {
		
		
	}

	@Override
	public int getObjectiveFunctionValue() {

		// TODO
		return -1;
	}

	@Override
	public void setObjectiveFunctionValue(int objectiveFunctionValue) {
		
		// TODO
	}

	@Override
	public SolutionRepresentationInterface getSolutionRepresentation() {
		
		// TODO
		return null;
	}
	
	@Override
	public UAVSolutionInterface clone() {
		
		// TODO
		return null;
	}

	@Override
	public int getNumberOfLocations() {
		
		// TODO
		return -1;
	}
}
