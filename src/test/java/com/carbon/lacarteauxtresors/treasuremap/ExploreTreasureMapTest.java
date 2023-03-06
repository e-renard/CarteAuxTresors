package com.carbon.lacarteauxtresors.treasuremap;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ExploreTreasureMapTest {

    @Test
    public void buildtest() throws IOException {
        ExploreTreasureMap treasureMap = new ExploreTreasureMap();
        treasureMap.createAndSimulateAdventure();
    }

    @Test
    public void shouldRemoveEmptyTreasureFromList() {

    }
}
