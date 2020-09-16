package com.palehorsestudios.alone.gui.controller;

import com.palehorsestudios.alone.gui.GameManager;
import com.palehorsestudios.alone.gui.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class GameMenuWindowController extends BaseController {

    @FXML
    private Label menuTitle;

    public GameMenuWindowController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @FXML
    void loadGameAction() {
        System.out.println("Load Game Button clicked");
    }

    @FXML
    void newGameAction() {
        System.out.println("New game button clicked");
        viewFactory.showIntroWindow();
        Stage stage = (Stage) menuTitle.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

}
