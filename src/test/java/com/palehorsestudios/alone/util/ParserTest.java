package com.palehorsestudios.alone.util;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.activity.*;
import com.palehorsestudios.alone.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    Parser parser;
    Player player;

    @Before
    public void setUp() {
        parser = new Parser();
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
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Moose")), parser.parseChoice("eat moose", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Fish")), parser.parseChoice("eat fish", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Squirrel")), parser.parseChoice("eat squirrel", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Bear")), parser.parseChoice("eat bear", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Rabbit")), parser.parseChoice("eat rabbit", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Porcupine")), parser.parseChoice("eat porcupine", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Bug")), parser.parseChoice("eat bug", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Bug")), parser.parseChoice("eat bugs", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Mushroom")), parser.parseChoice("eat mushroom", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Mushroom")), parser.parseChoice("eat mushrooms", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Berries")), parser.parseChoice("eat berry", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Berries")), parser.parseChoice("eat berries", player));
    }

    @Test
    public void testParseChoiceHunt() {
        assertEquals(new Choice("hunt", player), parser.parseChoice("hunt", player));
        assertEquals(new Choice("hunt", player), parser.parseChoice("go hunt", player));
        assertEquals(new Choice("hunt", player), parser.parseChoice("go hunting", player));
        assertEquals(new Choice("hunt", player), parser.parseChoice("hunt moose", player));
        assertEquals(new Choice("hunt", player), parser.parseChoice("hunt squirrel", player));
        assertEquals(new Choice("hunt", player), parser.parseChoice("hunt rabbit", player));
        assertEquals(new Choice("hunt", player), parser.parseChoice("hunt porcupine", player));
        assertEquals(new Choice("hunt", player), parser.parseChoice("kill moose", player));
        assertEquals(new Choice("hunt", player), parser.parseChoice("kill squirrel", player));
        assertEquals(new Choice("hunt", player), parser.parseChoice("kill rabbit", player));
        assertEquals(new Choice("hunt", player), parser.parseChoice("kill porcupine", player));
    }

    @Test
    public void testParseChoiceForage() {
        assertEquals(new Choice("forage", player), parser.parseChoice("forage", player));
        assertEquals(new Choice("forage", player), parser.parseChoice("go forage", player));
        assertEquals(new Choice("forage", player), parser.parseChoice("go foraging", player));
    }

    @Test
    public void testParseChoiceTrap() {
        assertEquals(new Choice("trap", player), parser.parseChoice("trap", player));
        assertEquals(new Choice("trap", player), parser.parseChoice("trap squirrel", player));
        assertEquals(new Choice("trap", player), parser.parseChoice("go trap", player));
        assertEquals(new Choice("trap", player), parser.parseChoice("trapping", player));
        assertEquals(new Choice("trap", player), parser.parseChoice("go trapping", player));
        assertEquals(new Choice("trap", player), parser.parseChoice("trap moose", player));
        assertEquals(new Choice("trap", player), parser.parseChoice("trap rabbit", player));
        assertEquals(new Choice("trap", player), parser.parseChoice("trap porcupine", player));
    }

    @Test
    public void testParseChoiceFish() {
        assertEquals(new Choice("fish", player), parser.parseChoice("fish", player));
        assertEquals(new Choice("fish", player), parser.parseChoice("fishing", player));
        assertEquals(new Choice("fish", player), parser.parseChoice("go fish", player));
        assertEquals(new Choice("fish", player), parser.parseChoice("go fishing", player));
        assertEquals(new Choice("fish", player), parser.parseChoice("get fish", player));
    }

    @Test
    public void testParseChoiceGet() {
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Line")), parser.parseChoice("get fishing line", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Line")), parser.parseChoice("get fishing lines", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Hooks")), parser.parseChoice("get fishing hooks", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Hooks")), parser.parseChoice("get fishing hook", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Lures")), parser.parseChoice("get fishing lure", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Lures")), parser.parseChoice("get fishing lures", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Knife")), parser.parseChoice("get knife", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flint and Steel")), parser.parseChoice("get flint and steel", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Bow")), parser.parseChoice("get bow", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Arrow")), parser.parseChoice("get arrow", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Arrow")), parser.parseChoice("get arrows", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Family Photo")), parser.parseChoice("get family photo", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Family Photo")), parser.parseChoice("get photo", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Family Photo")), parser.parseChoice("get photograph", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Parachute Chord")), parser.parseChoice("get rope", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Parachute Chord")), parser.parseChoice("get parachute chord", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Parachute Chord")), parser.parseChoice("get cordage", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flare")), parser.parseChoice("get flare", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flare")), parser.parseChoice("get flares", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Boots")), parser.parseChoice("get boots", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Boots")), parser.parseChoice("get boot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pants")), parser.parseChoice("get pants", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Sleeping Gear")), parser.parseChoice("get sleeping gear", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Sleeping Gear")), parser.parseChoice("get sleeping bag", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Cold Weather Gear")), parser.parseChoice("get cold weather gear", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Cold Weather Gear")), parser.parseChoice("get cold gear", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Tarp")), parser.parseChoice("get tarp", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Matches")), parser.parseChoice("get matches", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Matches")), parser.parseChoice("get match", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("First Aid Kit")), parser.parseChoice("get first aid kit", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("First Aid Kit")), parser.parseChoice("get first aid", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flashlight")), parser.parseChoice("get flashlight", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flashlight")), parser.parseChoice("get light", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Batteries")), parser.parseChoice("get batteries", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Batteries")), parser.parseChoice("get battery", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), parser.parseChoice("get wire", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), parser.parseChoice("get wires", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), parser.parseChoice("get 18 gauge wire", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), parser.parseChoice("get snare", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), parser.parseChoice("get 18 gauge wires", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pot")), parser.parseChoice("get pot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pot")), parser.parseChoice("get cooking pot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Axe")), parser.parseChoice("get axe", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Hatchet")), parser.parseChoice("get hatchet", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Iodine Tablets")), parser.parseChoice("get iodine tablets", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Iodine Tablets")), parser.parseChoice("get iodine", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol")), parser.parseChoice("get pistol", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol")), parser.parseChoice("get gun", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), parser.parseChoice("get ammunition", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), parser.parseChoice("get cartridges", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), parser.parseChoice("get ammo", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Shovel")), parser.parseChoice("get shovel", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Harmonica")), parser.parseChoice("get harmonica", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Lighter")), parser.parseChoice("get lighter", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Survival Manual")), parser.parseChoice("get survival manual", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Survival Manual")), parser.parseChoice("get manual", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Journal")), parser.parseChoice("get journal", player));
    }

    @Test
    public void testParseChoicePut() {
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Line")), parser.parseChoice("put fishing line", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Line")), parser.parseChoice("put fishing lines", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Hooks")), parser.parseChoice("put fishing hooks", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Hooks")), parser.parseChoice("put fishing hook", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Lures")), parser.parseChoice("put fishing lure", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Lures")), parser.parseChoice("put fishing lures", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Knife")), parser.parseChoice("put knife", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flint and Steel")), parser.parseChoice("put flint and steel", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Bow")), parser.parseChoice("put bow", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Arrow")), parser.parseChoice("put arrow", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Arrow")), parser.parseChoice("put arrows", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Family Photo")), parser.parseChoice("put family photo", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Family Photo")), parser.parseChoice("put photo", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Family Photo")), parser.parseChoice("put photograph", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Parachute Chord")), parser.parseChoice("put rope", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Parachute Chord")), parser.parseChoice("put parachute chord", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Parachute Chord")), parser.parseChoice("put cordage", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flare")), parser.parseChoice("put flare", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flare")), parser.parseChoice("put flares", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Boots")), parser.parseChoice("put boots", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Boots")), parser.parseChoice("put boot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pants")), parser.parseChoice("put pants", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Sleeping Gear")), parser.parseChoice("put sleeping gear", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Sleeping Gear")), parser.parseChoice("put sleeping bag", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Cold Weather Gear")), parser.parseChoice("put cold weather gear", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Cold Weather Gear")), parser.parseChoice("put cold gear", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Tarp")), parser.parseChoice("put tarp", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Matches")), parser.parseChoice("put matches", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Matches")), parser.parseChoice("put match", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("First Aid Kit")), parser.parseChoice("put first aid kit", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("First Aid Kit")), parser.parseChoice("put first aid", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flashlight")), parser.parseChoice("put flashlight", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flashlight")), parser.parseChoice("put light", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Batteries")), parser.parseChoice("put batteries", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Batteries")), parser.parseChoice("put battery", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), parser.parseChoice("put wire", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), parser.parseChoice("put wires", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), parser.parseChoice("put 18 gauge wire", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), parser.parseChoice("put snare", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), parser.parseChoice("put 18 gauge wires", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pot")), parser.parseChoice("put pot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pot")), parser.parseChoice("put cooking pot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Axe")), parser.parseChoice("put axe", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Hatchet")), parser.parseChoice("put hatchet", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Iodine Tablets")), parser.parseChoice("put iodine tablets", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Iodine Tablets")), parser.parseChoice("put iodine", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol")), parser.parseChoice("put pistol", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol")), parser.parseChoice("put gun", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), parser.parseChoice("put ammunition", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), parser.parseChoice("put cartridges", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), parser.parseChoice("put ammo", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Shovel")), parser.parseChoice("put shovel", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Harmonica")), parser.parseChoice("put harmonica", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Lighter")), parser.parseChoice("put lighter", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Survival Manual")), parser.parseChoice("put survival manual", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Survival Manual")), parser.parseChoice("put manual", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Journal")), parser.parseChoice("put journal", player));
    }

    @Test
    public void testParseChoiceImprove() {
        assertEquals(new Choice("improve", player), parser.parseChoice("build shelter", player));
        assertEquals(new Choice("improve", player), parser.parseChoice("work on shelter", player));
        assertEquals(new Choice("improve", player), parser.parseChoice("improve shelter", player));
        assertEquals(new Choice("improve", player), parser.parseChoice("make camp", player));
        assertEquals(new Choice("improve", player), parser.parseChoice("work on camp", player));
        assertEquals(new Choice("improve", player), parser.parseChoice("improve camp", player));
        assertEquals(new Choice("improve", player), parser.parseChoice("build camp", player));
    }

    @Test
    public void testParseChoiceDrink() {
        assertEquals(new Choice("drink", player), parser.parseChoice("drink", player));
        assertEquals(new Choice("drink", player), parser.parseChoice("drink water", player));
        assertEquals(new Choice("drink", player), parser.parseChoice("take a drink", player));
        assertEquals(new Choice("drink", player), parser.parseChoice("take drink", player));
    }

    @Test
    public void testParseChoiceGather() {
        assertEquals(new Choice("gather", player), parser.parseChoice("gather", player));
        assertEquals(new Choice("gather", player), parser.parseChoice("gather firewood", player));
        assertEquals(new Choice("gather", player), parser.parseChoice("gather wood", player));
        assertEquals(new Choice("gather", player), parser.parseChoice("get firewood", player));
    }

    @Test
    public void testParseChoiceFire() {
        assertEquals(new Choice("fire", player), parser.parseChoice("fire", player));
        assertEquals(new Choice("fire", player), parser.parseChoice("build fire", player));
        assertEquals(new Choice("fire", player), parser.parseChoice("light fire", player));
        assertEquals(new Choice("fire", player), parser.parseChoice("start fire", player));
        assertEquals(new Choice("fire", player), parser.parseChoice("make fire", player));
        assertEquals(new Choice("fire", player), parser.parseChoice("build a fire", player));
        assertEquals(new Choice("fire", player), parser.parseChoice("light a fire", player));
        assertEquals(new Choice("fire", player), parser.parseChoice("start a fire", player));
        assertEquals(new Choice("fire", player), parser.parseChoice("make a fire", player));
    }

    @Test
    public void testParseChoiceWater() {
        assertEquals(new Choice("water", player), parser.parseChoice("water", player));
        assertEquals(new Choice("water", player), parser.parseChoice("fetch water", player));
        assertEquals(new Choice("water", player), parser.parseChoice("collect water", player));
        assertEquals(new Choice("water", player), parser.parseChoice("get water", player));
    }

    @Test
    public void testParseChoiceMorale() {
        assertEquals(new Choice("morale", player), parser.parseChoice("morale", player));
        assertEquals(new Choice("morale", player), parser.parseChoice("boost morale", player));
        assertEquals(new Choice("morale", player), parser.parseChoice("look at photo", player));
        assertEquals(new Choice("morale", player), parser.parseChoice("look at family photo", player));
        assertEquals(new Choice("morale", player), parser.parseChoice("play harmonica", player));
        assertEquals(new Choice("morale", player), parser.parseChoice("write in journal", player));
    }

    @Test
    public void testParseChoiceRest() {
        assertEquals(new Choice("rest", player), parser.parseChoice("rest", player));
        assertEquals(new Choice("rest", player), parser.parseChoice("break", player));
        assertEquals(new Choice("rest", player), parser.parseChoice("take rest", player));
        assertEquals(new Choice("rest", player), parser.parseChoice("take break", player));
        assertEquals(new Choice("rest", player), parser.parseChoice("take a rest", player));
        assertEquals(new Choice("rest", player), parser.parseChoice("take a break", player));
        assertEquals(new Choice("rest", player), parser.parseChoice("relax", player));
        assertEquals(new Choice("rest", player), parser.parseChoice("nap", player));
        assertEquals(new Choice("rest", player), parser.parseChoice("take nap", player));
        assertEquals(new Choice("rest", player), parser.parseChoice("take a nap", player));
    }

    @Test
    public void testParseActivityChoiceGet() {
        for (Item item : ItemFactory.getAllItems()) {
            assertEquals(GetItemActivity.getInstance(), parser.parseActivityChoice(new Choice("get", player, item)));
        }
    }

    @Test
    public void testParseActivityChoicePut() {
        for (Item item : ItemFactory.getAllItems()) {
            assertEquals(PutItemActivity.getInstance(), parser.parseActivityChoice(new Choice("put", player, item)));
        }
    }

    @Test
    public void testParseActivityChoiceEat() {
        for (Food food : FoodFactory.getAllFood()) {
            assertEquals(EatActivity.getInstance(), parser.parseActivityChoice(new Choice("eat", player, food)));
        }
    }

    @Test
    public void testParseActivityChoiceDrink() {
        assertEquals(DrinkWaterActivity.getInstance(), parser.parseActivityChoice(new Choice("drink", player)));
    }

    @Test
    public void testParseActivityChoiceFish() {
        assertEquals(FishActivity.getInstance(), parser.parseActivityChoice(new Choice("fish", player)));
    }

    @Test
    public void testParseActivityChoiceHunt() {
        assertEquals(HuntActivity.getInstance(), parser.parseActivityChoice(new Choice("hunt", player)));
    }

    @Test
    public void testParseActivityChoiceTrap() {
        assertEquals(TrapActivity.getInstance(), parser.parseActivityChoice(new Choice("trap", player)));
    }

    @Test
    public void testParseActivityChoiceForage() {
        assertEquals(ForageActivity.getInstance(), parser.parseActivityChoice(new Choice("forage", player)));
    }

    @Test
    public void testParseActivityChoiceImprove() {
        assertEquals(ImproveShelterActivity.getInstance(), parser.parseActivityChoice(new Choice("improve", player)));
    }

    @Test
    public void testParseActivityChoiceGather() {
        assertEquals(GatherFirewoodActivity.getInstance(), parser.parseActivityChoice(new Choice("gather", player)));
    }

    @Test
    public void testParseActivityChoiceFire() {
        assertEquals(BuildFireActivity.getInstance(), parser.parseActivityChoice(new Choice("fire", player)));
    }

    @Test
    public void testParseActivityChoiceWater() {
        assertEquals(GetWaterActivity.getInstance(), parser.parseActivityChoice(new Choice("water", player)));
    }

    @Test
    public void testParseActivityChoiceMorale() {
        assertEquals(BoostMoraleActivity.getInstance(), parser.parseActivityChoice(new Choice("morale", player)));
    }

    @Test
    public void testParseActivityChoiceRest() {
        assertEquals(RestActivity.getInstance(), parser.parseActivityChoice(new Choice("rest", player)));
    }

}