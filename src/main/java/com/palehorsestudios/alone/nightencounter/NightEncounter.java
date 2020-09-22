package com.palehorsestudios.alone.nightencounter;

import com.palehorsestudios.alone.player.Player;
import com.palehorsestudios.alone.util.Encounter;

public abstract class NightEncounter implements Encounter {
    public abstract String encounter(Player player);
}
