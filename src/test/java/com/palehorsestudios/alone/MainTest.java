package com.palehorsestudios.alone;

import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.activity.*;
import com.palehorsestudios.alone.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.palehorsestudios.alone.Main.parseActivityChoice;
import static org.junit.Assert.assertEquals;

public class MainTest {

    Player player;

    @Before
    public void setUp() {
        player = new Player(ItemFactory.getNewInstances(
                "Axe",
                "Knife",
                "Fishing Line",
                "Fishing Hooks",
                "Wire",
                "Harmonica",
                "Flint and Steel",
                "Pot",
                "First Aid Kit",
                "Cold Weather Gear"
        ));
    }

    @Test
    public void testParseChoiceEat() {
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Moose")), Main.parseChoice("eat moose", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Fish")), Main.parseChoice("eat fish", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Squirrel")), Main.parseChoice("eat squirrel", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Bear")), Main.parseChoice("eat bear", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Rabbit")), Main.parseChoice("eat rabbit", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Porcupine")), Main.parseChoice("eat porcupine", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Bug")), Main.parseChoice("eat bug", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Bug")), Main.parseChoice("eat bugs", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Mushroom")), Main.parseChoice("eat mushroom", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Mushroom")), Main.parseChoice("eat mushrooms", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Berries")), Main.parseChoice("eat berry", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Berries")), Main.parseChoice("eat berries", player));
    }

    @Test
    public void testParseChoiceHunt() {
        assertEquals(new Choice("hunt", player), Main.parseChoice("hunt", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("go hunt", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("go hunting", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("hunt moose", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("hunt squirrel", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("hunt rabbit", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("hunt porcupine", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("kill moose", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("kill squirrel", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("kill rabbit", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("kill porcupine", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("get moose", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("get squirrel", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("get rabbit", player));
        assertEquals(new Choice("hunt", player), Main.parseChoice("get porcupine", player));
    }

    @Test
    public void testParseChoiceForage() {
        assertEquals(new Choice("forage", player), Main.parseChoice("forage", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("go forage", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("go foraging", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("hunt bug", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("hunt bugs", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("hunt mushrooms", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("hunt mushroom", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("hunt berry", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("hunt berries", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("get bug", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("get bugs", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("get mushrooms", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("get mushroom", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("get berry", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("get berries", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("kill bug", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("kill bugs", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("kill mushrooms", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("kill mushroom", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("kill berry", player));
        assertEquals(new Choice("forage", player), Main.parseChoice("kill berries", player));
    }

    @Test
    public void testParseChoiceTrap() {
        assertEquals(new Choice("trap", player), Main.parseChoice("trap", player));
        assertEquals(new Choice("trap", player), Main.parseChoice("trap squirrel", player));
        assertEquals(new Choice("trap", player), Main.parseChoice("go trap", player));
        assertEquals(new Choice("trap", player), Main.parseChoice("trapping", player));
        assertEquals(new Choice("trap", player), Main.parseChoice("go trapping", player));
        assertEquals(new Choice("trap", player), Main.parseChoice("trap moose", player));
        assertEquals(new Choice("trap", player), Main.parseChoice("trap rabbit", player));
        assertEquals(new Choice("trap", player), Main.parseChoice("trap porcupine", player));
    }

    @Test
    public void testParseChoiceFish() {
        assertEquals(new Choice("fish", player), Main.parseChoice("fish", player));
        assertEquals(new Choice("fish", player), Main.parseChoice("fishing", player));
        assertEquals(new Choice("fish", player), Main.parseChoice("go fish", player));
        assertEquals(new Choice("fish", player), Main.parseChoice("go fishing", player));
    }

    @Test
    public void testParseChoiceGet() {
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Line")), Main.parseChoice("get fishing line", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Line")), Main.parseChoice("get fishing lines", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Hooks")), Main.parseChoice("get fishing hooks", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Hooks")), Main.parseChoice("get fishing hook", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Lures")), Main.parseChoice("get fishing lure", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Lures")), Main.parseChoice("get fishing lures", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Knife")), Main.parseChoice("get knife", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flint and Steel")), Main.parseChoice("get flint and steel", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Bow")), Main.parseChoice("get bow", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Arrow")), Main.parseChoice("get arrow", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Arrow")), Main.parseChoice("get arrows", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Family Photo")), Main.parseChoice("get family photo", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Family Photo")), Main.parseChoice("get photo", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Family Photo")), Main.parseChoice("get photograph", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Parachute Chord")), Main.parseChoice("get rope", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Parachute Chord")), Main.parseChoice("get parachute chord", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Parachute Chord")), Main.parseChoice("get cordage", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flare")), Main.parseChoice("get flare", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flare")), Main.parseChoice("get flares", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Boots")), Main.parseChoice("get extra boots", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Boots")), Main.parseChoice("get extra boot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Boots")), Main.parseChoice("get boots", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Boots")), Main.parseChoice("get boot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pants")), Main.parseChoice("get extra pants", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pants")), Main.parseChoice("get pants", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Sleeping Gear")), Main.parseChoice("get sleeping gear", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Sleeping Gear")), Main.parseChoice("get sleeping bag", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Cold Weather Gear")), Main.parseChoice("get cold weather gear", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Cold Weather Gear")), Main.parseChoice("get cold gear", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Tarp")), Main.parseChoice("get tarp", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Matches")), Main.parseChoice("get matches", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Matches")), Main.parseChoice("get match", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("First Aid Kit")), Main.parseChoice("get first aid kit", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("First Aid Kit")), Main.parseChoice("get first aid", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flashlight")), Main.parseChoice("get flashlight", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flashlight")), Main.parseChoice("get light", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Batteries")), Main.parseChoice("get batteries", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Batteries")), Main.parseChoice("get battery", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), Main.parseChoice("get wire", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), Main.parseChoice("get wires", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), Main.parseChoice("get 18 gauge wire", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), Main.parseChoice("get snare", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), Main.parseChoice("get 18 gauge wires", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pot")), Main.parseChoice("get pot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pot")), Main.parseChoice("get cooking pot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Axe")), Main.parseChoice("get axe", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Hatchet")), Main.parseChoice("get hatchet", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Iodine Tablets")), Main.parseChoice("get iodine tablets", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Iodine Tablets")), Main.parseChoice("get iodine", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Iodine Tablets")), Main.parseChoice("get tablets", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol")), Main.parseChoice("get pistol", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol")), Main.parseChoice("get gun", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), Main.parseChoice("get ammunition", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), Main.parseChoice("get cartridges", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), Main.parseChoice("get ammo", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), Main.parseChoice("get rounds", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Shovel")), Main.parseChoice("get shovel", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Harmonica")), Main.parseChoice("get harmonica", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Lighter")), Main.parseChoice("get lighter", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Survival Manual")), Main.parseChoice("get survival manual", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Survival Manual")), Main.parseChoice("get manual", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Journal")), Main.parseChoice("get journal and pen", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Journal")), Main.parseChoice("get journal", player));
    }

    @Test
    public void testParseChoicePut() {
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Line")), Main.parseChoice("put fishing line", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Line")), Main.parseChoice("put fishing lines", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Hooks")), Main.parseChoice("put fishing hooks", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Hooks")), Main.parseChoice("put fishing hook", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Lures")), Main.parseChoice("put fishing lure", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Lures")), Main.parseChoice("put fishing lures", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Knife")), Main.parseChoice("put knife", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flint and Steel")), Main.parseChoice("put flint and steel", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Bow")), Main.parseChoice("put bow", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Arrow")), Main.parseChoice("put arrow", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Arrow")), Main.parseChoice("put arrows", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Family Photo")), Main.parseChoice("put family photo", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Family Photo")), Main.parseChoice("put photo", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Family Photo")), Main.parseChoice("put photograph", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Parachute Chord")), Main.parseChoice("put rope", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Parachute Chord")), Main.parseChoice("put parachute chord", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Parachute Chord")), Main.parseChoice("put cordage", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flare")), Main.parseChoice("put flare", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flare")), Main.parseChoice("put flares", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Boots")), Main.parseChoice("put extra boots", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Boots")), Main.parseChoice("put extra boot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Boots")), Main.parseChoice("put boots", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Boots")), Main.parseChoice("put boot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pants")), Main.parseChoice("put extra pants", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pants")), Main.parseChoice("put pants", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Sleeping Gear")), Main.parseChoice("put sleeping gear", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Sleeping Gear")), Main.parseChoice("put sleeping bag", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Cold Weather Gear")), Main.parseChoice("put cold weather gear", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Cold Weather Gear")), Main.parseChoice("put cold gear", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Tarp")), Main.parseChoice("put tarp", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Matches")), Main.parseChoice("put matches", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Matches")), Main.parseChoice("put match", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("First Aid Kit")), Main.parseChoice("put first aid kit", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("First Aid Kit")), Main.parseChoice("put first aid", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flashlight")), Main.parseChoice("put flashlight", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flashlight")), Main.parseChoice("put light", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Batteries")), Main.parseChoice("put batteries", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Batteries")), Main.parseChoice("put battery", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), Main.parseChoice("put wire", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), Main.parseChoice("put wires", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), Main.parseChoice("put 18 gauge wire", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), Main.parseChoice("put snare", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), Main.parseChoice("put 18 gauge wires", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pot")), Main.parseChoice("put pot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pot")), Main.parseChoice("put cooking pot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Axe")), Main.parseChoice("put axe", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Hatchet")), Main.parseChoice("put hatchet", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Iodine Tablets")), Main.parseChoice("put iodine tablets", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Iodine Tablets")), Main.parseChoice("put iodine", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Iodine Tablets")), Main.parseChoice("put tablets", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol")), Main.parseChoice("put pistol", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol")), Main.parseChoice("put gun", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), Main.parseChoice("put ammunition", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), Main.parseChoice("put cartridges", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), Main.parseChoice("put ammo", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), Main.parseChoice("put rounds", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Shovel")), Main.parseChoice("put shovel", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Harmonica")), Main.parseChoice("put harmonica", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Lighter")), Main.parseChoice("put lighter", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Survival Manual")), Main.parseChoice("put survival manual", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Survival Manual")), Main.parseChoice("put manual", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Journal")), Main.parseChoice("put journal and pen", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Journal")), Main.parseChoice("put journal", player));
    }

    @Test
    public void testParseChoiceImprove() {
        assertEquals(new Choice("improve", player), Main.parseChoice("build shelter", player));
        assertEquals(new Choice("improve", player), Main.parseChoice("make camp", player));
        assertEquals(new Choice("improve", player), Main.parseChoice("work on camp", player));
        assertEquals(new Choice("improve", player), Main.parseChoice("work on shelter", player));
        assertEquals(new Choice("improve", player), Main.parseChoice("improve shelter", player));
        assertEquals(new Choice("improve", player), Main.parseChoice("improve camp", player));
        assertEquals(new Choice("improve", player), Main.parseChoice("build camp", player));
    }

    @Test
    public void testParseChoiceDrink() {
        assertEquals(new Choice("drink", player), Main.parseChoice("drink", player));
        assertEquals(new Choice("drink", player), Main.parseChoice("drink water", player));
        assertEquals(new Choice("drink", player), Main.parseChoice("get a drink", player));
        assertEquals(new Choice("drink", player), Main.parseChoice("take a drink", player));
        assertEquals(new Choice("drink", player), Main.parseChoice("get drink", player));
        assertEquals(new Choice("drink", player), Main.parseChoice("take drink", player));
    }

    @Test
    public void testParseChoiceGather() {
        assertEquals(new Choice("gather", player), Main.parseChoice("gather", player));
        assertEquals(new Choice("gather", player), Main.parseChoice("gather firewood", player));
        assertEquals(new Choice("gather", player), Main.parseChoice("get firewood", player));
        assertEquals(new Choice("gather", player), Main.parseChoice("collect firewood", player));
        assertEquals(new Choice("gather", player), Main.parseChoice("cut firewood", player));
        assertEquals(new Choice("gather", player), Main.parseChoice("gather wood", player));
        assertEquals(new Choice("gather", player), Main.parseChoice("get wood", player));
        assertEquals(new Choice("gather", player), Main.parseChoice("collect wood", player));
        assertEquals(new Choice("gather", player), Main.parseChoice("cut wood", player));
    }

    @Test
    public void testParseChoiceFire() {
        assertEquals(new Choice("fire", player), Main.parseChoice("fire", player));
        assertEquals(new Choice("fire", player), Main.parseChoice("build fire", player));
        assertEquals(new Choice("fire", player), Main.parseChoice("light fire", player));
        assertEquals(new Choice("fire", player), Main.parseChoice("start fire", player));
        assertEquals(new Choice("fire", player), Main.parseChoice("make fire", player));
        assertEquals(new Choice("fire", player), Main.parseChoice("build a fire", player));
        assertEquals(new Choice("fire", player), Main.parseChoice("light a fire", player));
        assertEquals(new Choice("fire", player), Main.parseChoice("start a fire", player));
        assertEquals(new Choice("fire", player), Main.parseChoice("make a fire", player));
    }

    @Test
    public void testParseChoiceWater() {
        assertEquals(new Choice("water", player), Main.parseChoice("water", player));
        assertEquals(new Choice("water", player), Main.parseChoice("get water", player));
        assertEquals(new Choice("water", player), Main.parseChoice("fetch water", player));
        assertEquals(new Choice("water", player), Main.parseChoice("collect water", player));
    }

    @Test
    public void testParseChoiceMorale() {
        assertEquals(new Choice("morale", player), Main.parseChoice("morale", player));
        assertEquals(new Choice("morale", player), Main.parseChoice("improve morale", player));
        assertEquals(new Choice("morale", player), Main.parseChoice("boost morale", player));
        assertEquals(new Choice("morale", player), Main.parseChoice("photo", player));
        assertEquals(new Choice("morale", player), Main.parseChoice("look at photo", player));
        assertEquals(new Choice("morale", player), Main.parseChoice("look at family photo", player));
        assertEquals(new Choice("morale", player), Main.parseChoice("harmonica", player));
        assertEquals(new Choice("morale", player), Main.parseChoice("play harmonica", player));
        assertEquals(new Choice("morale", player), Main.parseChoice("journal", player));
        assertEquals(new Choice("morale", player), Main.parseChoice("write in journal", player));
    }

    @Test
    public void testParseChoiceRest() {
        assertEquals(new Choice("rest", player), Main.parseChoice("rest", player));
        assertEquals(new Choice("rest", player), Main.parseChoice("break", player));
        assertEquals(new Choice("rest", player), Main.parseChoice("take rest", player));
        assertEquals(new Choice("rest", player), Main.parseChoice("take break", player));
        assertEquals(new Choice("rest", player), Main.parseChoice("take a rest", player));
        assertEquals(new Choice("rest", player), Main.parseChoice("take a break", player));
        assertEquals(new Choice("rest", player), Main.parseChoice("relax", player));
        assertEquals(new Choice("rest", player), Main.parseChoice("nap", player));
        assertEquals(new Choice("rest", player), Main.parseChoice("take nap", player));
        assertEquals(new Choice("rest", player), Main.parseChoice("take a nap", player));
    }

    @Test
    public void testParseActivityChoiceGet() {
        for (Item item : ItemFactory.getAllItems()) {
            assertEquals(GetItemActivity.getInstance(), parseActivityChoice(new Choice("get", player, item)));
        }
    }

    @Test
    public void testParseActivityChoicePut() {
        for (Item item : ItemFactory.getAllItems()) {
            assertEquals(PutItemActivity.getInstance(), parseActivityChoice(new Choice("put", player, item)));
        }
    }

    @Test
    public void testParseActivityChoiceEat() {
        for (Food food : FoodFactory.getAllFood()) {
            assertEquals(EatActivity.getInstance(), parseActivityChoice(new Choice("eat", player, food)));
        }
    }

    @Test
    public void testParseActivityChoiceDrink() {
        assertEquals(DrinkWaterActivity.getInstance(), parseActivityChoice(new Choice("drink", player)));
    }

    @Test
    public void testParseActivityChoiceFish() {
        assertEquals(FishActivity.getInstance(), parseActivityChoice(new Choice("fish", player)));
    }

    @Test
    public void testParseActivityChoiceHunt() {
        assertEquals(HuntActivity.getInstance(), parseActivityChoice(new Choice("hunt", player)));
    }

    @Test
    public void testParseActivityChoiceTrap() {
        assertEquals(TrapActivity.getInstance(), parseActivityChoice(new Choice("trap", player)));
    }

    @Test
    public void testParseActivityChoiceForage() {
        assertEquals(ForageActivity.getInstance(), parseActivityChoice(new Choice("forage", player)));
    }

    @Test
    public void testParseActivityChoiceImprove() {
        assertEquals(ImproveShelterActivity.getInstance(), parseActivityChoice(new Choice("improve", player)));
    }

    @Test
    public void testParseActivityChoiceGather() {
        assertEquals(GatherFirewoodActivity.getInstance(), parseActivityChoice(new Choice("gather", player)));
    }

    @Test
    public void testParseActivityChoiceFire() {
        assertEquals(BuildFireActivity.getInstance(), parseActivityChoice(new Choice("fire", player)));
    }

    @Test
    public void testParseActivityChoiceWater() {
        assertEquals(GetWaterActivity.getInstance(), parseActivityChoice(new Choice("water", player)));
    }

    @Test
    public void testParseActivityChoiceMorale() {
        assertEquals(BoostMoraleActivity.getInstance(), parseActivityChoice(new Choice("morale", player)));
    }

    @Test
    public void testParseActivityChoiceRest() {
        assertEquals(RestActivity.getInstance(), parseActivityChoice(new Choice("rest", player)));
    }

    @Test
    public void temp() {
        System.out.println(HelperMethods.titleize("there Is nothing Here"));
    }
}
