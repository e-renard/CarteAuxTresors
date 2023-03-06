package com.carbon.lacarteauxtresors.adventurer;

import com.carbon.lacarteauxtresors.commons.Orientation;
import com.carbon.lacarteauxtresors.commons.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adventurer {

    private String name;
    private Position position;
    private Orientation orientation;
    private String movementSequence;
    private Integer nbTreasure;

}
