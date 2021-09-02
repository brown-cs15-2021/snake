package snake.c;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game {

    private BorderPane root;
    private Rectangle[][] board;
    private ArrayList<Circle> food;
    private Pane gamePane;
    private int score;
    private Label scoreLabel;
    private boolean gameOver;
    private ArrayList<Rectangle> snake;
    private int currDirection;
    private int nextDirection;

    public Game() {
        this.root = new BorderPane();

        Pane gamePane = new Pane();
        this.root.setCenter(gamePane);

        HBox bottomPane = new HBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setSpacing(Constants.SCORE_PANE_SPACING);
        bottomPane.setPrefHeight(Constants.SCORE_PANE_HEIGHT);
        Button quit = new Button("Quit");
        quit.setFocusTraversable(false);
        quit.setOnAction(ActionEvent -> {
            Platform.exit(); });

        bottomPane.getChildren().add(quit);
        this.scoreLabel = new Label(Constants.SCORE_LABEL_TEXT + 0);
        bottomPane.getChildren().add(this.scoreLabel);
        this.root.setBottom(bottomPane);

        this.gamePane = gamePane;
        this.score = 0;
        this.board = new Rectangle[Constants.NUM_ROWS][Constants.NUM_COLS];
        for (int row = 0; row < Constants.NUM_ROWS; row++) {
            for (int col = 0; col < Constants.NUM_COLS; col++) {
                this.board[row][col] = new Rectangle(col * Constants.SQ_WIDTH, row * Constants.SQ_WIDTH,
                        Constants.SQ_WIDTH, Constants.SQ_WIDTH);
                if ((row + col) % 2 == 0) {
                    this.board[row][col].setFill(Color.GREENYELLOW);
                } else {
                    this.board[row][col].setFill(Color.GREEN);
                }
                this.gamePane.getChildren().add(this.board[row][col]);
            }
        }

        this.snake = new ArrayList<>();
        /*
        initialize the snake graphically and logically based on coordinate constant,
        currently 3 squares in the left middle of the board
         */
        for (int[] coord : Constants.SNAKE_INITIAL_COORDINATES) {
            Rectangle square = this.board[coord[0]][coord[1]];
            square.setFill(Color.BLACK);
            this.snake.add(square);
        }

        this.gameOver = false;

        gamePane.setOnKeyPressed(e -> this.handleKeyPress(e));
        gamePane.setFocusTraversable(true);

        this.food = new ArrayList<>();
        this.spawnFood();
        this.spawnFood();
        this.spawnFood();

        this.currDirection = 0;
        this.nextDirection = 0;

        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.TIMELINE_DURATION), (e -> this.update()));
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Gets the root of the program.
     *
     * @return program's root BorderPane
     */
    public BorderPane getRoot() {
        return this.root;
    }

    private void spawnFood() {
        int row;
        int col;
        boolean hasFood;
        do {
            row = (int) (Math.random() * Constants.NUM_ROWS);
            col = (int) (Math.random() * Constants.NUM_COLS);
            hasFood = false;
            for (Circle myFood : this.food) {
                if ((int) myFood.getCenterY() / Constants.SQ_WIDTH == row &&
                        (int) myFood.getCenterX() / Constants.SQ_WIDTH == col) {
                    hasFood = true;
                    break;
                }
            }
        } while (this.board[row][col].getFill().equals(Color.BLACK) || hasFood);

        Color foodColor;
        switch ((int) (Math.random() * 10)) {
            case 0:
            case 1:
                foodColor = Color.GOLDENROD;
                break;
            case 2:
                foodColor = Color.MINTCREAM;
                break;
            case 3:
            case 4:
            case 5:
                foodColor = Color.BLACK;
                break;
            default:
                foodColor = Color.RED;
                break;
        }

        Circle newFood = new Circle(col * Constants.SQ_WIDTH + Constants.FRUIT_OFFSET,
                row * Constants.SQ_WIDTH + Constants.FRUIT_OFFSET, Constants.FRUIT_RADIUS, foodColor);
        this.gamePane.getChildren().add(newFood);
        this.food.add(newFood);
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                if (this.currDirection != 1) {
                    this.nextDirection = 3;
                }
                break;
            case DOWN:
                if (this.currDirection != 3) {
                    this.nextDirection = 1;
                }
                break;
            case LEFT:
                if (this.currDirection != 0) {
                    this.nextDirection = 2;
                }
                break;
            case RIGHT:
                if (this.currDirection != 2) {
                    this.nextDirection = 0;
                }
                break;
            default:
                break;
        }
    }

    private void update() {
        if (this.gameOver) {
            return;
        }

        this.currDirection = this.nextDirection;
        int newRow = (int) this.snake.get(0).getY() / Constants.SQ_WIDTH;
        int newCol = (int) this.snake.get(0).getX() / Constants.SQ_WIDTH;

        switch (this.currDirection) {
            case 0:
                newCol++;
                break;
            case 1:
                newRow++;
                break;
            case 2:
                newCol--;
                break;
            case 3:
                newRow--;
                break;
            default:
                break;
        }

        if (newCol >= Constants.NUM_COLS || newCol < 0 || newRow >= Constants.NUM_ROWS || newRow < 0 || this.board[newRow][newCol].getFill().equals(Color.BLACK)) {
            this.gameOver = true;
            return;
        }

        boolean ateFood = false;
        for (Circle myFood : this.food) {
            if ((int) myFood.getCenterY() / Constants.SQ_WIDTH == newRow && (int) myFood.getCenterX() / Constants.SQ_WIDTH == newCol) {
                if (myFood.getFill().equals(Color.GOLDENROD)) {
                    this.score += 50;
                } else if (myFood.getFill().equals(Color.MINTCREAM)) {
                    this.score += 100;
                } else if (myFood.getFill().equals(Color.BLACK)) {
                    this.score += 25;
                } else if (myFood.getFill().equals(Color.RED)) {
                    this.score += 10;
                }
                this.scoreLabel.setText(Constants.SCORE_LABEL_TEXT + this.score);

                this.food.remove(myFood);
                this.gamePane.getChildren().remove(myFood);
                this.spawnFood();
                ateFood = true;
                break;
            }
        }

        if (!ateFood) {
            Rectangle square = this.snake.remove(this.snake.size() - 1);
            int squareRow = (int) square.getY() / Constants.SQ_WIDTH;
            int squareCol = (int) square.getX() / Constants.SQ_WIDTH;
            if ((squareRow + squareCol) % 2 == 0) {
                square.setFill(Color.GREENYELLOW);
            } else {
                square.setFill(Color.GREEN);
            }
        }

        Rectangle newFrontSquare = this.board[newRow][newCol];
        this.snake.add(0, newFrontSquare);
        newFrontSquare.setFill(Color.BLACK);
    }
}
