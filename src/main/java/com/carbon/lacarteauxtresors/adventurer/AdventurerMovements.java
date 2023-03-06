package com.carbon.lacarteauxtresors.adventurer;

import com.carbon.lacarteauxtresors.commons.Orientation;
import com.carbon.lacarteauxtresors.commons.Position;
import com.carbon.lacarteauxtresors.treasuremap.TreasureMap;
import com.carbon.lacarteauxtresors.treasuremap.TreasureMapValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;


/**
 * AdventurerMovements handles the moves an adventurer makes.
 */
@Data
@AllArgsConstructor
@Service
public class AdventurerMovements {

    public void changeOrientationOnMove(Adventurer adventurer, String move) {
        if (move.equals("G")) {
            adventurer.setOrientation(adventurer.getOrientation().turnLeft());
        } else if (move.equals("D")) {
            adventurer.setOrientation(adventurer.getOrientation().turnRight());
        }
    }

    /**
     * Changes the position of the adventurer based on its current orientation.
     * @param adventurer
     * @param orientation
     * @return
     */
    private Position moveByOrientation(Adventurer adventurer, Orientation orientation) {
        int newPositionX = adventurer.getPosition().getX();
        int newPositionY = adventurer.getPosition().getY();
        if (orientation.equals(Orientation.N)) {
            newPositionY = adventurer.getPosition().getY() - 1;
        } else if (orientation.equals(Orientation.E)) {
            newPositionX = adventurer.getPosition().getX() + 1;
        } else if (orientation.equals(Orientation.S)) {
            newPositionY = adventurer.getPosition().getY() + 1;
        } else if (orientation.equals(Orientation.O)) {
            newPositionX = adventurer.getPosition().getX() - 1;
        }
        return new Position(newPositionX, newPositionY);
    }

    public void updateAdventurerPosition(Adventurer adventurer, String move) {
        if(move.equals("A")){
            adventurer.setPosition(moveByOrientation(adventurer, adventurer.getOrientation()));
        }
    }

    /**
     * Changes adventure's orientation and then sees if it can move within the boundaries.
     * @param adventurer
     * @param treasureMap
     * @param move
     * @return
     */
    public boolean canAdventurerMove(Adventurer adventurer, TreasureMap treasureMap, String move) {
        changeOrientationOnMove(adventurer, move);
        Position position = adventurer.getPosition();
        if(move.equals("A")) position = moveByOrientation(adventurer, adventurer.getOrientation());
        TreasureMapValidator validator = new TreasureMapValidator(treasureMap);
        AdventurerValidator adventurerValidator = new AdventurerValidator(adventurer);
        return !validator.hasMountainAtPosition(position) &&
                adventurerValidator.isNextPostionInBounds(position, treasureMap.getWidth(), treasureMap.getHeight());
    }

}
