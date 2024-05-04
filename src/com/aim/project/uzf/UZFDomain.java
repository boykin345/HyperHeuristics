package com.aim.project.uzf;

import com.aim.project.uzf.heuristics.AdjacentSwap;
import com.aim.project.uzf.heuristics.DavissHillClimbing;
import com.aim.project.uzf.heuristics.HeuristicOperators;
import com.aim.project.uzf.heuristics.NextDescent;
import com.aim.project.uzf.heuristics.SteepestDecent;
import com.aim.project.uzf.instance.Location;
import com.aim.project.uzf.interfaces.HeuristicInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;
import com.aim.project.uzf.interfaces.UZFInstanceInterface;
import com.aim.project.uzf.interfaces.Visualisable;

import AbstractClasses.ProblemDomain;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class UZFDomain extends ProblemDomain implements Visualisable {

	private int memorySize;
	private HeuristicInterface[] heuristics;

	public UZFDomain(long seed) {

		// TODO - set default memory size and create the array of low-level heuristics
		super(seed);
		this.memorySize = 12;
		this.heuristics = new HeuristicInterface[] {
				new AdjacentSwap(this.rng),
				new DavissHillClimbing(this.rng),
				new NextDescent(this.rng),
				new SteepestDecent(this.rng)
		};
	}

	@Override
	public double applyHeuristic(int hIndex, int currentIndex, int candidateIndex) {

		// TODO - apply heuristic and return the objective value of the candidate
		// solution
		return -1;
	}

	@Override
	public double applyHeuristic(int hIndex, int parent1Index, int parent2Index, int candidateIndex) {

		// TODO - apply heuristic and return the objective value of the candidate
		// solution
		return -1;
	}

	@Override
	public String bestSolutionToString() {

		// TODO
		return null;
	}

	@Override
	public boolean compareSolutions(int a, int b) {

		// TODO
		return false;
	}

	@Override
	public void copySolution(int a, int b) {

		// TODO - BEWARE this should copy the solution, not the reference to it!
		// That is, that if we apply a heuristic to the solution in index 'b',
		// then it does not modify the solution in index 'a' or vice-versa.

	}

	@Override
	public double getBestSolutionValue() {

		// TODO
		return 0;
	}

	@Override
	public double getFunctionValue(int index) {

		// TODO
		return -1;
	}

	// TODO
	@Override
	public int[] getHeuristicsOfType(HeuristicType type) {

		return null;
	}

	@Override
	public int[] getHeuristicsThatUseDepthOfSearch() {

		return null;
	}

	@Override
	public int[] getHeuristicsThatUseIntensityOfMutation() {

		return null;
	}

	@Override
	public int getNumberOfHeuristics() {
		// has to be hard-coded due to the design of the HyFlex framework
		return heuristics.length;
	}

	@Override
	public int getNumberOfInstances() {

		// TODO
		return -1;
	}

	@Override
	public void initialiseSolution(int index) {

		// TODO - make sure that you also update the best solution!
	}

	@Override
	public void loadInstance(int instanceId) {

		// TODO load the instance (referenced by ID) from file
		// here might be a good place to set the objective function within each
		// low-level heuristic
		String instancePath;
		switch (instanceId) {
			case 0:
				instancePath = "instances/uzf/square.uzf";
				break;
			case 1:
				instancePath = "instances/uzf/libraries-15.uzf";
				break;
			case 2:
				instancePath = "instances/uzf/carparks-40.uzf";
				break;
			case 3:
				instancePath = "instances/uzf/tramstops-85.uzf";
				break;
			case 4:
				instancePath = "instances/uzf/grid.uzf";
				break;
			case 5:
				instancePath = "instances/uzf/clustered-enclosures.uzf";
				break;
			case 6:
				instancePath = "instances/uzf/chatgpt-instance-100-enclosures.uzf";
				break;
			default:
				throw new IllegalArgumentException("Invalid instance ID");
		}

	}

	@Override
	public void setMemorySize(int size) {
		if (size < 0) {
			throw new IllegalArgumentException("Memory size cannot be negative");
		}
		this.memorySize = size;
	}

	@Override
	public String solutionToString(int index) {

		return null;

	}

	@Override
	public String toString() {

		return null;
	}

	private void updateBestSolution(int index) {

		// TODO - make sure we cannot modify the best solution accidentally after
		// storing it!
	}

	@Override
	public UZFInstanceInterface getLoadedInstance() {

		return null;
	}

	/**
	 * @return The integer array representing the ordering of the best solution.
	 */
	@Override
	public int[] getBestSolutionRepresentation() {

		return null;
	}

	@Override
	public Location[] getRouteOrderedByLocations() {

		// TODO
		return null;
	}

	public UAVSolutionInterface getBestSolution() {

		// TODO
		return null;
	}
}
