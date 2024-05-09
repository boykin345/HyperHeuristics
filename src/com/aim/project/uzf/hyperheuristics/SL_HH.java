package com.aim.project.uzf.hyperheuristics;

import AbstractClasses.HyperHeuristic;
import AbstractClasses.ProblemDomain;
import com.aim.project.uzf.SolutionPrinter;
import com.aim.project.uzf.UZFDomain;
import com.aim.project.uzf.heuristics.HeuristicOperators;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;

import java.util.Arrays;

public class SL_HH extends HyperHeuristic {
    private static final int SECOND_PARENT_INDEX = 2;

    private static final int BEST_ACCEPTED_INDEX = 3;

    public SL_HH(long lSeed) {

        super(lSeed);
    }

    @Override
    protected void solve(ProblemDomain oProblem) {

        oProblem.setMemorySize(4);

        int numberOfHeuristics = oProblem.getNumberOfHeuristics();
        int[] heuristicScores = new int[numberOfHeuristics];
        Arrays.fill(heuristicScores, 1);

        int currentIndex = 0;
        int candidateIndex = 1;
        oProblem.initialiseSolution(currentIndex);
        oProblem.copySolution(currentIndex, BEST_ACCEPTED_INDEX);

        double currentCost = oProblem.getFunctionValue(currentIndex);

        // cache indices of crossover heuristics
        boolean[] isCrossover = new boolean[numberOfHeuristics];
        Arrays.fill(isCrossover, false);

        for (int i : oProblem.getHeuristicsOfType(ProblemDomain.HeuristicType.CROSSOVER)) {

            isCrossover[i] = true;
        }

        // main search loop
        double candidateCost;
        while (!hasTimeExpired()) {
            int tournamentSize = rng.nextInt(numberOfHeuristics);
            int[] tournament = createRandomPermutation(numberOfHeuristics);
            int bstScore = heuristicScores[tournament[0]];
            int h = tournament[0];
            for (int i = 0; i < tournamentSize; i++) {
                if (heuristicScores[tournament[i]] < bstScore) {
                    h = tournament[i];
                    bstScore = heuristicScores[tournament[i]];
                }
            }
            if (isCrossover[h]) {
                if (rng.nextBoolean()) {
                    // randomly choose between crossover with newly initialised solution
                    oProblem.initialiseSolution(SECOND_PARENT_INDEX);
                    candidateCost = oProblem.applyHeuristic(h, currentIndex, SECOND_PARENT_INDEX, candidateIndex);
                } else {
                    // or with best solution accepted so far
                    candidateCost = oProblem.applyHeuristic(h, currentIndex, BEST_ACCEPTED_INDEX, candidateIndex);
                }
            } else {
                candidateCost = oProblem.applyHeuristic(h, currentIndex, candidateIndex);
            }

            // update best
            if (candidateCost < currentCost) {
                oProblem.copySolution(candidateIndex, BEST_ACCEPTED_INDEX);
            }
            if (candidateCost <= currentCost) {
                heuristicScores[h]++;
                currentCost = candidateCost;
                currentIndex = 1 - currentIndex;
                candidateIndex = 1 - candidateIndex;
            } else {
                heuristicScores[h]--;
            }
        }

        UAVSolutionInterface oSolution = ((UZFDomain) oProblem).getBestSolution();
        SolutionPrinter oSolutionPrinter = new SolutionPrinter("out.csv");
        oSolutionPrinter
                .printSolution(((UZFDomain) oProblem).getLoadedInstance().getSolutionAsListOfLocations(oSolution));
    }

    public int[] createRandomPermutation(int length) {
        int[] permutation = new int[length];
        for (int i = 0; i < length; i++) {
            permutation[i] = i;
        }
        for (int i = 0; i < length; i++) {
            int swapIndex = rng.nextInt(length);
            int temp = permutation[i];
            permutation[i] = permutation[swapIndex];
            permutation[swapIndex] = temp;
        }
        return permutation;
    }

    @Override
    public String toString() {

        return "SL_HH";
    }
}