package snake.b;

import javafx.scene.layout.Pane;

public class Board {

    private PicnicTile[][] _tiles;

    public Board(Pane gamePane) {
        _tiles = new PicnicTile[Constants.NUM_ROWS][Constants.NUM_COLS];
        for (int row = 0; row < Constants.NUM_ROWS; row++) {
            for (int col = 0; col < Constants.NUM_COLS; col++) {
                if ((row + col) % 2 == 0) {
                    _tiles[row][col] = new PicnicTile(gamePane, false, row, col);
                } else {
                    _tiles[row][col] = new PicnicTile(gamePane, true, row, col);
                }
            }
        }
    }

    public PicnicTile tileAt(int row, int col) {
        if (row >= 0 && row < Constants.NUM_ROWS && col >= 0 && col < Constants.NUM_COLS) {
            return _tiles[row][col];
        }
        return null;
    }

    public PicnicTile randomOpenTile() {
        int row;
        int col;
        do {
            row = (int) (Math.random() * Constants.NUM_ROWS);
            col = (int) (Math.random() * Constants.NUM_COLS);
        } while (!_tiles[row][col].getContents().equals(TileContents.EMPTY));
        // when the game is over this becomes an infinite loop

        return _tiles[row][col];
    }
}
