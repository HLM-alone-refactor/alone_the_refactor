package com.palehorsestudios.alone.gui.controller;

import com.palehorsestudios.alone.gui.GameManager;
import com.palehorsestudios.alone.gui.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GameIntroWindowController extends BaseController implements Initializable {

    @FXML
    private Button startGame;
    @FXML
    private TextArea intro;

    // Constructor
    public GameIntroWindowController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }


    @FXML
    public void startGameAction() {
        //System.out.println("startGameAction");
        viewFactory.showSelectItemsWindow();
        Stage stage = (Stage) intro.getScene().getWindow();
//        System.out.println("test");
        viewFactory.closeStage(stage);
    }

    public TextArea getIntro() {
        return intro;
    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
            //System.out.println("Accessing intronarrative.txt");
            File file = new File("resources/intronarrative.txt");
            try {
                String intro = Files.lines(file.toPath()).collect(Collectors.joining("\n"));
                this.getIntro().appendText(intro);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(
                        "Whoops! We seemed to have misplaced the next segment of the story. We're working on it!");
                return;
            }
            intro.setStyle("-fx-font-size: 14px;");
            // make sure scroll bar starts at top..
            intro.positionCaret(0);

    }

}
