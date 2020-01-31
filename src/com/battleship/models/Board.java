package com.battleship.models;

import com.battleship.models.block.PlayBlock;
import com.battleship.models.block.ShipBlock;
import com.battleship.models.block.WaterBlock;
import com.battleship.models.enums.ShipDescriptor;

import java.util.Scanner;

public class Board {
    private static final char WATER = '-';
    private static final int BOARD_SIZE = 10;
    private static final String HORIZONTAL = "H";
    private static final String VERTICAL = "V";


    private Scanner scanner;
    private PlayBlock[][] board;
    private static final Ship[] ships = new Ship[] {new Ship(ShipDescriptor.CARRIER), new Ship(ShipDescriptor.BATTLESHIP),
            new Ship(ShipDescriptor.CRUISER), new Ship(ShipDescriptor.SUBMARINE), new Ship(ShipDescriptor.DESTROYER)};

    public Board() {
        this.scanner = new Scanner(System.in);
        this.board = new PlayBlock[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new WaterBlock();
            }
        }
    }

    public void placeShips() {
        for(Ship ship : ships) {
            boolean horizontal = getShipDirection();
            Point startingPoint = getStartingPoint(ship, horizontal);
            placeShip(ship, startingPoint, horizontal);

            printBoard();
        }
    }

    private boolean getShipDirection() {
        System.out.println("Please enter the direction of ship - horizontal (H) or vertical (V):: ");
        String direction;
        do {
            direction = scanner.nextLine().trim();
        } while (!HORIZONTAL.equals(direction) && !VERTICAL.equals(direction));

        return HORIZONTAL.equals(direction);
    }

    private Point getStartingPoint(Ship ship, boolean horizontal) {
        Point startingPos;
        do {
            System.out.println("Enter starting position of " + ship.getShipDescriptor().getName() + " of length " +
                    ship.getShipDescriptor().getSize());
            startingPos = new Point(scanner.nextInt(), scanner.nextInt());
        } while(!isValidStartingPoint(startingPos, ship.getShipDescriptor().getSize(), horizontal));

        return startingPos;
    }

    private boolean isValidStartingPoint(Point startingPos, int length, boolean horizontal) {
        int xDiff = 0, yDiff = 0;
        if(horizontal)
            yDiff = 1;
        else
            xDiff = 1;

        int x = startingPos.getX();
        int y = startingPos.getY();
        if(isOutsideBoard(x, y) || (isOutsideBoard(x, y + length) && horizontal) || (isOutsideBoard(x + length, y) && !horizontal)) {
            return false;
        }

        for(int i = 0; i < length; i++) {
            if(board[startingPos.getX() + i * xDiff][startingPos.getY() + i * yDiff].getMarker() != WATER){
                return false;
            }
        }
        return true;
    }

    private void placeShip(Ship ship, Point startingPoint, boolean horizontal) {
        int xDiff = 0, yDiff = 0;
        if(horizontal)
            yDiff = 1;
        else
            xDiff = 1;

        for(int i = 0; i < ship.getShipDescriptor().getSize() ; i++) {
            board[startingPoint.getX()+ i * xDiff][startingPoint.getY() + i * yDiff] = new ShipBlock(ship);
        }
    }

    public PlayBlock getBlock(int x, int y) {
        if(isOutsideBoard(x, y)) {
            throw new IllegalArgumentException("Outside board - try again: ");
        }
        return board[y][x];
    }

    private boolean isOutsideBoard(int x, int y) {
        return (x < 0 || x > BOARD_SIZE) || (y < 0 || y > BOARD_SIZE);
    }

    public void printBoard() {
        System.out.print("\t");

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + "\t");
        }

        System.out.println();

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + "\t");
            for(int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j].getMarker() + "\t");
            }
            System.out.println();
        }
    }

    public void printBoardForEnemy() {
        System.out.print("\t");

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + "\t");
        }

        System.out.println();

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + "\t");
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(board[i][j].thisAreaIsHit() && (board[i][j].getMarker() == 'P' || board[i][j].getMarker() == 'X'))
                    System.out.print('H' + "\t");
                else if(board[i][j].thisAreaIsHit())
                    System.out.print('N' + "\t");
                else
                    System.out.print('-' + "\t");
            }
            System.out.println();
        }
    }
}
