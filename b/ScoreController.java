package snake.b;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ScoreController {
    private int score;
    private Label scoreLabel;

    public ScoreController(HBox pane) {
        Button quit = new Button("Quit");
        quit.setFocusTraversable(false);
        quit.setOnAction((ActionEvent event) -> Platform.exit());

        this.score = 0;
        this.scoreLabel = new Label(Constants.SCORE_LABEL_TEXT + 0);

        pane.getChildren().addAll(quit, this.scoreLabel);
    }

    public void addToScore(int points) {
        this.score += points;
        this.scoreLabel.setText(Constants.SCORE_LABEL_TEXT + this.score);
    }
}
