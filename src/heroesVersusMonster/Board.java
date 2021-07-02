package heroesVersusMonster;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Board {
    private Square[][] board;
    private final short size;
    private List<Monster> monsters;

    public Board(short size) {
        this.board = new Square[size][size];
        this.size = size;
        generateBoard();
        this.monsters = generateMonsters();
    }

    private void generateBoard() {
        generateMap();
        generateMonsters();
    }

    private List<Monster> generateMonsters() {
        List<Monster> monsters = new ArrayList<>();

        short NBMONSTERS = 10;

        //getting random position
        Position position = new Position((short) 0, (short) 0);
        for (short i = 0; i < NBMONSTERS; i++) {

            position = position.getRandomPosition(this.size);
            while (this.board[position.getX()][position.getY()].getSquareType().equals(SquareType.FOREST) || this.board[position.getX()][position.getY()].isOccupied() || (position.getX()==0 && position.getY()==0)) {
                position = position.getRandomPosition(this.size);
            }
            if (i % 2 == 0) {
                Orc orc = new Orc(position);
                monsters.add(orc);
                this.board[position.getX()][position.getY()].setOccupied(true);
            }
            else if (i % 3 == 0) {
                Wolf wolf = new Wolf(position);
                monsters.add(wolf);
                this.board[position.getX()][position.getY()].setOccupied(true);
            } else {
                Whelp whelp = new Whelp(position);
                monsters.add(whelp);
                this.board[position.getX()][position.getY()].setOccupied(true);
            }
        }
        return monsters;
    }

    private void generateMap() {
        Random r = new Random();
        for (short x = 0; x < this.board.length; x++) {
            for (short y = 0; y < this.board.length; y++) {
                if (r.nextInt(10) < 8) this.board[x][y] = new Square(SquareType.PLAIN, new Position(x, y));
                else this.board[x][y] = new Square(SquareType.FOREST, new Position(x, y));
            }
        }
        /*
        prevent the player from being stuck
         */
        board[1][0].setSquareType(SquareType.PLAIN);
        board[0][1].setSquareType(SquareType.PLAIN);
        board[0][0].setSquareType(SquareType.PLAIN);
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void removeMonster(Monster monster) {
        this.monsters.remove(monster);
    }

    public void display(Heroes player) {
        String playerColor = "\u001B[91m";
        String resetColor = "\u001B[0m";
        String playerDisplay = playerColor+player.getName();
        for (int y = 0; y < size; y++) {
            System.out.println();
            for (int x = 0; x < size; x++) {
                SquareType temp = board[x][y].getSquareType();
                switch (temp) {
                    case FOREST:
                        System.out.format("%-12s", "FOREST");
                        break;
                    case PLAIN:
                        if (player.getPosition().getX() == x && player.getPosition().getY() == y) {
                            System.out.format("%-12s", playerDisplay);
                            System.out.format("%-9s", resetColor); //9 pcq reset color fait 9 ?
                        }
                        else {
                            Position tempPos = new Position((short) x, (short) y);
                            if (this.monsters.stream().anyMatch(m -> m.getPosition().equals(tempPos))) {
                                String type = "";
                                for (Monster m : monsters) {
                                    if (m.getPosition().equals(tempPos)) type = m.getType().toUpperCase(Locale.ROOT);
                                }
                                System.out.format("%-12s", type);
                            } else {
                                System.out.format("%-12s", "PLAIN");
                            }
                        }
                }

            }
        }
    }

    public boolean isMovePossible(Direction direction, Position playerPos) {
        switch (direction) {
            case NORTH :
                if (playerPos.getY()-1 >= 0) return board[playerPos.getX()][playerPos.getY()-1].getSquareType().equals(SquareType.PLAIN);
                break;
            case SOUTH:
                if (playerPos.getY()+1 <= 14) return board[playerPos.getX()][playerPos.getY()+1].getSquareType().equals(SquareType.PLAIN);
                break;
            case EAST:
                if (playerPos.getX()+1 <= 14) return board[playerPos.getX()+1][playerPos.getY()].getSquareType().equals(SquareType.PLAIN);
                break;
            case WEST:
                if (playerPos.getX()-1 >=0) return board[playerPos.getX()-1][playerPos.getY()].getSquareType().equals(SquareType.PLAIN);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
        return false;
    }
}
