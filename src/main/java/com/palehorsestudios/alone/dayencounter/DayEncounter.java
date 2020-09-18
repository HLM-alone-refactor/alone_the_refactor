package com.palehorsestudios.alone.dayencounter;

import com.palehorsestudios.alone.gui.model.PlayerStatus;
import com.palehorsestudios.alone.player.Player;

public abstract class DayEncounter {
    public abstract PlayerStatus encounter(Player player);
}
