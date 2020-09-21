package com.palehorsestudios.alone.nightencounter;

import com.palehorsestudios.alone.gui.model.PlayerStatus;
import com.palehorsestudios.alone.player.Player;

public abstract class NightEncounter {
    public abstract PlayerStatus encounter(Player player);
}
