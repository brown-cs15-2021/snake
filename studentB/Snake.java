package snake.studentB;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Wraps an ArrayList<SnakeTile> and ArrayList<Rectangle> to represent the snake
 * moving across the board. Tracks the direction it's moving in and the row/col
 * location of its head.
 */
public class Snake {

    private ArrayList<PicnicTile> snakeTiles;
    private ArrayList<Rectangle> snakeBody;
    private PicnicTile[][] board;
    private Pane gamePane;
    private Direction directionMoving;
    private Direction nextDirection;
    private int row;
    private int col;

    /**
     * Creates the snake.
     * @param board the board for it to move across
     * @param gamePane the pane for it to graphically appear
     */
    public Snake(PicnicTile[][] board, Pane gamePane) {
        this.board = board;
        this.gamePane = gamePane;
        this.snakeTiles = new ArrayList<>();
        this.snakeBody = new ArrayList<>();
        this.restart();
    }

    /**
     * Resets the location and state of the snake, back to 3 square long.
     */
    public void restart() {
        this.snakeTiles.clear();
        this.snakeBody.clear();
        this.directionMoving = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;
        this.row = Constants.SNAKE_INITIAL_COORDINATES[0][0];
        this.col = Constants.SNAKE_INITIAL_COORDINATES[0][1];
        for (int[] coord : Constants.SNAKE_INITIAL_COORDINATES) {
            PicnicTile tile = this.board[coord[0]][coord[1]];
            Rectangle square = this.makeSnakeSquare(coord[0], coord[1]);
            this.snakeBody.add(square);
            tile.addSnake();
            this.snakeTiles.add(tile);
        }
    }

    /**
     * Changes the location the snake is moving in, as long as it doesn't
     * move 180º.
     *
     * @param dir the direction to move in
     */
    public void changeDirection(Direction dir) {
        if (!dir.equals(this.directionMoving.opposite())) {
            this.nextDirection = dir;
        }
    }

    /**
     * Moves the snake one tile in the direction it's currently moving and
     * reacts based on the contents of that tile.
     *
     * @return the tile it moved onto
     */
    public PicnicTile move() {
        this.directionMoving = this.nextDirection;
        this.row = this.directionMoving.newRow(this.row);
        this.col = this.directionMoving.newCol(this.col);

        PicnicTile tile = this.board[this.row][this.col];
        if (tile == null) {
            return null;
        } else {
            switch (tile.getContents()) {
                case SNAKE:
                    return null;
                case EMPTY:
                    this.snakeTiles.get(this.snakeTiles.size() - 1).reset();
                    PicnicTile backTile = this.snakeTiles.remove(this.snakeTiles.size() - 1);
                    backTile.reset();
                    Rectangle rect = this.snakeBody.remove(this.snakeTiles.size() - 1);
                    this.gamePane.getChildren().remove(rect);
                default:
                    this.snakeTiles.add(0, tile);
                    this.snakeBody.add(0, this.makeSnakeSquare(this.row, this.col));
            }
        }
        return tile;
    }

    /**
     * Helper method to create one square for the graphical representation of the snake.
     *
     * @param row the row of the square
     * @param col the column of the square
     * @return the fully setup square
     */
    private Rectangle makeSnakeSquare(int row, int col) {
        Rectangle square = new Rectangle(col * Constants.SQ_WIDTH, row * Constants.SQ_WIDTH,
                Constants.SQ_WIDTH, Constants.SQ_WIDTH);
        square.setFill(Color.BLACK);
        this.gamePane.getChildren().add(square);
        return square;
    }
}
