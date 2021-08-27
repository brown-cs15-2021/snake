package snake.a;


import java.util.ArrayList;

public class Snake {

    private ArrayList<PicnicTile> _snakeTiles;
    private Board _board;
    private Direction _directionMoving;
    private Direction _nextDirection;
    private int _row;
    private int _col;

    public Snake(Board board) {
        _board = board;
        _snakeTiles = new ArrayList<>();
        _snakeTiles.clear();
        _directionMoving = Direction.RIGHT;
        _nextDirection = Direction.RIGHT;
        _row = Constants.SNAKE_INITIAL_COORDINATES[0][0];
        _col = Constants.SNAKE_INITIAL_COORDINATES[0][1];
        for (int[] coord : Constants.SNAKE_INITIAL_COORDINATES) {
            PicnicTile tile = _board.tileAt(coord[0], coord[1]);
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
        int newRow = _directionMoving.newRow(_snakeTiles.get(0).getRow());
        int newCol = _directionMoving.newCol(_snakeTiles.get(0).getCol());

        PicnicTile tile = _board.tileAt(newRow, newCol);
        if (tile == null) {
            return null;
        } else {
            switch (tile.getContents()) {
                case SNAKE:
                    return null;
                case EMPTY:
                    PicnicTile backTile = _snakeTiles.remove(_snakeTiles.size() - 1);
                    backTile.reset();
                default:
                    _snakeTiles.add(0, tile);
            }
        }
        return tile;
    }
}
