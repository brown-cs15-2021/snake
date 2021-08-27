package snake.b;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class Snake {

    private LinkedList<PicnicTile> _snakeTiles;
    private LinkedList<Rectangle> _snakeBody;
    private Board _board;
    private Pane _gamePane;
    private Direction _directionMoving;
    private Direction _nextDirection;
    private int _row;
    private int _col;

    public Snake(Board board, Pane gamePane) {
        _board = board;
        _gamePane = gamePane;
        _snakeTiles = new LinkedList<>();
        _snakeBody = new LinkedList<>();
        this.restart();
    }

    public void restart() {
        _snakeTiles.clear();
        _snakeBody.clear();
        _directionMoving = Direction.RIGHT;
        _nextDirection = Direction.RIGHT;
        _row = Constants.SNAKE_INITIAL_COORDINATES[0][0];
        _col = Constants.SNAKE_INITIAL_COORDINATES[0][1];
        for (int[] coord : Constants.SNAKE_INITIAL_COORDINATES) {
            PicnicTile tile = _board.tileAt(coord[0], coord[1]);
            Rectangle square = this.makeSnakeSquare(coord[0], coord[1]);
            _snakeBody.add(square);
            tile.addSnake();
            _snakeTiles.add(tile);
        }
    }

    public void changeDirection(Direction dir) {
        if (!dir.equals(_directionMoving.opposite())) {
            _nextDirection = dir;
        }
    }

    public PicnicTile move() {
        _directionMoving = _nextDirection;
        _row = _directionMoving.newRow(_row);
        _col = _directionMoving.newCol(_col);

        PicnicTile tile = _board.tileAt(_row, _col);
        if (tile == null) {
            return null;
        } else {
            switch (tile.getContents()) {
                case SNAKE:
                    return null;
                case EMPTY:
                    _snakeTiles.getLast().reset();
                    PicnicTile backTile = _snakeTiles.removeLast();
                    backTile.reset();
                    Rectangle rect = _snakeBody.removeLast();
                    _gamePane.getChildren().remove(rect);
                default:
                    _snakeTiles.addFirst(tile);
                    _snakeBody.addFirst(this.makeSnakeSquare(_row, _col));
            }
        }
        tile.addSnake();
        return tile;
    }

    private Rectangle makeSnakeSquare(int row, int col) {
        Rectangle square = new Rectangle(col * Constants.SQ_WIDTH, row * Constants.SQ_WIDTH,
                Constants.SQ_WIDTH, Constants.SQ_WIDTH);
        square.setFill(Color.BLACK);
        _gamePane.getChildren().add(square);
        return square;
    }
}
