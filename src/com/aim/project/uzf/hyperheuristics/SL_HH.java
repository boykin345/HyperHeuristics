package com.aim.project.uzf.hyperheuristics;

import java.util.Arrays;
import java.util.Random;

import com.aim.project.uzf.SolutionPrinter;
import com.aim.project.uzf.UZFDomain;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;
import AbstractClasses.HyperHeuristic;
import AbstractClasses.ProblemDomain;

public class SL_HH extends HyperHeuristic {

    private static final int BEST_ACCEPTED_INDEX = 3;

    public SL_HH(long seed) {
        super(seed);
    }

    private double coolingRate = 0.95;
    double currentTemperature;

    private void advanceTemperature() {
        currentTemperature *= coolingRate;
    }

    @Override
    protected void solve(ProblemDomain oProblem) {

        oProblem.setMemorySize(4);
        int currentIndex = 0;
        int candidateIndex = 1;

        oProblem.initialiseSolution(currentIndex);

        double currentCost = oProblem.getFunctionValue(currentIndex);
        oProblem.copySolution(currentIndex, BEST_ACCEPTED_INDEX);
        currentTemperature = currentCost;

        int numberOfHeuristics = oProblem.getNumberOfHeuristics();

        // cache indices of crossover heuristics
        boolean[] isCrossover = new boolean[numberOfHeuristics];
        Arrays.fill(isCrossover, false);
        for (int i : oProblem.getHeuristicsOfType(ProblemDomain.HeuristicType.CROSSOVER)) {
            isCrossover[i] = true;
        }

        double candidateCost;

        while (!hasTimeExpired()) {
            int[] perm = createRandomPermutation(numberOfHeuristics);
            for (int i = 0; i < numberOfHeuristics; i++) {
                int h = perm[i];
                if (isCrossover[h]) {
                    candidateCost = oProblem.applyHeuristic(h, currentIndex, BEST_ACCEPTED_INDEX, candidateIndex);
                } else {
                    candidateCost = oProblem.applyHeuristic(h, currentIndex, candidateIndex);
                }
                double delta = candidateCost - currentCost;
                double r = rng.nextDouble();
                double P = Math.pow(Math.E, -delta / currentTemperature);
                if (delta < 0 || r < P) {
                    oProblem.copySolution(candidateIndex, currentIndex);
                    if (candidateCost < ((UZFDomain) oProblem).getBestSolutionValue()) {
                        oProblem.copySolution(candidateIndex, BEST_ACCEPTED_INDEX);
                    }
                    currentCost = candidateCost;
                }
                advanceTemperature();
            }
        }
        UAVSolutionInterface oSolution = ((UZFDomain) oProblem).getBestSolution();
        SolutionPrinter oSolutionPrinter = new SolutionPrinter("out.csv");
        oSolutionPrinter
                .printSolution(((UZFDomain) oProblem).getLoadedInstance().getSolutionAsListOfLocations(oSolution));
    }

    private int[] createRandomPermutation(int numberOfHeuristics) {
        int[] permutation = new int[numberOfHeuristics];
        for (int i = 0; i < numberOfHeuristics; i++) {
            permutation[i] = i;
        }
        java.util.Collections.shuffle(Arrays.asList(permutation));
        return permutation;
    }

    @Override
    public String toString() {
        return "SL_HH";
    }
}