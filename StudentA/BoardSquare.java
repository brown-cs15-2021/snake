package snake.StudentA;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents one square of the board and tracks its contents (food, snake, or empty).
 */
public class BoardSquare {

    private Rectangle rect;
    private Food food;
    private Color originalColor;
    private int row;
    private int col;

    /**
     * Constructs the empty board square.
     *
     * @param gamePane the pane on which to add the square
     * @param odd flag to indicate color of the square as even or odd
     * @param row row index of the square
     * @param col column index of the square
     */
    public BoardSquare(Pane gamePane, boolean odd, int row, int col) {
        this.row = row;
        this.col = col;
        this.rect = new Rectangle(col * Constants.SQ_WIDTH, row * Constants.SQ_WIDTH,
                Constants.SQ_WIDTH, Constants.SQ_WIDTH);
        this.food = null;

        if (odd) {
            this.originalColor = Constants.ODD_SQUARE_COLOR;
        } else {
            this.originalColor = Constants.EVEN_SQUARE_COLOR;
        }

        this.reset();
        gamePane.getChildren().add(this.rect);
    }

    /**
     * Resets the square to be empty.
     */
    public void reset() {
        this.rect.setFill(this.originalColor);
    }

    /**
     * Sets content of the square to hold snake body. If the square held
     * food previously, the food is eaten and the food's score is returned.
     *
     * @return score of food eaten from the square; if no food, 0 is returned
     */
    public int addSnake() {
        this.rect.setFill(Color.BLACK);

        if (this.food != null) {
            int score = this.food.eat();
            this.food = null;
            return score;

        }
        return 0;
    }

    /**
     * Sets content of the square to hold a food item.
     *
     * @param food food item for the square to hold
     */
    public void addFood(Food food) {
        this.food = food;
    }

    /**
     * Indicates if square is empty or not (otherwise has snake or food).
     *
     * @return boolean to indicate square is empty (true) or not (false)
     */
    public boolean isEmpty() {
        return this.rect.getFill().equals(this.originalColor) && this.food == null;
    }

    /**
     * Gets row index of square.
     *
     * @return square's row index
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets column index of square.
     *
     * @return square's column index
     */
    public int getCol() {
        return this.col;
    }
}
