package com.battleship.models.player;

import com.battleship.models.Board;
import com.battleship.models.Point;
import com.battleship.models.enums.ShipState;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private int livesRemaining = 17;
    private int playerId;
    private Board board;
    private Scanner scanner;

    public HumanPlayer(int playerId) {
        this.playerId = playerId;
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int getPlayerId() {
        return this.playerId;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public void placeShips() {
        System.out.println("Player " + playerId + " please place your ships");
        board.placeShips();
    }

    @Override
    public void fireAt(Player enemy) {
        System.out.println(this.playerId + " : Please enter co-ordinates of attacking enemy");
        boolean isPointValid = false;

        while(!isPointValid) {
            try {
                Point point = new Point(scanner.nextInt(), scanner.nextInt());
                int x = point.getX();
                int y = point.getY();

                ShipState shipState = ((HumanPlayer) enemy).board.getBlock(x, y).shootAt();

                if(shipState == ShipState.PARTIAL_DESTRUCTION || shipState == ShipState.DESTROYED) {
                    livesRemaining--;
                }
                isPointValid = true;
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Self board:: " + this.getPlayerId());
        board.printBoard();
        System.out.println();
        System.out.println("Enemy board:: ");
        enemy.getBoard().printBoardForEnemy();
        System.out.println();
    }

    @Override
    public int getLivesRemaining() {
        return livesRemaining;
    }
}
