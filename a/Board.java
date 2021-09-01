package snake.a;

import javafx.scene.layout.Pane;

/**
 * Represents the game board of squares.
 */
public class Board {

    private BoardSquare[][] tiles;

    /**
     * Constructs the board logically and graphically.
     *
     * @param gamePane the pane on which to add the board
     */
    public Board(Pane gamePane) {
        this.tiles = new BoardSquare[Constants.NUM_ROWS][Constants.NUM_COLS];
        for (int row = 0; row < Constants.NUM_ROWS; row++) {
            for (int col = 0; col < Constants.NUM_COLS; col++) {
                if ((row + col) % 2 == 0) {
                    this.tiles[row][col] = new BoardSquare(gamePane, false, row, col);
                } else {
                    this.tiles[row][col] = new BoardSquare(gamePane, true, row, col);
                }
            }
        }
    }

    /**
     * Retrieves the BoardSquare at particular indices of the board.
     *
     * @param row row of tile to get
     * @param col column of tile to get
     * @return the BoardSquare at the given indices, or null if indices are out of bounds
     */
    public BoardSquare tileAt(int row, int col) {
        if (row >= 0 && row < Constants.NUM_ROWS && col >= 0 && col < Constants.NUM_COLS) {
            return this.tiles[row][col];
        }
        return null;
    }

    /**
     * Gets a random tile that has no contents (no food or snake body)
     *
     * @return a random empty BoardSquare
     */
    public BoardSquare getRandomEmptyTile() {
        int row;
        int col;
        // generate random coordinates until the coordinates yield an empty square
        do {
            row = (int) (Math.random() * Constants.NUM_ROWS);
            col = (int) (Math.random() * Constants.NUM_COLS);
        } while (!this.tiles[row][col].isEmpty());

        return this.tiles[row][col];
    }
}
