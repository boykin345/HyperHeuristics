package com.aim.project.uzf;

import com.aim.project.uzf.instance.Location;
import com.aim.project.uzf.interfaces.ObjectiveFunctionInterface;
import com.aim.project.uzf.interfaces.UZFInstanceInterface;
import com.aim.project.uzf.interfaces.SolutionRepresentationInterface;


/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class UZFObjectiveFunction implements ObjectiveFunctionInterface {

	public UZFObjectiveFunction(UZFInstanceInterface oInstance) {
		
	}

	@Override
	public int getObjectiveFunctionValue(SolutionRepresentationInterface oSolution) {

		return -1;
	}
	
	public int getCost(Location oLocationA, Location oLocationB) {

		return -1;
	}

	@Override
	public int getCost(int iLocationA, int iLocationB) {

		return -1;
	}

	@Override
	public int getCostBetweenFoodPreparationAreaAnd(int iLocation) {
		
		return -1;
	}

}
