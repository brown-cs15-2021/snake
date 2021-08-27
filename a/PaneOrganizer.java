package snake.a;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PaneOrganizer {

    private BorderPane _root;

    public PaneOrganizer() {
        _root = new BorderPane();

        Pane gamePane = new Pane();
        _root.setCenter(gamePane);

        HBox lowerPane = new HBox();
        lowerPane.setAlignment(Pos.CENTER);
        lowerPane.setSpacing(Constants.SCORE_PANE_SPACING);
        lowerPane.setPrefHeight(Constants.SCORE_PANE_HEIGHT);
        Button quit = new Button("Quit");
        quit.setFocusTraversable(false);
        quit.setOnAction(ActionEvent -> {
            Platform.exit(); });

        Label score = new Label(Constants.SCORE_LABEL_TEXT + 0);
        lowerPane.getChildren().addAll(quit, score);
        _root.setBottom(lowerPane);

        new Game(gamePane, score);
    }


    public BorderPane getRoot() {
        return _root;
    }
}
