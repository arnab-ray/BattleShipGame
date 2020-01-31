package com.battleship.models.block;

import com.battleship.models.Ship;
import com.battleship.models.enums.ShipState;

public class ShipBlock implements PlayBlock {
    private final Ship ship;
    private boolean thisAreaIsHit = false;

    public ShipBlock(Ship ship) {
        this.ship = ship;
    }

    @Override
    public char getMarker() {
        char marker = ' ';
        ShipState shipState = ship.getState();
        switch (shipState) {
            case HEALTHY:
                marker = 'S';
                break;
            case PARTIAL_DESTRUCTION:
                marker = 'P';
                break;
            case DESTROYED:
                marker = 'X';
                break;
        }

        return marker;
    }

    @Override
    public boolean thisAreaIsHit() {
        return thisAreaIsHit;
    }


    @Override
    public ShipState shootAt() {
        ship.hitShip();
        thisAreaIsHit = true;
        return ship.getState();
    }
}
