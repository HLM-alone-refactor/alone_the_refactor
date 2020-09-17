package com.palehorsestudios.alone.gui;

import com.palehorsestudios.alone.player.Player;

public class GameManager {
    private Player player;
    private String winCondition ="dead";


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

}
