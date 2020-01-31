package com.battleship.models;

import com.battleship.models.enums.ShipDescriptor;
import com.battleship.models.enums.ShipState;

public class Ship {
    private final ShipDescriptor shipDescriptor;
    private int lives;

    public Ship(ShipDescriptor shipDescriptor) {
        this.shipDescriptor = shipDescriptor;
        this.lives = shipDescriptor.getSize();
    }

    public ShipDescriptor getShipDescriptor() {
        return this.shipDescriptor;
    }

    public int getLives() {
        return this.lives;
    }

    public void hitShip() {
        if(lives > 0) {
            System.out.println(shipDescriptor.getName() + " was hit");
            lives--;
        } else {
            System.out.println("Ship is destroyed!");
        }
    }

    public ShipState getState() {
        if(lives == 0) {
            return ShipState.DESTROYED;
        } else if(lives < shipDescriptor.getSize()) {
            return ShipState.PARTIAL_DESTRUCTION;
        } else {
            return ShipState.HEALTHY;
        }
    }
}
