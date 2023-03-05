package com.carbon.lacarteauxtresors.treasuremap;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BuildTreasureMapTest {

    @Test
    public void buildtest() throws IOException {
        CreateTreasureMap treasureMap = new CreateTreasureMap();
        treasureMap.createMap();
    }
}
