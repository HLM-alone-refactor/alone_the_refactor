package com.palehorsestudios.alone.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(new GameManager());
        viewFactory.showGameMenuWindow();
    }
}
