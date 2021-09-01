package snake.a;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the App class, where the program starts. It sets the scene with the
 * PaneOrganizer's root pane.
 */
public class App extends Application {

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        PaneOrganizer po = new PaneOrganizer();
        Scene scene = new Scene(po.getRoot(), Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake!");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
