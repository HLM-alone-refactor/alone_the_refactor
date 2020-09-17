package com.palehorsestudios.alone.gui;

import com.palehorsestudios.alone.gui.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {

    private GameManager gameManager;
    private ArrayList<Stage> activeStages;

    public ViewFactory(GameManager gameManager) {
        this.gameManager = gameManager;
        this.activeStages = new ArrayList<>();
    }

    public void showGameMenuWindow() {
        System.out.println("show game menu window called");

        BaseController controller = new GameMenuWindowController(gameManager,this, "view/gameMenu.fxml");
        initializeStage(controller);
    }

    public void showAboutWindow() {
        System.out.println("show game menu window called");

        BaseController controller = new AboutWindowController(gameManager,this, "view/about.fxml");
        initializeStage(controller);
    }

    public void showIntroWindow() {
        System.out.println("show Intro window called");

        BaseController controller = new IntroWindowController(gameManager,this, "view/intro.fxml");
        initializeStage(controller);
    }

    public void showSelectItemsWindow() {
        System.out.println("Show select items window called");

        BaseController controller = new ItemSelectionWindowController(gameManager,this, "view/testSelectItems.fxml");
        initializeStage(controller);
    }

    public void showGameWindow() {
        System.out.println("how game window called");

        BaseController controller = new GameWindowController(gameManager,this, "view/game.fxml");
        initializeStage(controller);
    }

    public void showGameOver() {
        System.out.println("Show game over window");

        BaseController controller = new GameOverWindowController(gameManager, this, "view/gameOver.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public void closeStage(Stage stageToClose) {
        stageToClose.close();
        activeStages.remove(stageToClose);
    }

    public ArrayList<Stage> getActiveStages() {
        return activeStages;
    }

}
