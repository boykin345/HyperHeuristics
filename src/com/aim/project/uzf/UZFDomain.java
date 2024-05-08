package com.aim.project.uzf;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import com.aim.project.uzf.heuristics.AdjacentSwap;
import com.aim.project.uzf.heuristics.DavissHillClimbing;
import com.aim.project.uzf.heuristics.ExchangeSwap;
import com.aim.project.uzf.heuristics.NextDescent;
import com.aim.project.uzf.heuristics.OrderCrossover;
import com.aim.project.uzf.heuristics.PMX;
import com.aim.project.uzf.heuristics.Reinsertion;
import com.aim.project.uzf.heuristics.SteepestDescent;
import com.aim.project.uzf.instance.InitialisationMode;
import com.aim.project.uzf.instance.Location;
import com.aim.project.uzf.instance.reader.UAVInstanceReader;
import com.aim.project.uzf.interfaces.HeuristicInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;
import com.aim.project.uzf.interfaces.UZFInstanceInterface;
import com.aim.project.uzf.interfaces.Visualisable;
import AbstractClasses.ProblemDomain;

public class UZFDomain extends ProblemDomain implements Visualisable {

	private int memorySize;
	private HeuristicInterface[] heuristics;
	private UAVSolutionInterface[] memory;
	private UAVSolutionInterface bestSolution;
	private UZFInstanceInterface instance;

	public UZFDomain(long seed) {

		// set default memory size and create the array of low-level heuristics
		super(seed);

		this.rng = new Random();

		this.memorySize = 6;
		this.memory = new UAVSolutionInterface[this.memorySize];

		this.heuristics = new HeuristicInterface[8];
		this.heuristics[0] = new AdjacentSwap(this.rng);
		this.heuristics[1] = new DavissHillClimbing(this.rng);
		this.heuristics[2] = new NextDescent(this.rng);
		this.heuristics[3] = new SteepestDescent(this.rng);
		this.heuristics[4] = new PMX(this.rng);
		this.heuristics[5] = new Reinsertion(this.rng);
		this.heuristics[6] = new ExchangeSwap(this.rng);
		this.heuristics[7] = new OrderCrossover(this.rng);
	}

	@Override
	public double applyHeuristic(int hIndex, int currentIndex, int candidateIndex) {
		// apply the heuristic and return the objective function value of the current
		// solution
		UAVSolutionInterface current = this.memory[currentIndex].clone();
		this.heuristics[hIndex].apply(current, depthOfSearch, intensityOfMutation);
		this.memory[candidateIndex] = current;
		return current.getObjectiveFunctionValue();
	}

	@Override
	public double applyHeuristic(int hIndex, int parent1Index, int parent2Index, int candidateIndex) {
		// apply the crossover heuristic and return the objective function value of the
		// candidate solution
		UAVSolutionInterface parent1 = memory[parent1Index];
		UAVSolutionInterface parent2 = memory[parent2Index];
		UAVSolutionInterface candidate = parent1.clone();
		if (hIndex == 4) {
			PMX pmx = (PMX) this.heuristics[4];
			pmx.apply(parent1, parent2, candidate, depthOfSearch, intensityOfMutation);
		}
		this.memory[candidateIndex] = candidate;
		return candidate.getObjectiveFunctionValue();
	}

	@Override
	public String bestSolutionToString() {
		return this.getBestSolution().toString();
	}

	@Override
	public boolean compareSolutions(int a, int b) {
		// return true if the solution in index 'a' is having a lower value than the
		// solution in index
		int aFunctionValue = this.memory[a].getObjectiveFunctionValue();
		int bFunctionValue = this.memory[b].getObjectiveFunctionValue();
		return aFunctionValue < bFunctionValue;
	}

	@Override
	public void copySolution(int a, int b) {

		// BEWARE this should copy the solution, not the reference to it!
		// That is, that if we apply a heuristic to the solution in index 'b',
		// then it does not modify the solution in index 'a' or vice-versa.
		this.memory[b] = this.memory[a].clone();

	}

	@Override
	public double getBestSolutionValue() {
		return this.getBestSolution().getObjectiveFunctionValue();
	}

	@Override
	public double getFunctionValue(int index) {
		return this.memory[index].getObjectiveFunctionValue();
	}

	@Override
	public int[] getHeuristicsOfType(HeuristicType type) {
		// return the indices of the heuristics that belong to the given type
		if (type == HeuristicType.LOCAL_SEARCH) {
			return new int[] { 0, 1, 2, 3 };
		} else if (type == HeuristicType.CROSSOVER) {
			return new int[] { 4 };
		} else if (type == HeuristicType.MUTATION) {
			return new int[] { 5 };
		}
		return null;
	}

	@Override
	public int[] getHeuristicsThatUseDepthOfSearch() {
		return new int[] { 1, 3 };
	}

	@Override
	public int[] getHeuristicsThatUseIntensityOfMutation() {
		return new int[] { 0, 2, 5 };
	}

	@Override
	public int getNumberOfHeuristics() {
		// has to be hard-coded due to the design of the HyFlex framework
		return (heuristics != null) ? heuristics.length : 0;
	}

	@Override
	public int getNumberOfInstances() {
		// get the number of instances from the directory
		File dir = new File("/Users/mkoliakin/Downloads/COMP2001-UZF-TEMPLATE-CODES/instances/uzf");
		return dir.list((dir1, name) -> name.endsWith(".uzf")).length;
	}

	public void initialiseSolution(int index) {
		// make sure that you also update the best solution!
		// initialise the solution at the given index
		UZFInstanceInterface instance = getLoadedInstance();
		UAVSolutionInterface constructiveSolution = instance.createSolution(InitialisationMode.CONSTRUCTIVE);
		UAVSolutionInterface randomSolution = instance.createSolution(InitialisationMode.RANDOM);
		if (constructiveSolution.getObjectiveFunctionValue() < randomSolution.getObjectiveFunctionValue()) {
			memory[index] = constructiveSolution;
		} else {
			memory[index] = randomSolution;
		}
		updateBestSolution(index);
	}

	@Override
	public void loadInstance(int instanceId) {
		// load the instance from the file
		String filename;
		switch (instanceId) {
			case 0:
				filename = "square.uzf";
				break;
			case 1:
				filename = "libraries-15.uzf";
				break;
			case 2:
				filename = "carparks-40.uzf";
				break;
			case 3:
				filename = "tramstops-85.uzf";
				break;
			case 4:
				filename = "grid.uzf";
				break;
			case 5:
				filename = "clustered-enclosures.uzf";
				break;
			case 6:
				filename = "chatgpt-instance-100-enclosures.uzf";
				break;
			default:
				throw new IllegalArgumentException("Invalid instance ID");
		}
		UAVInstanceReader reader = new UAVInstanceReader();
		String path = "/Users/mkoliakin/Downloads/COMP2001-UZF-TEMPLATE-CODES/instances/uzf/" + filename;
		Path pathToFile = Paths.get(path);
		this.instance = reader.readUZFInstance(pathToFile, this.rng);
		for (HeuristicInterface heuristic : heuristics) {
			heuristic.setObjectiveFunction(instance.getUZFObjectiveFunction());
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
		return this.memory[index].toString();

	}

	@Override
	public String toString() {
		return "UZFDomain";
	}

	private void updateBestSolution(int index) {
		// update the best solution if the solution at the given index is better
		if (bestSolution == null || this.memory[index].getObjectiveFunctionValue() < this.getBestSolution()
				.getObjectiveFunctionValue()) {
			this.bestSolution = this.memory[index].clone();
		}
	}

	@Override
	public UZFInstanceInterface getLoadedInstance() {

		return this.instance;
	}

	/**
	 * @return The integer array representing the ordering of the best solution.
	 */
	@Override
	public int[] getBestSolutionRepresentation() {
		return this.bestSolution.getSolutionRepresentation().getSolutionRepresentation();
	}

	@Override
	public Location[] getRouteOrderedByLocations() {
		// return the best solution as an array of locations
		if (this.bestSolution == null) {
			return null;
		}
		List<Location> locationList = this.getLoadedInstance().getSolutionAsListOfLocations(this.bestSolution);
		Location[] locationArray = new Location[locationList.size()];
		return locationList.toArray(locationArray);
	}

	public UAVSolutionInterface getBestSolution() {
		return this.bestSolution;
	}
}
