package snake.StudentB;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food {

    private Circle circle;
    private int score;
    private Pane gamePane;

    public Food(Pane gamePane, Color color, int score, int y, int x) {
        this.gamePane = gamePane;
        this.circle = new Circle(x + Constants.FRUIT_OFFSET, y + Constants.FRUIT_OFFSET,
                Constants.FRUIT_RADIUS, color);
        this.score = score;
        this.gamePane.getChildren().add(this.circle);
    }

    public int eat() {
        this.gamePane.getChildren().remove(this.circle);
        return this.score;
    }
}
