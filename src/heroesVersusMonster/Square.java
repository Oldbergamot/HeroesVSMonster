package heroesVersusMonster;

public class Square {
    private SquareType squareType;
    private Position position;
    private boolean isWalkable;
    private boolean isOccupied;

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Square(SquareType type, Position position) {
        this.squareType = type;
        this.position = position;
        this.isOccupied = false;
        switch (type) {
            case FOREST: this.isWalkable = false;
            break;
            case PLAIN: this.isWalkable = true;
            break;
            default : this.isWalkable = true;
        }
    }

    public SquareType getSquareType() {
        return squareType;
    }

    public void setSquareType(SquareType squareType) {
        this.squareType = squareType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }
}
