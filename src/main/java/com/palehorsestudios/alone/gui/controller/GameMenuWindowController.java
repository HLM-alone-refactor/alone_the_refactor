package com.palehorsestudios.alone.gui.controller;

import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.gui.GameManager;
import com.palehorsestudios.alone.gui.ViewFactory;
import com.palehorsestudios.alone.player.Player;
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
        Saving saving = new Saving();

        // figure out file
        File file = new File("resources/games/temp.ser");

        gameManager.setPlayer(saving.readPlayer(file));
        viewFactory.showGameWindow(saving.readLog(file));
    }

    @FXML
    void newGameAction() {
        System.out.println("New game button clicked");
        viewFactory.showIntroWindow();
        Stage stage = (Stage) menuTitle.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

}
