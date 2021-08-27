package snake.b;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Constants {

    public static final int NUM_ROWS = 15;
    public static final int NUM_COLS = 15;
    public static final int SQ_WIDTH = 35;
    public static final int SCORE_PANE_HEIGHT = 60;
    public static final int GAME_PANE_HEIGHT = SQ_WIDTH * NUM_ROWS;
    public static final int SCENE_HEIGHT = GAME_PANE_HEIGHT + SCORE_PANE_HEIGHT;
    public static final int SCENE_WIDTH = SQ_WIDTH * NUM_COLS;
    public static final double SCORE_PANE_SPACING = 20;

    public static final int FRUIT_RADIUS = 12;
    public static final int FRUIT_OFFSET = SQ_WIDTH / 2;
    public static final int NUM_FRUIT = 3;

    public static final double TIMELINE_DURATION = 0.4;

    public static final int[][] SNAKE_INITIAL_COORDINATES = {{NUM_ROWS/2, 2}, {NUM_ROWS/2, 1}, {NUM_ROWS/2, 0}};


    public static final int INPUT_LAYER_SIZE = (NUM_COLS + 2) * (NUM_ROWS + 2) + 2;
    public static final int HIDDEN_LAYER_SIZE = 50; // change me!!!
    public static final int OUTPUT_LAYER_SIZE = 4;

    public static final double SNAKE_OPACITY = 0.8;

    // learning parameters
    public static final int SELECTION_THRESHOLD = 131; // min best fitness required for selection
    public static final double SELECTION_RATE = 0.1; // the percentage of the previous generation that passes on genome
    public static double MUTATION_RATE = 0.05;       // the probability that any given weight is mutated between gens
    public static final int GENERATION_SIZE = 50;   // num birds per generation
    public static final int SCORE_INCREMENT = 5;


    public static final String SCORE_LABEL_TEXT = "Score: ";


    public static final String AVG_FITNESS_TEXT = "Avg Fitness Last Gen: ";
    public static final String PREV_BEST_FITNESS_TEXT = "Best Fitness Last Gen: ";
    public static final String ALLTIME_FITNESS_TEXT = "Best Fitness All Time: ";
    public static final String GENERATION_TEXT = "Generation: ";
    public static final String NUM_ALIVE_TEXT = "Alive: ";
    public static final String CURRENT_FITNESS_TEXT = "Current Fitness: ";
    public static final String HIGH_SCORE_TEXT = "High Score: ";
    public static final String CURRENT_SCORE_TEXT = "Current Score: ";
    public static final Paint STATS_COLOR = Color.BLACK;
}
