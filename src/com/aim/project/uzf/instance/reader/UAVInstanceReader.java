package com.aim.project.uzf.instance.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.nio.file.Path;
import java.util.Random;

import com.aim.project.uzf.interfaces.UZFInstanceInterface;
import com.aim.project.uzf.interfaces.UAVInstanceReaderInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class UAVInstanceReader implements UAVInstanceReaderInterface {

	@Override
	public UZFInstanceInterface readUZFInstance(Path path, Random random) {
		try {
			Scanner scanner = new Scanner(new File(path.toString()));
			String name = null;
			String comment = null;
			int[] preparationArea = null;
			List<int[]> enclosureLocations = new ArrayList<>();
	
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
	
				if (line.startsWith("NAME :")) {
					name = line.substring(6).trim();
				} else if (line.startsWith("COMMENT :")) {
					comment = line.substring(9).trim();
				} else if (line.equals("PREPARATION_AREA")) {
					preparationArea = parseCoordinates(scanner.nextLine());
				} else if (line.equals("ENCLOSURE_LOCATIONS")) {
					while (scanner.hasNextLine()) {
						line = scanner.nextLine().trim();
						if (line.equals("EOF")) {
							break;
						}
						enclosureLocations.add(parseCoordinates(line));
					}
				}
			}
	
			scanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	private int[] parseCoordinates(String line) {
		String[] parts = line.split(" ");
		return new int[] { Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) };
	}
}
