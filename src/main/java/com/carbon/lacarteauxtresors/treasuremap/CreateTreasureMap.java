package com.carbon.lacarteauxtresors.treasuremap;

import com.carbon.lacarteauxtresors.adventurer.Adventurer;
import com.carbon.lacarteauxtresors.adventurer.AdventurerValidator;
import com.carbon.lacarteauxtresors.commons.FileManager;
import com.carbon.lacarteauxtresors.commons.Orientation;
import com.carbon.lacarteauxtresors.treasuremapitems.Mountain;
import com.carbon.lacarteauxtresors.treasuremapitems.Treasure;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Creates the map and its objects by using treasure_map.txt values.
 */
@NoArgsConstructor
@Service
public class CreateTreasureMap {
    /**
     * Create all objects of map.
     * @throws IOException if there is an error reading the file.
     */
    public void createMap() throws IOException {
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
        System.out.print(treasureMap);
    }
    private Treasure createTreasureFromData(List<String> treasureMapData) {
        return new Treasure(
                Integer.parseInt(treasureMapData.get(1)),
                Integer.parseInt(treasureMapData.get(2)),
                Integer.parseInt(treasureMapData.get(3)));
    }

    private Mountain createMountainFromData(List<String> treasureMapData) {
        return new Mountain(
                Integer.parseInt(treasureMapData.get(1)),
                Integer.parseInt(treasureMapData.get(2)));
    }

    private TreasureMap createMapFromData(List<String> treasureMapData, List<Mountain> mountain, List<Treasure> treasure) {
        TreasureMap treasureMap = new TreasureMap(
                Integer.parseInt(treasureMapData.get(1)),
                Integer.parseInt(treasureMapData.get(2)),
                mountain,
                treasure);
        TreasureMapValidator treasureMapValidator = new TreasureMapValidator(treasureMap);
        return treasureMapValidator.isTreasureMapValid() ? treasureMap : null;
    }

    private Adventurer createAdventurerFromData(List<String> adventurerData) {
        Adventurer adventurer = new Adventurer(
                adventurerData.get(1),
                Integer.parseInt(adventurerData.get(2)),
                Integer.parseInt(adventurerData.get(3)),
                Orientation.valueOf(adventurerData.get(4)),
                adventurerData.get(5),
                0);
        AdventurerValidator adventurerValidator = new AdventurerValidator(adventurer);
        return adventurerValidator.isAdventurerInfoValid() ? adventurer : null;
    }


}
