package com.carbon.lacarteauxtresors.adventurer;

import com.carbon.lacarteauxtresors.commons.Orientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdventurerValidatorTest {
    /*
    * is created
    * missing args
    * outofbounds
    * correct sequence mvt
    * isorientationconforme
    * shouldHaveNoTreasureOnStart
    *
     * */

    @Test
    public void shouldHaveAllAttributes() {
        Adventurer adventurer = new Adventurer(
                "Emily",
                1,
                1,
                Orientation.S,
                "AADAGADDA",
                0);
        AdventurerValidator adventurerValidator = new AdventurerValidator(adventurer);
        assertTrue(adventurerValidator.hasAllInfo());
    }

    @Test
    public void shouldHaveCorrectPosition() {
        Adventurer adventurer = new Adventurer(
                "Emily",
                1,
                1,
                Orientation.S,
                "AADAGADDA",
                0);
        AdventurerValidator adventurerValidator = new AdventurerValidator(adventurer);

        assertTrue(adventurerValidator.isPositionInMapBounds());
    }

    @Test
    public void shouldHaveIncorrectPosition() {
        Adventurer adventurer = new Adventurer(
                "Emily",
                -1,
                1,
                Orientation.S,
                "AADAGADDA",
                0);
        AdventurerValidator adventurerValidator = new AdventurerValidator(adventurer);

        assertFalse(adventurerValidator.isPositionInMapBounds());
    }
    @Test
    public void shouldHaveCorrectOrientation(){
        Adventurer adventurer = new Adventurer(
                "Emily",
                1,
                1,
                Orientation.S,
                "AADAGADDA",
                0);
        AdventurerValidator adventurerValidator = new AdventurerValidator(adventurer);

        assertTrue(adventurerValidator.isOrientationValid());
    }

    @Test
    public void shouldHaveCorrectMovementSequence(){
        Adventurer adventurer = new Adventurer(
                "Emily",
                1,
                1,
                Orientation.S,
                "AADAGADDA",
                0);
        AdventurerValidator adventurerValidator = new AdventurerValidator(adventurer);

        assertTrue(adventurerValidator.isMovementSequenceValid());
    }

}
