package com.carbon.lacarteauxtresors.treasuremap;

import com.carbon.lacarteauxtresors.adventurer.Adventurer;
import com.carbon.lacarteauxtresors.adventurer.AdventurerMovements;
import com.carbon.lacarteauxtresors.adventurer.AdventurerValidator;
import com.carbon.lacarteauxtresors.commons.FileManager;
import com.carbon.lacarteauxtresors.commons.Orientation;
import com.carbon.lacarteauxtresors.commons.Position;
import com.carbon.lacarteauxtresors.treasuremapitems.Mountain;
import com.carbon.lacarteauxtresors.treasuremapitems.Treasure;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main actions of the program are defined in this class.
 * Creates the map and its objects by using treasure_map.txt values.
 */
@NoArgsConstructor
@Service
@Slf4j
public class ExploreTreasureMap {
    /**
     * Create all objects of map.
     * TODO separate the creation of objects and the simulation.
     * @throws IOException if there is an error reading the file.
     */
    public void createAndSimulateAdventure() throws IOException {
        List<List<String>> info = FileManager.readFile();

        List<Treasure> treasure = info.stream()
                .filter(listData -> listData.get(0).equals("T"))
                .map(this::createTreasureFromData)
                .toList();
        List<Mountain> mountains = info.stream()
                .filter(listData -> listData.get(0).equals("M"))
                .map(this::createMountainFromData)
                .toList();

        TreasureMap treasureMap = info.stream()
                .filter(listData -> listData.get(0).equals("C"))
                .map(stringList -> createMapFromData(stringList, mountains, treasure))
                .toList().get(0);

        List<Adventurer> adventurers = info.stream()
                .filter(listData -> listData.get(0).equals("A"))
                .map(this::createAdventurerFromData)
                .toList();

        adventurers.forEach(adventurer -> moveAdventurer(adventurer, treasureMap));
        FileManager.writeFile(treasureMap, adventurers);
        log.info("Check the root of the project! you will find the results in treasure_map_exploration_result.txt !");
    }

    public void moveAdventurer(Adventurer adventurer, TreasureMap treasureMap) {
        AdventurerMovements adventurerMovements = new AdventurerMovements();
        TreasureMapValidator treasureMapValidator = new TreasureMapValidator();
        String movements = adventurer.getMovementSequence();
        for (int i = 0; i < movements.length(); i++) {
            char move = movements.charAt(i);
            if (adventurerMovements.canAdventurerMove(adventurer, treasureMap, String.valueOf(move))) {
                adventurerMovements.updateAdventurerPosition(adventurer, String.valueOf(move));
                if(move == 'A') {
                    treasureMapValidator.findTreasureAtPosition(treasureMap.getTreasure(), adventurer.getPosition(), adventurer);
                    treasureMap.setTreasure(removeEmptyTreasure(treasureMap.getTreasure()));
                }
            } else {
                break;
            }
        }
    }

    public Treasure createTreasureFromData(List<String> treasureMapData) {
        return new Treasure(
                new Position(Integer.parseInt(treasureMapData.get(1)), Integer.parseInt(treasureMapData.get(2))),
                Integer.parseInt(treasureMapData.get(3)));
    }

    public Mountain createMountainFromData(List<String> treasureMapData) {
        return new Mountain(new Position(
                Integer.parseInt(treasureMapData.get(1)),
                Integer.parseInt(treasureMapData.get(2))));
    }

    public TreasureMap createMapFromData(List<String> treasureMapData, List<Mountain> mountain, List<Treasure> treasure) {
        TreasureMap treasureMap = new TreasureMap(
                Integer.parseInt(treasureMapData.get(1)),
                Integer.parseInt(treasureMapData.get(2)),
                mountain,
                treasure);
        TreasureMapValidator treasureMapValidator = new TreasureMapValidator(treasureMap);
        return treasureMapValidator.isTreasureMapValid() ? treasureMap : null;
    }

    public Adventurer createAdventurerFromData(List<String> adventurerData) {
        Adventurer adventurer = new Adventurer(
                adventurerData.get(1),
                new Position(Integer.parseInt(adventurerData.get(2)), Integer.parseInt(adventurerData.get(3))),
                Orientation.valueOf(adventurerData.get(4)),
                adventurerData.get(5),
                0);
        AdventurerValidator adventurerValidator = new AdventurerValidator(adventurer);
        return adventurerValidator.isAdventurerInfoValid() ? adventurer : null;
    }

    public List<Treasure> removeEmptyTreasure(List<Treasure> treasure) {
        return treasure.stream().filter(treasure1 -> treasure1.getNbTreasure()>0).collect(Collectors.toList());
    }

}
