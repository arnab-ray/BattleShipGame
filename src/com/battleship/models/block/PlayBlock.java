package com.battleship.models.block;

import com.battleship.models.enums.ShipState;

public interface PlayBlock {
    char getMarker();

    boolean thisAreaIsHit();

    ShipState shootAt();
}
