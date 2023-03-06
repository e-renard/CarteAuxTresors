package com.carbon.lacarteauxtresors.adventurer;

import com.carbon.lacarteauxtresors.commons.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdventurerValidator {

    private Adventurer adventurer;

    public boolean isAdventurerInfoValid() {
        return hasAllInfo() && isPositionInMapBounds() && isMovementSequenceValid();
    }
    public boolean hasAllInfo(){
        if (this.adventurer.getName().isEmpty() ||
                this.adventurer.getPosition() == null  ||
                this.adventurer.getOrientation() == null ||
                this.adventurer.getMovementSequence().isEmpty() ||
                this.adventurer.getNbTreasure() ==  null) {
            return false;
        }
        return true;
    }

    public boolean isPositionInMapBounds() {
        return this.adventurer.getPosition().getX() >= 0 && this.adventurer.getPosition().getY() >= 0;
    }

    public boolean isNextPostionInBounds(Position position, int width, int height){
        return position.getX() >= 0 && position.getX() < width
                && position.getY() < height && position.getY() >= 0;
    }

    public boolean isMovementSequenceValid(){
        Pattern pattern = Pattern.compile("^[\\s\\dADG]*$");
        Matcher matcher = pattern.matcher(this.adventurer.getMovementSequence());
        return matcher.find();
    }

}
