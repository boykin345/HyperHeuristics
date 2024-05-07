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
        int currentIndex = 0;
        int candidateIndex = 1;
        problem.setMemorySize(10);
        problem.initialiseSolution(currentIndex);
        problem.copySolution(currentIndex, 1);

        double currentCost = problem.getFunctionValue(currentIndex);
        int numberOfHeuristics = problem.getNumberOfHeuristics();

        while (!hasTimeExpired()) {
            int h = rng.nextInt(numberOfHeuristics);

            double candidateCost = problem.applyHeuristic(h, currentIndex, candidateIndex);

            if (candidateCost < currentCost) {
                problem.copySolution(candidateIndex, currentIndex);
                currentCost = candidateCost;
            }
        }
    }

    @Override
    public String toString() {
        return "SL_HH";
    }
}