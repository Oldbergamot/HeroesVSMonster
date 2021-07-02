package heroesVersusMonster;

import java.util.ArrayList;
import java.util.List;
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
            while (this.board[position.getX()][position.getY()].getSquareType().equals(SquareType.FOREST) || this.board[position.getX()][position.getY()].isOccupied()) {
                position = position.getRandomPosition(this.size);
                System.out.println("getting new position");
            }
            System.out.println(position.getX()+", "+ position.getY());
            if (i % 2 == 0) {
                Orc orc = new Orc(position);
                monsters.add(orc);
                this.board[position.getX()][position.getY()].setOccupied(true);
                System.out.println("orc created");
            }
            else if (i % 3 == 0) {
                Wolf wolf = new Wolf(position);
                monsters.add(wolf);
                this.board[position.getX()][position.getY()].setOccupied(true);
                System.out.println("wolf created");
            } else {
                Whelp whelp = new Whelp(position);
                monsters.add(whelp);
                this.board[position.getX()][position.getY()].setOccupied(true);
                System.out.println("whelp created");
            }
        }
        System.out.println("Monsters generated");
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
        System.out.println("Map generated");
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void removeMonster(Monster monster) {
        this.monsters.remove(monster);
    }

    public void display(Heroes player) {
        for (int x = 0; x < size; x++) {
            System.out.println();
            for (int y = 0; y < size; y++) {
                SquareType temp = board[x][y].getSquareType();
                switch (temp) {
                    case FOREST:
                        System.out.format("%-8s", "FOREST");
                        break;
                    case PLAIN:
                        if (player.getPosition().getX() == x && player.getPosition().getY() == y) {
                            System.out.format("%-8s", "PLAYER");
                        }
                        else {
                            Position tempPos = new Position((short) x, (short) y);
                            if (this.monsters.stream().anyMatch(m -> m.getPosition().equals(tempPos))) {
                                String type = "";
                                for (Monster m : monsters) {
                                    if (m.getPosition().equals(tempPos)) type = m.getType();
                                }
                                System.out.format("%-8s", type);
                            } else {
                                System.out.format("%-8s", "PLAIN");
                            }
                        }
                }

            }
        }
    }

    public boolean isMovePossible(Direction direction, Position playerPos) {
        boolean result = board[playerPos.getNextSquare(direction).getX()][playerPos.getNextSquare(direction).getY()].getSquareType().equals(SquareType.PLAIN);
        System.out.println("null : " +(playerPos.getNextSquare(direction)==null));
        System.out.println("next : "+playerPos.getNextSquare(direction).getX()+" , "+playerPos.getNextSquare(direction).getY());
        return playerPos.getNextSquare(direction) != null && result;
    }
}
