package snake.studentB;

/**
 * An enum to represent the direction in which something moves.
 */
public enum Direction {

    UP, DOWN, LEFT, RIGHT;

    /**
     * Gets the opposite direction of the current value.
     * @return opposite direction
     */
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

    /**
     * Calculates the new row value when starting at old row and moving in this
     * direction.
     *
     * @param oldRow the previous row value
     * @return the new row value
     */
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

    /**
     * Calculates the new column value when starting at old column and moving in this
     * direction.
     *
     * @param oldCol the previous column value
     * @return the new column value
     */
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
