package com.carbon.lacarteauxtresors.adventurer;

import com.carbon.lacarteauxtresors.commons.Orientation;
import com.carbon.lacarteauxtresors.treasuremapitems.Mountain;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AdventurerMovements handles the moves an adventurer makes.
 */
@Data
@AllArgsConstructor
@Service
public class AdventurerMovements {
    public void moveAdventurer(Adventurer adventurer) {
        String movements = adventurer.getMovementSequence();
        movements.chars().forEach(move -> {
            if(canAdventurerMove(adventurer, move)){

            }
        });
    }

    public void changeOrientationOnMove(Adventurer adventurer, String move){
        if(move.equals("G")){
            adventurer.setOrientation(adventurer.getOrientation().turnLeft());
        } else if (move.equals("D")) {
            adventurer.setOrientation(adventurer.getOrientation().turnRight());
        }
    }

    private void moveByOrientation(Adventurer adventurer, String orientation) {
        if (orientation.equals(Orientation.N.name())) {
            adventurer.setPositionY(adventurer.getPositionY() - 1);
        } else if (orientation.equals(Orientation.E.name())) {
            adventurer.setPositionX(adventurer.getPositionX() + 1);
        } else if (orientation.equals(Orientation.S.name())) {
            adventurer.setPositionY(adventurer.getPositionY() + 1);
        } else if (orientation.equals(Orientation.O.name())) {
            adventurer.setPositionX(adventurer.getPositionX() - 1);
        }
    }

    public boolean canAdventurerMove(Adventurer adventurer, String move) {

    }

    public boolean isAdventurerInBounds(Adventurer adventurer, int width, int height) {
        return adventurer.getPositionX() >= 0 && adventurer.getPositionX() < width
                && adventurer.getPositionY() >= 0 && adventurer.getPositionY() < height;
    }

    public boolean isAdventurerBlockedByMountain(Adventurer adventurer, List<Mountain> mountains){
        return mountains.stream().anyMatch(mountain -> mountain.getPositionX().equals(adventurer.getPositionX())
        && mountain.getPositionY().equals(adventurer.getPositionY()));
    }
}
