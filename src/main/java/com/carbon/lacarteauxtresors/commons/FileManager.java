package com.carbon.lacarteauxtresors.commons;

import com.carbon.lacarteauxtresors.adventurer.Adventurer;
import com.carbon.lacarteauxtresors.treasuremap.TreasureMap;
import com.carbon.lacarteauxtresors.treasuremapitems.Mountain;
import com.carbon.lacarteauxtresors.treasuremapitems.Treasure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.carbon.lacarteauxtresors.constants.Constants.INFO_FILE_NAME;
import static com.carbon.lacarteauxtresors.constants.Constants.RESULT_FILE_NAME;

@Slf4j
public class FileManager {

    /**
     * Reads default data file.
     * @return List of each line's information.
     * @throws IOException if file not found.
     */
    public static List<List<String>> readFile() throws IOException {
        ClassPathResource resource = new ClassPathResource(INFO_FILE_NAME);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        List<String> lines = new ArrayList<>();
        String line;
        log.debug("IN METHODE");
        while ((line = reader.readLine()) != null) {
            if (isFormatCorrect(line)) {
                log.debug("IN IF {}", line);
                lines.add(line);
            }
        }
        return lines
                .stream()
                .map(s -> List.of(s.split(" - ")))
                .collect(Collectors.toList());
    }

    public static void writeFile(TreasureMap treasureMap, List<Adventurer> adventurers) throws IOException {
        File file = new File(RESULT_FILE_NAME);
        fileContent(file, treasureMap.getMountains(), adventurers, treasureMap);
    }

    /**
     * Write result of adventure to file, using the correct output format.
     * @param file
     * @param mountains
     * @param adventurers
     * @param treasureMap
     * @throws IOException
     */
    private static void fileContent(File file, List<Mountain> mountains, List<Adventurer> adventurers, TreasureMap treasureMap) throws IOException {

        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.printf("C - %d - %d\n", treasureMap.getWidth(), treasureMap.getHeight());
            for (Mountain mountain : mountains) {
                out.printf("M - %d - %d\n", mountain.getPosition().getX(), mountain.getPosition().getY());
            }
            for (Treasure treasure : treasureMap.getTreasure()) {
                out.printf("T - %d - %d - %d\n", treasure.getPosition().getX(), treasure.getPosition().getY(), treasure.getNbTreasure());
            }
            for (Adventurer adventurer : adventurers) {
                out.printf("A - %s - %d - %d - %s - %d\n", adventurer.getName(), adventurer.getPosition().getX(),
                        adventurer.getPosition().getY(), adventurer.getOrientation(), adventurer.getNbTreasure());
            }
        }
    }

    private static boolean isFormatCorrect(String line) {
        boolean isFormarCorrect = true;
        if (line.charAt(0) == 'C') {
            isFormarCorrect = isMapFormatCorrect(line);
        } else if (line.charAt(0) == 'M') {
            isFormarCorrect = isMountainFormatCorrect(line);
        } else if (line.charAt(0) == 'T') {
            isFormarCorrect = isTreasureFormatCorrect(line);
        } else if (line.charAt(0) == 'A') {
            isFormarCorrect = isAdventurerFormatCorrect(line);
        }
        return isFormarCorrect;
    }

    private static boolean isMapFormatCorrect(String line) {
        return line.matches("^C - \\d+ - \\d+$");
    }

    private static boolean isMountainFormatCorrect(String line) {
        return line.matches("^M - \\d+ - \\d+$");
    }

    private static boolean isTreasureFormatCorrect(String line) {
        return line.matches("^T - \\d+ - \\d+ - \\d+$");
    }

    private static boolean isAdventurerFormatCorrect(String line) {
        return line.matches("^A - \\w+ - \\d+ - \\d+ - [NESO] - [ADG]+$");
    }

}