package com.aim.project.uzf.runners;

import com.aim.project.uzf.hyperheuristics.SL_HH;
import com.aim.project.uzf.hyperheuristics.SR_IE_HH;

import AbstractClasses.HyperHeuristic;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 *
 *        Runs a simple random IE hyper-heuristic then displays the best
 *        solution found
 */
public class SR_IE_VisualRunner extends HH_Runner_Visual {

	public SR_IE_VisualRunner(int instanceId) {
		super(instanceId);
	}

	@Override
	protected HyperHeuristic getHyperHeuristic(long seed) {

		return new SL_HH(seed);
	}

	public static void main(String[] args) {
		int instanceId = 0;
		// HH_Runner_Visual runner = new SR_IE_VisualRunner(instanceId);
		// System.out.println("Number of instance - " + instanceId);
		// runner.run();
		// for (int i = 0; i < 7; i++) {
		System.out.println("Number of instance - " + instanceId);
		HH_Runner_Visual runner1 = new SR_IE_VisualRunner(instanceId);
		runner1.run();
		// }
	}

}
