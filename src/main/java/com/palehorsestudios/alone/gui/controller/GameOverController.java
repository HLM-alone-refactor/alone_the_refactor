package com.palehorsestudios.alone.gui.controller;

import com.palehorsestudios.alone.gui.GameManager;
import com.palehorsestudios.alone.gui.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class GameOverController extends BaseController {

    @FXML
    private ImageView gameOverImage;

    public GameOverController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

}

