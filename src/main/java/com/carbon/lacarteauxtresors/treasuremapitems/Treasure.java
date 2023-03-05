package com.carbon.lacarteauxtresors.treasuremapitems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Treasure {

    private int positionX;
    private int positionY;
    private int nbTreasure;

}
