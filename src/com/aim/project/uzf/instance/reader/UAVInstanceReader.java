package com.aim.project.uzf.instance.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.util.Random;
import com.aim.project.uzf.interfaces.UZFInstanceInterface;
import com.aim.project.uzf.instance.Location;
import com.aim.project.uzf.instance.UZFInstance;
import com.aim.project.uzf.interfaces.UAVInstanceReaderInterface;

/**
 * @author Warren G Jackson
 * @since 1.0.0 (22/03/2024)
 */
public class UAVInstanceReader implements UAVInstanceReaderInterface {

	Random random;

	@Override
	public UZFInstanceInterface readUZFInstance(Path path, Random random) {
		List<Location> locations = new ArrayList<>();
		Location foodPreparationLocation = null;
		try (Scanner scanner = new Scanner(path)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.startsWith("ENCLOSURE_LOCATIONS")) {
					while (scanner.hasNextInt()) {
						int x = scanner.nextInt();
						int y = scanner.nextInt();
						locations.add(new Location(x, y));
					}
				} else if (line.startsWith("PREPARATION_AREA")) {
					int x = scanner.nextInt();
					int y = scanner.nextInt();
					foodPreparationLocation = new Location(x, y);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new UZFInstance(locations.size() + 1, locations.toArray(new Location[0]), foodPreparationLocation,
				random);
	}
}
