package com.carbon.lacarteauxtresors.treasuremap;

import com.carbon.lacarteauxtresors.adventurer.Adventurer;
import com.carbon.lacarteauxtresors.commons.Position;
import com.carbon.lacarteauxtresors.treasuremapitems.Mountain;
import com.carbon.lacarteauxtresors.treasuremapitems.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TreasureMapValidatorTest {

    @Mock
    Adventurer adventurer;
    List<Treasure> treasure;
    List<Mountain> mountains;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        treasure = new ArrayList<>();
        treasure.add(new Treasure(new Position(0, 0), 1));
        treasure.add(new Treasure(new Position(1, 1), 2));
        mountains = new ArrayList<>();
        mountains.add(new Mountain(new Position(0,1)));
        mountains.add(new Mountain(new Position(2,1)));
        adventurer.setNbTreasure(0);
    }

    @Test
    public void shouldHaveCorrectSize() {
        TreasureMap treasureMap = new TreasureMap(3, 4, mountains, treasure);
        TreasureMapValidator treasureMapValidator = new TreasureMapValidator(treasureMap);
        assertTrue(treasureMapValidator.isMapSizeValid());
    }

    @Test
    public void shouldHaveAllMountainsInBounds() {
        TreasureMap treasureMap = new TreasureMap(3, 4, mountains,treasure);
        TreasureMapValidator treasureMapValidator = new TreasureMapValidator(treasureMap);
        assertTrue(treasureMapValidator.areMapMountainsInBounds());
    }

    @Test
    public void shouldHaveAllTreasureInBounds() {
        TreasureMap treasureMap = new TreasureMap(3, 4, mountains, treasure);
        TreasureMapValidator treasureMapValidator = new TreasureMapValidator(treasureMap);
        assertTrue(treasureMapValidator.isMapTreasureInBounds());
    }

    @Test
    void testFindTreasureAtPosition() {
        TreasureMapValidator validator = new TreasureMapValidator();
        List<Treasure> foundTreasure = validator.findTreasureAtPosition(treasure, new Position(0, 0), adventurer);
        assertEquals(1, foundTreasure.size());
        assertEquals(0, treasure.get(0).getNbTreasure());
    }
}
