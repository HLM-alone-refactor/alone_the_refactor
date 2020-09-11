package com.palehorsestudios.alone.Craft;

import com.palehorsestudios.alone.player.Player;

public interface Crafting<T>{

    public T craft(Player player, String goal);
}
