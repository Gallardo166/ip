package bibot.gui;

import bibot.Bibot;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bibot bibot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Bibot.jpg"));

    private static final String INITIAL_GREETING = "Hello, I'm Bibot!\nWhat can I do for you?";
    private static final String INITIAL_COMMAND = "remind";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setup(Bibot bibot) {
        setBibot(bibot);
        displayGreeting();
        displayReminders();
    }

    private void setBibot(Bibot bibot) {
        this.bibot = bibot;
    }

    private void displayGreeting() {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(INITIAL_GREETING, dukeImage)
        );
    }

    private void displayReminders() {
        String reminderResponse = bibot.getResponse(INITIAL_COMMAND);

        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(reminderResponse, dukeImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String response = bibot.getResponse(userInput.getText());
        displayOutput(response);
        userInput.clear();

        if (bibot.isFinished()) {
            closeWindowWithDelay();
        }
    }

    private void closeWindowWithDelay() {
        //@@author Gallardo166-reused
        //Reused from https://github.com/NUS-CS2103-AY2526-S2/forum/issues/160#issuecomment-3857719372
        // with minor modifications
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
        delay.setOnFinished((event) -> Platform.exit());
        delay.play();
    }

    private void displayOutput(String response) {
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userInput.getText(), userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
    }
}
