package com.carbon.lacarteauxtresors.treasuremap;

import com.carbon.lacarteauxtresors.commons.Position;
import com.carbon.lacarteauxtresors.treasuremapitems.Mountain;
import com.carbon.lacarteauxtresors.treasuremapitems.Treasure;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class TreasureMapValidatorTest {
    @Test
    public void shouldHaveCorrectSize() {
        TreasureMap treasureMap = new TreasureMap(3, 4, any(), any());
        TreasureMapValidator treasureMapValidator = new TreasureMapValidator(treasureMap);
        assertTrue(treasureMapValidator.isMapSizeValid());
    }

    @Test
    public void shouldHaveAllMountainsInBounds() {
        List<Mountain> mountains = Stream.
                of(
                new Mountain(new Position(1,0)),
                        new Mountain(new Position(1,0))
                )
                .toList();
        TreasureMap treasureMap = new TreasureMap(3, 4, mountains, any());
        TreasureMapValidator treasureMapValidator = new TreasureMapValidator(treasureMap);
        assertTrue(treasureMapValidator.areMapMountainsInBounds());
    }

    @Test
    public void shouldHaveAllTreasureInBounds() {
        List<Treasure> treasure = Stream.of(
                new Treasure(new Position(0,2), 2)
        ).toList();
        TreasureMap treasureMap = new TreasureMap(3, 4, any(), treasure);
        TreasureMapValidator treasureMapValidator = new TreasureMapValidator(treasureMap);
        assertTrue(treasureMapValidator.isMapTreasureInBounds());
    }

    public void shouldHaveOneObjectPerPosition() {

    }
}
