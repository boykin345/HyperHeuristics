package com.aim.project.uzf.heuristics;

import java.util.Random;

import com.aim.project.uzf.interfaces.ObjectiveFunctionInterface;
import com.aim.project.uzf.interfaces.UAVSolutionInterface;
import com.aim.project.uzf.interfaces.XOHeuristicInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class OrderCrossover implements XOHeuristicInterface {

    private final Random random;

    public OrderCrossover(Random random) {

        this.random = random;
    }

    @Override
    public int apply(UAVSolutionInterface solution, double depthOfSearch, double intensityOfMutation) {

        // TODO
        return 0;
    }

    @Override
    public double apply(UAVSolutionInterface parent1, UAVSolutionInterface parent2, UAVSolutionInterface offspring,
            double depthOfSearch, double intensityOfMutation) {

        int[] p1 = parent1.getSolutionRepresentation().getSolutionRepresentation();
        int[] p2 = parent2.getSolutionRepresentation().getSolutionRepresentation();
        int[] child = offspring.getSolutionRepresentation().getSolutionRepresentation();

        int size = p1.length;

        // Step 1: Select a random subset of the first parent
        int start = random.nextInt(size);
        int end = random.nextInt(size - start) + start;
        for (int i = start; i < end; i++) {
            child[i] = p1[i];
        }

        // Step 2: Fill the remaining positions with the genes from the second parent in
        // the order they appear, without duplicating any genes in the selected subset
        // from step 1
        int current = end;
        for (int i = end; i < end + size; i++) {
            int gene = p2[i % size];
            if (!containsGene(child, start, end, gene)) {
                child[current % size] = gene;
                current++;
            }
        }

        // Update the solution representation of the offspring
        offspring.getSolutionRepresentation().setSolutionRepresentation(child);

        // Evaluate the new solution
        double objectiveValue = offspring.getObjectiveFunctionValue();

        return objectiveValue;
    }

    private boolean containsGene(int[] array, int start, int end, int gene) {
        for (int i = start; i < end; i++) {
            if (array[i] == gene) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setObjectiveFunction(ObjectiveFunctionInterface f) {

        // TODO
    }

    @Override
    public boolean isCrossover() {

        return true;
    }

    @Override
    public boolean usesIntensityOfMutation() {

        return false;
    }

    @Override
    public boolean usesDepthOfSearch() {

        return false;
    }
}