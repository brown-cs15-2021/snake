package snake.StudentB;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class Snake {

    private LinkedList<PicnicTile> snakeTiles;
    private LinkedList<Rectangle> snakeBody;
    private PicnicTile[][] board;
    private Pane gamePane;
    private Direction directionMoving;
    private Direction nextDirection;
    private int row;
    private int col;

    public Snake(PicnicTile[][] board, Pane gamePane) {
        this.board = board;
        this.gamePane = gamePane;
        this.snakeTiles = new LinkedList<>();
        this.snakeBody = new LinkedList<>();
        this.restart();
    }

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

    public void changeDirection(Direction dir) {
        if (!dir.equals(this.directionMoving.opposite())) {
            this.nextDirection = dir;
        }
    }

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
                    this.snakeTiles.getLast().reset();
                    PicnicTile backTile = this.snakeTiles.removeLast();
                    backTile.reset();
                    Rectangle rect = this.snakeBody.removeLast();
                    this.gamePane.getChildren().remove(rect);
                default:
                    this.snakeTiles.addFirst(tile);
                    this.snakeBody.addFirst(this.makeSnakeSquare(this.row, this.col));
            }
        }
        return tile;
    }

    private Rectangle makeSnakeSquare(int row, int col) {
        Rectangle square = new Rectangle(col * Constants.SQ_WIDTH, row * Constants.SQ_WIDTH,
                Constants.SQ_WIDTH, Constants.SQ_WIDTH);
        square.setFill(Color.BLACK);
        this.gamePane.getChildren().add(square);
        return square;
    }
}
