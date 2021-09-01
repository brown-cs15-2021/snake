package snake.a;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents a food item on the board with an associated score.
 */
public class Food {

    private Circle circle;
    private int score;
    private Pane gamePane;

    /**
     * Constructs food item.
     *
     * @param gamePane pane on which to add food
     * @param color color of food
     * @param score food's score
     * @param row row index of tile on which to add food
     * @param col column index of tile on which to add food
     */
    public Food(Pane gamePane, Color color, int score, int row, int col) {
        this.gamePane = gamePane;
        circle = new Circle(col * Constants.SQ_WIDTH + Constants.FRUIT_OFFSET,
                row * Constants.SQ_WIDTH + Constants.FRUIT_OFFSET,
                Constants.FRUIT_RADIUS, color);
        this.score = score;
        this.gamePane.getChildren().add(circle);
    }

    /**
     * Removes food from the game and returns score.
     *
     * @return score of food eaten
     */
    public int eat() {
        gamePane.getChildren().remove(circle);
        return score;
    }
}
