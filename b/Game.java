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
    private PicnicTile[][] board;
    private Pane gamePane;
    private ScoreController scoreController;
    private boolean gameOver;

    public Game(Pane gamePane, ScoreController scoreController) {
        this.gamePane = gamePane;
        this.board = new PicnicTile[Constants.NUM_ROWS][Constants.NUM_COLS];
        for (int row = 0; row < Constants.NUM_ROWS; row++) {
            for (int col = 0; col < Constants.NUM_COLS; col++) {
                if ((row + col) % 2 == 0) {
                    this.board[row][col] = new PicnicTile(gamePane, Color.GREENYELLOW, row, col);
                } else {
                    this.board[row][col] = new PicnicTile(gamePane, Color.GREEN, row, col);
                }
            }
        }

        this.snake = new Snake(this.board, gamePane);
        this.scoreController = scoreController;
        this.gameOver = false;

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
        int row;
        int col;
        do {
            row = (int) (Math.random() * Constants.NUM_ROWS);
            col = (int) (Math.random() * Constants.NUM_COLS);
        } while (!this.board[row][col].getContents().equals(TileContents.EMPTY));

        PicnicTile tile = this.board[row][col];
        Color foodColor;
        int foodScore;
        switch ((int) (Math.random() * 10)) {
            case 0:
            case 1:
                foodColor = Color.GOLDENROD;
                foodScore = 50;
                break;
            case 2:
                foodColor = Color.MINTCREAM;
                foodScore = 100;
                break;
            case 3:
            case 4:
            case 5:
                foodColor = Color.BLACK;
                foodScore = 25;
                break;
            default:
                foodColor = Color.RED;
                foodScore = 10;
                break;
        }

        tile.addFood(new Food(this.gamePane, foodColor, foodScore,
                tile.getRow() * Constants.SQ_WIDTH, tile.getCol() * Constants.SQ_WIDTH));
    }

    private void handleKeyPress(KeyEvent event) {
        Direction newDirection = null;
        switch (event.getCode()) {
            case UP:
                newDirection = Direction.UP;
                break;
            case DOWN:
                newDirection = Direction.DOWN;
                break;
            case LEFT:
                newDirection = Direction.LEFT;
                break;
            case RIGHT:
                newDirection = Direction.RIGHT;
                break;
            default:
                break;
        }
        if (newDirection != null && !this.gameOver) {
            this.snake.changeDirection(newDirection);
        }
    }

    private void update() {
        if (this.gameOver) {
            return;
        }

        PicnicTile tile = this.snake.move();
        if (tile == null) {
            this.gameOver = true;
            return;
        }

        if (tile.getContents().equals(TileContents.FOOD)) {
            this.scoreController.addToScore(tile.eatFood());
            this.spawnFood();
        }

        tile.addSnake();
    }
}
