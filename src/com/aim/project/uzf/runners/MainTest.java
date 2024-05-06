package com.aim.project.uzf.runners;

import com.aim.project.uzf.UZFDomain;
import com.aim.project.uzf.UZFObjectiveFunction;
import com.aim.project.uzf.heuristics.AdjacentSwap;
import com.aim.project.uzf.heuristics.DavissHillClimbing;
import com.aim.project.uzf.heuristics.NextDescent;
import com.aim.project.uzf.heuristics.PMX;
import com.aim.project.uzf.heuristics.Reinsertion;
import com.aim.project.uzf.heuristics.SteepestDecent;
import com.aim.project.uzf.hyperheuristics.SR_IE_HH;
import com.aim.project.uzf.instance.InitialisationMode;
import com.aim.project.uzf.instance.UZFInstance;
import com.aim.project.uzf.instance.reader.UAVInstanceReader;
import com.aim.project.uzf.interfaces.UZFInstanceInterface;
import com.aim.project.uzf.solution.UZFSolution;

import AbstractClasses.HyperHeuristic;

import java.nio.file.Paths;
import java.util.Random;

class MainTest {
        public static void main(String[] args) {
                UAVInstanceReader reader = new UAVInstanceReader();

                UZFDomain domain = new UZFDomain(1234);
                domain.loadInstance(0);
                System.out.println("Loaded instance: " + domain.getLoadedInstance()); // Add debugging output here

                HyperHeuristic hh = new SR_IE_HH(1234);
                hh.loadProblemDomain(domain);
                System.out.println("Best solution value before running: " + hh.getBestSolutionValue()); // Add debugging
                                                                                                        // output here

                hh.run();

                System.out.println("Best solution value after running: " + hh.getBestSolutionValue()); // Add debugging
                                                                                                       // output here

                // UZFInstance instance = (UZFInstance)
                // reader.readUZFInstance(Paths.get("instances/uzf/grid.uzf"),
                // new Random());

                // PMX as = new PMX(new Random());

                // UZFObjectiveFunction objFunc = new UZFObjectiveFunction(instance);

                // UZFSolution sol = instance.createSolution(InitialisationMode.CONSTRUCTIVE);

                // as.apply(sol, 0.5, 0.5);

                // System.out.println(sol.getObjectiveFunctionValue());

                // System.out.println("\nNumber of locations: " +
                // instance.getNumberOfLocations());

                // System.out.println(objFunc.getCost(2, 3));

                // System.out.println(objFunc.getCostBetweenFoodPreparationAreaAnd(3));

                // System.out.println(objFunc.getObjectiveFunctionValue(
                // instance.createSolution(InitialisationMode.CONSTRUCTIVE).getSolutionRepresentation()));

                // System.out.println(sol);
        }
}