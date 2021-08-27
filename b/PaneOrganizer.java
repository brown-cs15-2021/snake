package snake.b;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PaneOrganizer {

    private BorderPane root;

    public PaneOrganizer() {
        root = new BorderPane();

        Pane gamePane = new Pane();
        root.setCenter(gamePane);

        HBox lowerPane = new HBox();
        lowerPane.setAlignment(Pos.CENTER);
        lowerPane.setSpacing(Constants.SCORE_PANE_SPACING);
        lowerPane.setPrefHeight(Constants.SCORE_PANE_HEIGHT);
        root.setBottom(lowerPane);

        new Game(gamePane, new ScoreController(lowerPane));
    }


    public BorderPane getRoot() {
        return root;
    }
}
