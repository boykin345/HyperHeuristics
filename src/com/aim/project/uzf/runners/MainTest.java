package com.aim.project.uzf.runners;

import com.aim.project.uzf.SolutionPrinter;
import com.aim.project.uzf.UZFObjectiveFunction;
import com.aim.project.uzf.instance.InitialisationMode;
import com.aim.project.uzf.instance.reader.UAVInstanceReader;
import com.aim.project.uzf.interfaces.UZFInstanceInterface;
import java.nio.file.Paths;
import java.util.Random;

class MainTest {
    public static void main(String[] args) {
        UAVInstanceReader reader = new UAVInstanceReader();
        UZFInstanceInterface instance = reader.readUZFInstance(
                Paths.get(
                        "/Users/mkoliakin/Downloads/COMP2001-UZF-TEMPLATE-CODES/instances/uzf/clustered-enclosures.uzf"),
                new Random());
        UZFObjectiveFunction objFunc = new UZFObjectiveFunction(instance);

        System.out.println("\nNumber of locations: " + instance.getNumberOfLocations());

        System.out.println(objFunc.getCost(2, 3));

        System.out.println(objFunc.getCostBetweenFoodPreparationAreaAnd(3));

        System.out.println(objFunc.getObjectiveFunctionValue(
                instance.createSolution(InitialisationMode.RANDOM).getSolutionRepresentation()));
    }
}