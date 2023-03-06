package com.carbon.lacarteauxtresors.treasuremap;

import com.carbon.lacarteauxtresors.treasuremapitems.Mountain;
import com.carbon.lacarteauxtresors.treasuremapitems.Treasure;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreasureMap {
    private Integer width;
    private Integer height;
    private List<Mountain> mountains;
    private List<Treasure> treasure;
}