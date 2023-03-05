package com.carbon.lacarteauxtresors.treasuremap;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class TreasureMapValidator {
    private TreasureMap treasureMap;

    public boolean isTreasureMapValid(){
        return isMapSizeValid() && isMapTreasureInBounds() && areMapMountainsInBounds();
    }
    public boolean isMapSizeValid(){
        return this.treasureMap.getHeight() > 1 && this.treasureMap.getWidth() > 1;
    }

    public boolean areMapMountainsInBounds(){
        int width = this.treasureMap.getWidth();
        int height = this.treasureMap.getHeight();
        return this.treasureMap.getMountains().stream()
                .allMatch(mountain -> mountain.getPositionX() < width && mountain.getPositionY() < height);
    }

    public boolean isMapTreasureInBounds(){
        int width = this.treasureMap.getWidth();
        int height = this.treasureMap.getHeight();
        return this.treasureMap.getTreasure().stream()
                .allMatch(treasure -> treasure.getPositionX() < width && treasure.getPositionY() < height);
    }
}
