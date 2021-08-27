package snake.b;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food {

    private Circle _circle;
    private int _score;
    private Pane _gamePane;

    public Food(Pane gamePane, Color color, int score, int row, int col) {
        _gamePane = gamePane;
        _circle = new Circle(col * Constants.SQ_WIDTH + Constants.FRUIT_OFFSET,
                row * Constants.SQ_WIDTH + Constants.FRUIT_OFFSET,
                Constants.FRUIT_RADIUS, color);
        _score = score;
        gamePane.getChildren().add(_circle);
    }

    public int eat() {
        _gamePane.getChildren().remove(_circle);
        return _score;
    }
}
