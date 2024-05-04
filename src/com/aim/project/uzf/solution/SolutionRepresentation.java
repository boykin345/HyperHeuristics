package com.aim.project.uzf.solution;

import com.aim.project.uzf.interfaces.SolutionRepresentationInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class SolutionRepresentation implements SolutionRepresentationInterface {
    private int[] solutionRepresentation;

    public SolutionRepresentation(int[] solutionRepresentation) {
        this.solutionRepresentation = solutionRepresentation;
    }

    @Override
    public int[] getSolutionRepresentation() {
        return this.solutionRepresentation;
    }

    @Override
    public void setSolutionRepresentation(int[] solutionRepresentation) {
        this.solutionRepresentation = solutionRepresentation;
    }

    @Override
    public int getNumberOfLocations() {
        return this.solutionRepresentation.length;
    }

    @Override
    public SolutionRepresentationInterface clone() {
        return new SolutionRepresentation(this.solutionRepresentation);
    }

}
