package com.battleship.models.block;

import com.battleship.models.enums.ShipState;

public class WaterBlock implements PlayBlock {
    private boolean thisAreaIsHit = false;

    @Override
    public char getMarker() {
        return thisAreaIsHit ? 'H' : '-';
    }

    @Override
    public boolean thisAreaIsHit() {
        return thisAreaIsHit;
    }

    @Override
    public ShipState shootAt() {
        thisAreaIsHit = true;
        return ShipState.HEALTHY;
    }
}
