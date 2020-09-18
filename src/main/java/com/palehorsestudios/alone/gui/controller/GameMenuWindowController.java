package com.palehorsestudios.alone.gui.controller;

import com.palehorsestudios.alone.gui.GameManager;
import com.palehorsestudios.alone.gui.ViewFactory;
import com.palehorsestudios.alone.util.Saving;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;


public class GameMenuWindowController extends BaseController {

    @FXML
    private Label menuTitle;

    public GameMenuWindowController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @FXML
    void loadGameAction() {
        System.out.println("Load Game Button clicked");

        Stage stage = (Stage) menuTitle.getScene().getWindow();

        // figure out which file to read from
        File file = viewFactory.getFileChooser().showOpenDialog(stage);

        if (file != null) {
            Saving saving = new Saving();

            gameManager.setPlayer(saving.readPlayer(file));
            viewFactory.showGameWindow(saving.readLog(file));
            viewFactory.closeStage(stage);
        }
    }

    @FXML
    void newGameAction() {
        System.out.println("New game button clicked");
        viewFactory.showIntroWindow();
        Stage stage = (Stage) menuTitle.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

}
