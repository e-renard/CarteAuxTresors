package com.carbon.lacarteauxtresors.commons;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.carbon.lacarteauxtresors.constants.Constants.INFO_FILE_NAME;

public class FileManager {
    public static List<List<String>> readFile() throws IOException {
        ClassPathResource resource = new ClassPathResource(INFO_FILE_NAME);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (isFormatCorrect(line)) {
                lines.add(line);
            }
        }
        return lines
                .stream()
                .map(s -> List.of(s.split(" - ")))
                .collect(Collectors.toList());
    }

    public static void writeFile() {

    }

    private static boolean isFormatCorrect(String line) {
        boolean isFormarCorrect = true;
        if (line.charAt(0) == 'C' || line.charAt(0) == 'M'){
            isFormarCorrect = isMapAndMountainFormatCorrect(line);
        } else if (line.charAt(0) == 'T') {
            isFormarCorrect = isTreasureFormatCorrect(line);
        } else if (line.charAt(0) == 'A') {
            isFormarCorrect = isAdventurerFormatCorrect(line);
        }
        return isFormarCorrect;
    }

    /**
     * TODO regex for mountain and map format
     * @param line
     * @return
     */
    private static boolean isMapAndMountainFormatCorrect(String line) {
        List<String> data = List.of(line.split(" - "));
        data.stream().anyMatch(s -> s.matches("[A-Za-z]\\d{2}"));
        //
        return true;
    }

    /**
     * TODO regex for treasure format
     * @param line
     * @return
     */
    private static boolean isTreasureFormatCorrect(String line) {
        List<String> data = List.of(line.split(" - "));
        data.stream().anyMatch(s -> s.matches("[A-Za-z]\\d{2}"));
        return true;
    }

    /**
     * TODO adventurer format
     * @param line
     * @return
     */
    private static boolean isAdventurerFormatCorrect(String line) {
        List<String> adventurerData = List.of(line.split(" - "));
        if (adventurerData.size() != 5) {
            return true;
        }
        return true;
    }

}