package com.aim.project.uzf.hyperheuristics;

import java.util.Random;

import AbstractClasses.HyperHeuristic;
import AbstractClasses.ProblemDomain;

public class SL_HH extends HyperHeuristic {

    public SL_HH(long seed) {
        super(seed);
    }

    @Override
    protected void solve(ProblemDomain problem) {
        problem.setMemorySize(4);

        int numberOfHeuristics = problem.getNumberOfHeuristics();

        int currentIndex = 0;
        problem.initialiseSolution(currentIndex);

        while (!hasTimeExpired()) {

            int heuristicToApply = rng.nextInt(numberOfHeuristics);

            // Apply the selected heuristic to the current solution
            double currentFitness = problem.getFunctionValue(currentIndex);
            double newFitness = problem.applyHeuristic(heuristicToApply, currentIndex, currentIndex);

            // If the new solution is better, accept it
            if (newFitness < currentFitness) {
                problem.copySolution(currentIndex, currentIndex);
            }
        }
    }

    @Override
    public String toString() {
        return "SL_HH";
    }
}