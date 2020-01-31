package com.battleship.models.player;

import com.battleship.models.Board;

public interface Player {
    int getPlayerId();

    Board getBoard();

    void placeShips();

    void fireAt(Player enemy);

    int getLivesRemaining();
}
