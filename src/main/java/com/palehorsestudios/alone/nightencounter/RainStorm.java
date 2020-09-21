package com.palehorsestudios.alone.nightencounter;

import com.palehorsestudios.alone.gui.model.PlayerStatus;
import com.palehorsestudios.alone.gui.model.Status;
import com.palehorsestudios.alone.player.Player;

public class RainStorm extends NightEncounter {
    private static NightEncounter encounter;

    private RainStorm() {
    }

    public static NightEncounter getInstance() {
        if (encounter == null) {
            encounter = new RainStorm();
        }
        return encounter;
    }

    @Override
    public PlayerStatus encounter(Player player) {
        return new PlayerStatus(Status.HARD_RAIN,"It rained last night.");
    }
}
