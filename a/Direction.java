package snake.a;

public enum Direction {

    UP, DOWN, LEFT, RIGHT;

    public Direction opposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            default:
                return LEFT;
        }
    }

    public int newRow(int oldRow) {
        switch (this) {
            case UP:
                return oldRow - 1;
            case DOWN:
                return oldRow + 1;
            default:
                return oldRow;
        }
    }

    public int newCol(int oldCol) {
        switch (this) {
            case LEFT:
                return oldCol - 1;
            case RIGHT:
                return oldCol + 1;
            default:
                return oldCol;
        }
    }
}
