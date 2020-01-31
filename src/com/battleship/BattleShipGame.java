package com.battleship;

import com.battleship.models.player.HumanPlayer;
import com.battleship.models.player.Player;

public class BattleShipGame {
    private Player[] players;

    public BattleShipGame() {
        this.players = new HumanPlayer[] { new HumanPlayer(1), new HumanPlayer(2)};
    }

    public void startPlay() {
        int i = 0;
        int j = 1;
        int len = players.length;
        Player player = null;

        this.players[i].placeShips();
        this.players[j].placeShips();

        while(players[0].getLivesRemaining() > 0 && players[1].getLivesRemaining() > 0) {
            players[i++ % len].fireAt(players[j++ % len]);
            player = (players[0].getLivesRemaining() < players[1].getLivesRemaining()) ? players[1] : players[0];
        }

        if(player != null)
            System.out.println("Congrats Player " + player.getPlayerId() + ", you won!");
        else
            System.out.println("No winner");
    }
}
