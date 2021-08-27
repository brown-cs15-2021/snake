package snake.b;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Game {

    private Snake snake;
    private Board board;
    private Pane gamePane;
    private ScoreController scoreController;

    public Game(Pane gamePane, ScoreController scoreController) {
        this.gamePane = gamePane;
        this.board = new Board(gamePane);
        this.snake = new Snake(board, gamePane);
        this.scoreController = scoreController;

        gamePane.setOnKeyPressed(e -> this.handleKeyPress(e));
        gamePane.setFocusTraversable(true);
        for (int i = 0; i < Constants.NUM_FRUIT; i++) {
            this.spawnFood();
        }

        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.TIMELINE_DURATION), (e -> this.update()));
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    private void spawnFood() {
        PicnicTile tile = board.randomOpenTile();
        Food food;
        switch ((int) (Math.random() * 10)) {
            case 0:
            case 1:
                food = new Food(gamePane, Color.GOLDENROD, 50, tile.getRow(), tile.getCol());
                break;
            case 2:
                food = new Food(gamePane, Color.MINTCREAM, 100, tile.getRow(), tile.getCol());
                break;
            case 3:
            case 4:
            case 5:
                food = new Food(gamePane, Color.BLACK, 25, tile.getRow(), tile.getCol());
                break;
            default:
                food = new Food(gamePane, Color.RED, 10, tile.getRow(), tile.getCol());
                break;
        }

        tile.addFood(food);
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                snake.changeDirection(Direction.UP);
                break;
            case DOWN:
                snake.changeDirection(Direction.DOWN);
                break;
            case LEFT:
                snake.changeDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.changeDirection(Direction.RIGHT);
                break;
            default:
                break;
        }
    }

    private void update() {
        PicnicTile tile = snake.move();
        if (tile == null) {
            //game over
            return;
        }

        if (tile.getContents().equals(TileContents.FOOD)) {
            this.scoreController.addToScore(tile.eatFood());
            this.spawnFood();
        }

        tile.addSnake();
    }
}
