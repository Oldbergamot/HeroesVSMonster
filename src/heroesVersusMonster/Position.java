package heroesVersusMonster;

import java.util.Objects;
import java.util.Random;

public class Position {
    private short x;
    private short y;

    public Position(short x, short y) {
        this.x = x;
        this.y = y;
    }

    public short getX() {
        return x;
    }

    public void setX(short x) {
        this.x = x;
    }

    public short getY() {
        return y;
    }

    public void setY(short y) {
        this.y = y;
    }

    public Position getRandomPosition(short limit) {
        Random r = new Random();
        return new Position((short) r.nextInt(limit), (short) r.nextInt(limit));
    }
//
//    public Position getNextSquare(Direction direction) {
//        switch (direction) {
//            case NORTH :
//                if (this.y-1 >= 0) return new Position(this.x, (short) (this.y-1));
//                break;
//            case SOUTH:
//                if (this.y+1 <= 14) return new Position(this.x, (short) (this.y+1));
//                break;
//            case EAST:
//                if (this.x+1 <= 14) return new Position((short) (this.x+1), this.y);
//                break;
//            case WEST:
//                if (this.x-1 >=0) return new Position((short) (this.x-1), this.y);
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + direction);
//        }
//        return null;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
