package com.palehorsestudios.alone.dayencounter;

import com.palehorsestudios.alone.player.Player;
import com.palehorsestudios.alone.util.Encounter;

public abstract class DayEncounter implements Encounter {
    public abstract String encounter(Player player);
}
