package com.carbon.lacarteauxtresors.treasuremapitems;

import com.carbon.lacarteauxtresors.commons.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Treasure {

    private Position position;
    private int nbTreasure;

}
