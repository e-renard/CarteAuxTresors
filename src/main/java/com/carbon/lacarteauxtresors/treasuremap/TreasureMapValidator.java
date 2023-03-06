package com.carbon.lacarteauxtresors.treasuremap;


import com.carbon.lacarteauxtresors.adventurer.Adventurer;
import com.carbon.lacarteauxtresors.commons.Position;
import com.carbon.lacarteauxtresors.treasuremapitems.Treasure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TreasureMapValidator {
    private TreasureMap treasureMap;

    public boolean isTreasureMapValid() {
        return isMapSizeValid() && isMapTreasureInBounds() && areMapMountainsInBounds();
    }

    public boolean isMapSizeValid() {
        return this.treasureMap.getHeight() > 1 && this.treasureMap.getWidth() > 1;
    }

    public boolean areMapMountainsInBounds() {
        int width = this.treasureMap.getWidth();
        int height = this.treasureMap.getHeight();
        return this.treasureMap.getMountains().stream()
                .allMatch(mountain -> mountain.getPosition().getX() < width && mountain.getPosition().getY() < height);
    }

    public boolean isMapTreasureInBounds() {
        int width = this.treasureMap.getWidth();
        int height = this.treasureMap.getHeight();
        return this.treasureMap.getTreasure().stream()
                .allMatch(treasure -> treasure.getPosition().getX() < width && treasure.getPosition().getY() < height);
    }

    public boolean hasMountainAtPosition(Position position) {
        log.info("HAS MOUNTAIN : {}", this.treasureMap.getMountains().stream().anyMatch(mountain -> mountain.getPosition().equals(position)));
        return this.treasureMap.getMountains().stream().anyMatch(mountain -> mountain.getPosition().equals(position));
    }

    public List<Treasure> findTreasureAtPosition(List<Treasure> treasure, Position position, Adventurer adventurer) {
        return treasure.stream()
                .filter(treasure1 -> treasure1.getPosition().equals(position))
                .map(treasure1 -> {
                    log.info("{}", adventurer.getPosition());
                    treasure1.setNbTreasure(treasure1.getNbTreasure() - 1);
                    adventurer.setNbTreasure(adventurer.getNbTreasure()+1);
                    return treasure1;
                })
                .collect(Collectors.toList());
    }
}
