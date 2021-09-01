package snake.b;

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
        snakeTiles.clear();
        snakeBody.clear();
        directionMoving = Direction.RIGHT;
        nextDirection = Direction.RIGHT;
        this.row = Constants.SNAKE_INITIAL_COORDINATES[0][0];
        this.col = Constants.SNAKE_INITIAL_COORDINATES[0][1];
        for (int[] coord : Constants.SNAKE_INITIAL_COORDINATES) {
            PicnicTile tile = this.board[coord[0]][coord[1]];
            Rectangle square = this.makeSnakeSquare(coord[0], coord[1]);
            snakeBody.add(square);
            tile.addSnake();
            snakeTiles.add(tile);
        }
    }

    public void changeDirection(Direction dir) {
        if (!dir.equals(directionMoving.opposite())) {
            nextDirection = dir;
        }
    }

    public PicnicTile move() {
        directionMoving = nextDirection;
        row = directionMoving.newRow(row);
        col = directionMoving.newCol(col);

        PicnicTile tile = this.board[row][col];
        if (tile == null) {
            return null;
        } else {
            switch (tile.getContents()) {
                case SNAKE:
                    return null;
                case EMPTY:
                    snakeTiles.getLast().reset();
                    PicnicTile backTile = snakeTiles.removeLast();
                    backTile.reset();
                    Rectangle rect = snakeBody.removeLast();
                    gamePane.getChildren().remove(rect);
                default:
                    snakeTiles.addFirst(tile);
                    snakeBody.addFirst(this.makeSnakeSquare(row, col));
            }
        }
        return tile;
    }

    private Rectangle makeSnakeSquare(int row, int col) {
        Rectangle square = new Rectangle(col * Constants.SQ_WIDTH, row * Constants.SQ_WIDTH,
                Constants.SQ_WIDTH, Constants.SQ_WIDTH);
        square.setFill(Color.BLACK);
        gamePane.getChildren().add(square);
        return square;
    }
}
