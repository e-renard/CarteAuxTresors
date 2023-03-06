package com.carbon.lacarteauxtresors.treasuremap;

import com.carbon.lacarteauxtresors.adventurer.Adventurer;
import com.carbon.lacarteauxtresors.commons.Orientation;
import com.carbon.lacarteauxtresors.commons.Position;
import com.carbon.lacarteauxtresors.treasuremapitems.Mountain;
import com.carbon.lacarteauxtresors.treasuremapitems.Treasure;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExploreTreasureMapTest {

    @Test
    public void shouldRemoveEmptyTreasureFromList() {
        List<Treasure> treasure = Arrays.asList(
                new Treasure(new Position(1, 2), 1),
                new Treasure(new Position(2, 2), 0)
                );
        ExploreTreasureMap exploreTreasureMap = new ExploreTreasureMap();
        List<Treasure> expectedTreasureList = Arrays.asList(new Treasure(new Position(1, 2), 1));
        assertEquals(exploreTreasureMap.removeEmptyTreasure(treasure), expectedTreasureList);
    }

    @Test
    public void shouldCreateTreasureObject(){
        List<String> treasureData = List.of("T", "3", "4", "5");
        Position expectedPosition = new Position(3, 4);
        ExploreTreasureMap treasureMap = new ExploreTreasureMap();
        int expectedNbTreasure = 5;

        Treasure treasure = treasureMap.createTreasureFromData(treasureData);

        assertNotNull(treasure);
        assertEquals(expectedPosition, treasure.getPosition());
        assertEquals(expectedNbTreasure, treasure.getNbTreasure());
    }

    @Test
    public void shouldCreateMountainObject(){
        List<String> mountainData = List.of("M", "2", "0");
        Position expectedPosition = new Position(2, 0);
        ExploreTreasureMap treasureMap = new ExploreTreasureMap();

        Mountain mountain = treasureMap.createMountainFromData(mountainData);

        assertNotNull(mountain);
        assertEquals(expectedPosition, mountain.getPosition());
    }

    @Test
    public void shouldCreateAdventurerObject(){
        List<String> adventurerData = List.of("A", "Emily", "0", "0", "E", "AADADAGA");
        Position expectedPosition = new Position(0, 0);
        Orientation expectedOrientation = Orientation.E;
        ExploreTreasureMap treasureMap = new ExploreTreasureMap();

        Adventurer adventurer = treasureMap.createAdventurerFromData(adventurerData);

        assertNotNull(adventurer);
        assertEquals(expectedPosition, adventurer.getPosition());
        assertEquals(expectedOrientation, adventurer.getOrientation());
    }
}
