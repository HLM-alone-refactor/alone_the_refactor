package com.palehorsestudios.alone.nightencounter;

import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.activity.Activity;
import com.palehorsestudios.alone.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RainStormTest {

  NightEncounter rainStorm;
  Player player;

  @Before
  public void setUp() throws Exception {
    rainStorm = RainStormNight.getInstance();
    player = new Player(List.of());
  }

  @Test
  public void rainStormWithBagPositive() {
    player.getItems().add(ItemFactory.getNewInstance("Waterproof Bag"));
    double weightCheck = player.getWeight();
    double moraleCheck = player.getMorale();
    String result = rainStorm.encounter(player);
    assertEquals(moraleCheck +2, player.getMorale(), .01);
//    assertEquals(weightCheck -100, player.getWeight(), .01);
  }
}