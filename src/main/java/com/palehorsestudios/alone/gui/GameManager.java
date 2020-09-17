package com.palehorsestudios.alone.gui;

import com.palehorsestudios.alone.gui.controller.GameWindowController;
import com.palehorsestudios.alone.player.Player;

import java.io.File;
import java.nio.file.Paths;

public class GameManager {
    private Player player;
    private String winCondition ="dead";
    private File defaultGameFile;

    public GameManager() {
        defaultGameFile = Paths.get("resources", "games", "alone.ser").toFile();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public String getWinCondition() {
        return winCondition;
    }

    public void setWinCondition(String winCondition) {
        this.winCondition = winCondition;
    }

    public File getDefaultGameFile() {
        return defaultGameFile;
    }

    public void setDefaultGameFile(File defaultGameFile) {
        this.defaultGameFile =defaultGameFile;
    }

}
