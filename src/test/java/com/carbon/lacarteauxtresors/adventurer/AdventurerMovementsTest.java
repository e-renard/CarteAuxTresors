package com.carbon.lacarteauxtresors.adventurer;

import com.carbon.lacarteauxtresors.commons.Orientation;
import com.carbon.lacarteauxtresors.commons.Position;
import com.carbon.lacarteauxtresors.treasuremap.TreasureMap;
import com.carbon.lacarteauxtresors.treasuremap.TreasureMapValidator;
import com.carbon.lacarteauxtresors.treasuremapitems.Mountain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdventurerMovementsTest {

    private AdventurerMovements adventurerMovement;

    private TreasureMapValidator treasureMapValidator;
    @Mock
    private Adventurer adventurer;
    @Mock
    private TreasureMap treasureMap;
    @Mock
    private AdventurerValidator adventurerValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock Adventurer object
        adventurer = mock(Adventurer.class);
        when(adventurer.getPosition()).thenReturn(new Position(1, 1));
        when(adventurer.getOrientation()).thenReturn(Orientation.N);
        when(adventurer.getMovementSequence()).thenReturn("AA");

        // Mock TreasureMap object
        treasureMap = mock(TreasureMap.class);
        when(treasureMap.getHeight()).thenReturn(4);
        when(treasureMap.getWidth()).thenReturn(3);
        when(treasureMap.getMountains()).thenReturn(Arrays.asList(new Mountain(new Position(1, 1))));

        adventurerMovement = new AdventurerMovements();
        treasureMapValidator = new TreasureMapValidator(treasureMap);
        adventurerValidator = new AdventurerValidator(adventurer);
    }

    @Test
    public void shouldNotPassThroughMountainsIfMountainAtAdventurerNextPosition() {
        assertTrue(treasureMapValidator.hasMountainAtPosition(adventurer.getPosition()));
    }

    @Test
    public void shouldOrientationChangeIfDGMovements() {
        Adventurer newOrientationAdventurer = new Adventurer(
                "Emily",
                new Position(1,1),
                Orientation.N,
                "A",
                0);
        adventurerMovement.changeOrientationOnMove(newOrientationAdventurer, "D");
        Orientation expectedOrientation = Orientation.E;
        assertEquals(newOrientationAdventurer.getOrientation(), expectedOrientation);
    }

    @Test
    public void shouldUpdateAdventurersPosition() {
        Adventurer newPosAdventurer = new Adventurer(
                "Emily",
                new Position(1,1),
                Orientation.N,
                "A",
                0);
        Position expectedPosition = new Position(1, 0);
        AdventurerMovements adventurerMovements = new AdventurerMovements();
        adventurerMovements.updateAdventurerPosition(newPosAdventurer, "A");
        assertEquals(newPosAdventurer.getPosition(), expectedPosition);
    }
}