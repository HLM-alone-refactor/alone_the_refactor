package com.palehorsestudios.alone.gui.controller;

import com.palehorsestudios.alone.gui.GameManager;
import com.palehorsestudios.alone.gui.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class GameOverWindowController extends BaseController implements Initializable {

    @FXML
    private MediaView mediaView;

    @FXML
    private Label sceneLabel;

    public GameOverWindowController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playClip();
//        Stage stage = (Stage) sceneLabel.getScene().getWindow();
//        viewFactory.closeStage(stage);
    }

    private void playClip() {
        if (gameManager.getWinCondition().equals("dead")) {
            //System.out.println("game over");

            Media media = new Media(Paths.get("resources/clips/dark_souls.mp4").toUri().toString());
            MediaPlayer player = new MediaPlayer(media);

            mediaView.setMediaPlayer(player);
            player.play();

        }
    }
}

