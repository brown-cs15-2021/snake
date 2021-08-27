package snake.a;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Game {

    private Snake _snake;
    private Board _board;
    private int _score;
    private Label _scoreLabel;
    private Timeline _timeline;
    private Pane _gamePane;

    public Game(Pane gamePane, Label scoreLabel) {
        _gamePane = gamePane;
        _board = new Board(gamePane);
        _snake = new Snake(_board);
        _scoreLabel = scoreLabel;

        _score = 0;
        gamePane.setOnKeyPressed((KeyEvent event) -> this.handleKeyInput(event.getCode()));
        gamePane.setFocusTraversable(true);
        for (int i = 0; i < Constants.NUM_FRUIT; i++) {
            this.spawnFood();
        }
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.TIMELINE_DURATION), (ActionEvent event) -> this.update());
        _timeline = new Timeline(kf);
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    private void spawnFood() {
        PicnicTile tile = _board.getRandomOpenTile();
        Food food;
        switch ((int) (Math.random() * 10)) {
            case 0:
            case 1:
                food = new Food(_gamePane, Color.GOLDENROD, 50, tile.getRow(), tile.getCol());
                break;
            case 2:
                food = new Food(_gamePane, Color.MINTCREAM, 100, tile.getRow(), tile.getCol());
                break;
            case 3:
            case 4:
            case 5:
                food = new Food(_gamePane, Color.BLACK, 25, tile.getRow(), tile.getCol());
                break;
            default:
                food = new Food(_gamePane, Color.RED, 10, tile.getRow(), tile.getCol());
                break;
        }

        tile.addFood(food);
    }


    private void update() {
        PicnicTile tile = _snake.move();
        if (tile == null) {
            //game over
            return;
        }

        if (tile.getContents().equals(TileContents.FOOD)) {
            _score += tile.eatFood();
            this.spawnFood();
        }

        tile.addSnake();

        _scoreLabel.setText(Constants.SCORE_LABEL_TEXT + _score);
    }

    private void handleKeyInput(KeyCode code) {
        switch (code) {
            case UP:
                _snake.changeDirection(Direction.UP);
                break;
            case DOWN:
                _snake.changeDirection(Direction.DOWN);
                break;
            case LEFT:
                _snake.changeDirection(Direction.LEFT);
                break;
            case RIGHT:
                _snake.changeDirection(Direction.RIGHT);
                break;
            default:
                break;
        }
    }
}
