package snake.StudentA;

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

/**
 * Handles the high-level logic of the program, by running a timeline that updates
 * the snake and board state at each tick of the timeline and by updating the
 * snake direction of key input.
 */
public class Game {

    private Snake snake;
    private Board board;
    private int score;
    private Label scoreLabel;
    private Timeline timeline;
    private Pane gamePane;

    /**
     * Sets up the game by registering the KeyEvent handler on the gamePane and starting
     * the timeline that triggers the game update.
     *
     * @param gamePane the pane on which to add game elements
     * @param scoreLabel a label that tracks score
     */
    public Game(Pane gamePane, Label scoreLabel) {
        this.gamePane = gamePane;
        this.board = new Board(this.gamePane);
        this.snake = new Snake(this.board);
        this.scoreLabel = scoreLabel;
        this.score = 0;

        for (int i = 0; i < Constants.NUM_FRUIT; i++) {
            this.spawnFood();
        }

        this.gamePane.setOnKeyPressed((KeyEvent event) -> this.handleKeyInput(event.getCode()));
        this.gamePane.setFocusTraversable(true);

        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.TIMELINE_DURATION),
                (ActionEvent event) -> this.update());
        this.timeline = new Timeline(kf);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    /**
     * Spawns a random new food item on a random tile that is empty.
     */
    private void spawnFood() {
        BoardSquare tile = this.board.getRandomEmptyTile();
        Food food;
        switch ((int) (Math.random() * 10)) {
            case 0:
            case 1:
                food = new Food(this.gamePane, Color.GOLDENROD, Constants.FOOD_3_SCORE, tile.getRow(), tile.getCol());
                break;
            case 2:
                food = new Food(this.gamePane, Color.MINTCREAM, Constants.FOOD_4_SCORE, tile.getRow(), tile.getCol());
                break;
            case 3:
            case 4:
            case 5:
                food = new Food(this.gamePane, Color.BLACK, Constants.FOOD_2_SCORE, tile.getRow(), tile.getCol());
                break;
            default:
                food = new Food(this.gamePane, Color.RED, Constants.FOOD_1_SCORE, tile.getRow(), tile.getCol());
                break;
        }

        tile.addFood(food);
    }


    /**
     * Updates the state of the game, by moving the snake, updating the score,
     * and spawning new food when necessary.
     */
    private void update() {
        SnakeMoveResult result = this.snake.move();

        if (result.equals(SnakeMoveResult.GAME_OVER)) {
            this.timeline.stop();
        }

        if (result.getScoreIncrease() > 0) {
            this.score += result.getScoreIncrease();
            this.scoreLabel.setText(Constants.SCORE_LABEL_TEXT + this.score);
            this.spawnFood();
        }
    }

    /**
     * Handles key input by changing direction of snake on up, down, left, and
     * right arrow keys.
     *
     * @param code code of the key pressed
     */
    private void handleKeyInput(KeyCode code) {
        switch (code) {
            case UP:
                this.snake.changeDirection(Direction.UP);
                break;
            case DOWN:
                this.snake.changeDirection(Direction.DOWN);
                break;
            case LEFT:
                this.snake.changeDirection(Direction.LEFT);
                break;
            case RIGHT:
                this.snake.changeDirection(Direction.RIGHT);
                break;
            default:
                break;
        }
    }
}
