package bibot;

import bibot.gui.MainWindow;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Bibot bibot;

    private static final String DEFAULT_FILE_PATH = "./data/tasks.txt";
    
    private static final int MINIMUM_HEIGHT = 220;
    private static final int MINIMUM_WIDTH = 417;

    public Main() {
        this.bibot = new Bibot(DEFAULT_FILE_PATH);
    }

    public Main(String filePath) {
        this.bibot = new Bibot(filePath);
    }

    private void setMinimumSize(Stage stage) {
        stage.setMinHeight(MINIMUM_HEIGHT);
        stage.setMinWidth(MINIMUM_WIDTH);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane mainLayout = fxmlLoader.load();
            Scene scene = new Scene(mainLayout);
            stage.setScene(scene);
            setMinimumSize(stage);
            fxmlLoader.<MainWindow>getController().setup(this.bibot);
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
