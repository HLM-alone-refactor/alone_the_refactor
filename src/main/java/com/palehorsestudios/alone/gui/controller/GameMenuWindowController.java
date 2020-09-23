package com.palehorsestudios.alone.gui.controller;

import com.palehorsestudios.alone.gui.GameManager;
import com.palehorsestudios.alone.gui.ViewFactory;
import com.palehorsestudios.alone.util.Saving;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;


public class GameMenuWindowController extends BaseController implements Initializable {

    @FXML
    private Label menuTitle;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button buttonNew;

    @FXML
    private Button buttonLoad;

    public GameMenuWindowController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @FXML
    void loadGameAction() {
        //System.out.println("Load Game Button clicked");

        Stage stage = (Stage) menuTitle.getScene().getWindow();

        // figure out which file to read from
        File file = viewFactory.getFileChooser().showOpenDialog(stage);

        if (file != null) {
            Saving saving = new Saving();

            gameManager.setDefaultGameFile(file);
            gameManager.setPlayer(saving.readPlayer(file));
            viewFactory.showGameWindow(saving.readLog(file));
            viewFactory.closeStage(stage);
        }
    }

    @FXML
    void newGameAction() {
        //System.out.println("New game button clicked");
        viewFactory.showIntroWindow();
        Stage stage = (Stage) menuTitle.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateStyles();
    }

    private void updateStyles() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image(Paths.get("resources/images/angry_bear.jpg").toUri().toString()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0,1.0,true,true,false,false));
        pane.setBackground(new Background(backgroundImage));

        String btnStyle = "     -fx-background-color:\n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 16px;\n" +
                "    -fx-padding: 10 20 10 20;";
        buttonNew.setStyle(btnStyle);
        buttonLoad.setStyle(btnStyle);


    }
}
