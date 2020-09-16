package com.palehorsestudios.alone.gui;

import com.palehorsestudios.alone.player.Player;

public class GameManager {
    private Player player;


    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
