package com.carbon.lacarteauxtresors.adventurer;

import com.carbon.lacarteauxtresors.commons.Orientation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adventurer {
    private String name;
    private Integer positionX;
    private Integer positionY;
    private Orientation orientation;
    private String movementSequence;
    private Integer nbTreasure;
}
