package com.carbon.lacarteauxtresors.adventurer;

import com.carbon.lacarteauxtresors.commons.Orientation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdventurerValidator {

    private Adventurer adventurer;

    public boolean isAdventurerInfoValid() {
        return hasAllInfo() && isPositionInMapBounds() && isOrientationValid() && isMovementSequenceValid();
    }
    public boolean hasAllInfo(){
        if (this.adventurer.getName().isEmpty() ||
                this.adventurer.getPositionX() == null  ||
                this.adventurer.getPositionY() == null ||
                this.adventurer.getOrientation() == null ||
                this.adventurer.getMovementSequence().isEmpty() ||
                this.adventurer.getNbTreasure() ==  null) {
            return false;
        }
        return true;
    }

    public boolean isPositionInMapBounds() {
        return this.adventurer.getPositionX() >= 0 && this.adventurer.getPositionY() >= 0;
    }

    public boolean isOrientationValid(){
        return Arrays.stream(Orientation.values())
                .anyMatch(orientation -> this.adventurer.getOrientation().equals(orientation.name()));
    }

    public boolean isMovementSequenceValid(){
        Pattern pattern = Pattern.compile("^[\\s\\dADG]*$");
        Matcher matcher = pattern.matcher(this.adventurer.getMovementSequence());
        return matcher.find();
    }

}
