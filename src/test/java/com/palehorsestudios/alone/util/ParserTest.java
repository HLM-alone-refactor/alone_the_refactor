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
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Moose")), Parser.parseChoice("eat moose", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Fish")), Parser.parseChoice("eat fish", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Squirrel")), Parser.parseChoice("eat squirrel", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Bear")), Parser.parseChoice("eat bear", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Rabbit")), Parser.parseChoice("eat rabbit", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Porcupine")), Parser.parseChoice("eat porcupine", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Bug")), Parser.parseChoice("eat bug", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Bug")), Parser.parseChoice("eat bugs", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Mushroom")), Parser.parseChoice("eat mushroom", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Mushroom")), Parser.parseChoice("eat mushrooms", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Berries")), Parser.parseChoice("eat berry", player));
        assertEquals(new Choice("eat", player, FoodFactory.getNewInstance("Berries")), Parser.parseChoice("eat berries", player));
    }

    @Test
    public void testParseChoiceHunt() {
        assertEquals(new Choice("hunt", player), Parser.parseChoice("hunt", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("go hunt", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("go hunting", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("hunt moose", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("hunt squirrel", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("hunt rabbit", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("hunt porcupine", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("kill moose", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("kill squirrel", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("kill rabbit", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("kill porcupine", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("get moose", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("get squirrel", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("get rabbit", player));
        assertEquals(new Choice("hunt", player), Parser.parseChoice("get porcupine", player));
    }

    @Test
    public void testParseChoiceForage() {
        assertEquals(new Choice("forage", player), Parser.parseChoice("forage", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("go forage", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("go foraging", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("hunt bug", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("hunt bugs", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("hunt mushrooms", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("hunt mushroom", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("hunt berry", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("hunt berries", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("get bug", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("get bugs", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("get mushrooms", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("get mushroom", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("get berry", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("get berries", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("kill bug", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("kill bugs", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("kill mushrooms", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("kill mushroom", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("kill berry", player));
        assertEquals(new Choice("forage", player), Parser.parseChoice("kill berries", player));
    }

    @Test
    public void testParseChoiceTrap() {
        assertEquals(new Choice("trap", player), Parser.parseChoice("trap", player));
        assertEquals(new Choice("trap", player), Parser.parseChoice("trap squirrel", player));
        assertEquals(new Choice("trap", player), Parser.parseChoice("go trap", player));
        assertEquals(new Choice("trap", player), Parser.parseChoice("trapping", player));
        assertEquals(new Choice("trap", player), Parser.parseChoice("go trapping", player));
        assertEquals(new Choice("trap", player), Parser.parseChoice("trap moose", player));
        assertEquals(new Choice("trap", player), Parser.parseChoice("trap rabbit", player));
        assertEquals(new Choice("trap", player), Parser.parseChoice("trap porcupine", player));
    }

    @Test
    public void testParseChoiceFish() {
        assertEquals(new Choice("fish", player), Parser.parseChoice("fish", player));
        assertEquals(new Choice("fish", player), Parser.parseChoice("fishing", player));
        assertEquals(new Choice("fish", player), Parser.parseChoice("go fish", player));
        assertEquals(new Choice("fish", player), Parser.parseChoice("go fishing", player));
    }

    @Test
    public void testParseChoiceGet() {
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Line")), Parser.parseChoice("get fishing line", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Line")), Parser.parseChoice("get fishing lines", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Hooks")), Parser.parseChoice("get fishing hooks", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Hooks")), Parser.parseChoice("get fishing hook", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Lures")), Parser.parseChoice("get fishing lure", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Fishing Lures")), Parser.parseChoice("get fishing lures", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Knife")), Parser.parseChoice("get knife", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flint and Steel")), Parser.parseChoice("get flint and steel", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Bow")), Parser.parseChoice("get bow", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Arrow")), Parser.parseChoice("get arrow", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Arrow")), Parser.parseChoice("get arrows", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Family Photo")), Parser.parseChoice("get family photo", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Family Photo")), Parser.parseChoice("get photo", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Family Photo")), Parser.parseChoice("get photograph", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Parachute Chord")), Parser.parseChoice("get rope", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Parachute Chord")), Parser.parseChoice("get parachute chord", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Parachute Chord")), Parser.parseChoice("get cordage", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flare")), Parser.parseChoice("get flare", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flare")), Parser.parseChoice("get flares", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Boots")), Parser.parseChoice("get extra boots", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Boots")), Parser.parseChoice("get extra boot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Boots")), Parser.parseChoice("get boots", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Boots")), Parser.parseChoice("get boot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pants")), Parser.parseChoice("get extra pants", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pants")), Parser.parseChoice("get pants", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Sleeping Gear")), Parser.parseChoice("get sleeping gear", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Sleeping Gear")), Parser.parseChoice("get sleeping bag", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Cold Weather Gear")), Parser.parseChoice("get cold weather gear", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Cold Weather Gear")), Parser.parseChoice("get cold gear", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Tarp")), Parser.parseChoice("get tarp", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Matches")), Parser.parseChoice("get matches", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Matches")), Parser.parseChoice("get match", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("First Aid Kit")), Parser.parseChoice("get first aid kit", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("First Aid Kit")), Parser.parseChoice("get first aid", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flashlight")), Parser.parseChoice("get flashlight", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Flashlight")), Parser.parseChoice("get light", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Batteries")), Parser.parseChoice("get batteries", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Batteries")), Parser.parseChoice("get battery", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), Parser.parseChoice("get wire", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), Parser.parseChoice("get wires", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), Parser.parseChoice("get 18 gauge wire", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), Parser.parseChoice("get snare", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Wire")), Parser.parseChoice("get 18 gauge wires", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pot")), Parser.parseChoice("get pot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pot")), Parser.parseChoice("get cooking pot", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Axe")), Parser.parseChoice("get axe", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Hatchet")), Parser.parseChoice("get hatchet", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Iodine Tablets")), Parser.parseChoice("get iodine tablets", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Iodine Tablets")), Parser.parseChoice("get iodine", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Iodine Tablets")), Parser.parseChoice("get tablets", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol")), Parser.parseChoice("get pistol", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol")), Parser.parseChoice("get gun", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), Parser.parseChoice("get ammunition", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), Parser.parseChoice("get cartridges", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), Parser.parseChoice("get ammo", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Pistol Cartridge")), Parser.parseChoice("get rounds", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Shovel")), Parser.parseChoice("get shovel", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Harmonica")), Parser.parseChoice("get harmonica", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Lighter")), Parser.parseChoice("get lighter", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Survival Manual")), Parser.parseChoice("get survival manual", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Survival Manual")), Parser.parseChoice("get manual", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Journal")), Parser.parseChoice("get journal and pen", player));
        assertEquals(new Choice("get", player, ItemFactory.getNewInstance("Journal")), Parser.parseChoice("get journal", player));
    }

    @Test
    public void testParseChoicePut() {
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Line")), Parser.parseChoice("put fishing line", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Line")), Parser.parseChoice("put fishing lines", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Hooks")), Parser.parseChoice("put fishing hooks", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Hooks")), Parser.parseChoice("put fishing hook", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Lures")), Parser.parseChoice("put fishing lure", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Fishing Lures")), Parser.parseChoice("put fishing lures", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Knife")), Parser.parseChoice("put knife", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flint and Steel")), Parser.parseChoice("put flint and steel", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Bow")), Parser.parseChoice("put bow", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Arrow")), Parser.parseChoice("put arrow", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Arrow")), Parser.parseChoice("put arrows", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Family Photo")), Parser.parseChoice("put family photo", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Family Photo")), Parser.parseChoice("put photo", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Family Photo")), Parser.parseChoice("put photograph", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Parachute Chord")), Parser.parseChoice("put rope", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Parachute Chord")), Parser.parseChoice("put parachute chord", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Parachute Chord")), Parser.parseChoice("put cordage", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flare")), Parser.parseChoice("put flare", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flare")), Parser.parseChoice("put flares", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Boots")), Parser.parseChoice("put extra boots", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Boots")), Parser.parseChoice("put extra boot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Boots")), Parser.parseChoice("put boots", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Boots")), Parser.parseChoice("put boot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pants")), Parser.parseChoice("put extra pants", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pants")), Parser.parseChoice("put pants", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Sleeping Gear")), Parser.parseChoice("put sleeping gear", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Sleeping Gear")), Parser.parseChoice("put sleeping bag", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Cold Weather Gear")), Parser.parseChoice("put cold weather gear", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Cold Weather Gear")), Parser.parseChoice("put cold gear", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Tarp")), Parser.parseChoice("put tarp", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Matches")), Parser.parseChoice("put matches", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Matches")), Parser.parseChoice("put match", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("First Aid Kit")), Parser.parseChoice("put first aid kit", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("First Aid Kit")), Parser.parseChoice("put first aid", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flashlight")), Parser.parseChoice("put flashlight", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Flashlight")), Parser.parseChoice("put light", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Batteries")), Parser.parseChoice("put batteries", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Batteries")), Parser.parseChoice("put battery", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), Parser.parseChoice("put wire", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), Parser.parseChoice("put wires", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), Parser.parseChoice("put 18 gauge wire", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), Parser.parseChoice("put snare", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Wire")), Parser.parseChoice("put 18 gauge wires", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pot")), Parser.parseChoice("put pot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pot")), Parser.parseChoice("put cooking pot", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Axe")), Parser.parseChoice("put axe", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Hatchet")), Parser.parseChoice("put hatchet", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Iodine Tablets")), Parser.parseChoice("put iodine tablets", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Iodine Tablets")), Parser.parseChoice("put iodine", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Iodine Tablets")), Parser.parseChoice("put tablets", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol")), Parser.parseChoice("put pistol", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol")), Parser.parseChoice("put gun", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), Parser.parseChoice("put ammunition", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), Parser.parseChoice("put cartridges", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), Parser.parseChoice("put ammo", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Pistol Cartridge")), Parser.parseChoice("put rounds", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Shovel")), Parser.parseChoice("put shovel", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Harmonica")), Parser.parseChoice("put harmonica", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Lighter")), Parser.parseChoice("put lighter", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Survival Manual")), Parser.parseChoice("put survival manual", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Survival Manual")), Parser.parseChoice("put manual", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Journal")), Parser.parseChoice("put journal and pen", player));
        assertEquals(new Choice("put", player, ItemFactory.getNewInstance("Journal")), Parser.parseChoice("put journal", player));
    }

    @Test
    public void testParseChoiceImprove() {
        assertEquals(new Choice("improve", player), Parser.parseChoice("build shelter", player));
        assertEquals(new Choice("improve", player), Parser.parseChoice("make camp", player));
        assertEquals(new Choice("improve", player), Parser.parseChoice("work on camp", player));
        assertEquals(new Choice("improve", player), Parser.parseChoice("work on shelter", player));
        assertEquals(new Choice("improve", player), Parser.parseChoice("improve shelter", player));
        assertEquals(new Choice("improve", player), Parser.parseChoice("improve camp", player));
        assertEquals(new Choice("improve", player), Parser.parseChoice("build camp", player));
    }

    @Test
    public void testParseChoiceDrink() {
        assertEquals(new Choice("drink", player), Parser.parseChoice("drink", player));
        assertEquals(new Choice("drink", player), Parser.parseChoice("drink water", player));
        assertEquals(new Choice("drink", player), Parser.parseChoice("get a drink", player));
        assertEquals(new Choice("drink", player), Parser.parseChoice("take a drink", player));
        assertEquals(new Choice("drink", player), Parser.parseChoice("get drink", player));
        assertEquals(new Choice("drink", player), Parser.parseChoice("take drink", player));
    }

    @Test
    public void testParseChoiceGather() {
        assertEquals(new Choice("gather", player), Parser.parseChoice("gather", player));
        assertEquals(new Choice("gather", player), Parser.parseChoice("gather firewood", player));
        assertEquals(new Choice("gather", player), Parser.parseChoice("get firewood", player));
        assertEquals(new Choice("gather", player), Parser.parseChoice("collect firewood", player));
        assertEquals(new Choice("gather", player), Parser.parseChoice("cut firewood", player));
        assertEquals(new Choice("gather", player), Parser.parseChoice("gather wood", player));
        assertEquals(new Choice("gather", player), Parser.parseChoice("get wood", player));
        assertEquals(new Choice("gather", player), Parser.parseChoice("collect wood", player));
        assertEquals(new Choice("gather", player), Parser.parseChoice("cut wood", player));
    }

    @Test
    public void testParseChoiceFire() {
        assertEquals(new Choice("fire", player), Parser.parseChoice("fire", player));
        assertEquals(new Choice("fire", player), Parser.parseChoice("build fire", player));
        assertEquals(new Choice("fire", player), Parser.parseChoice("light fire", player));
        assertEquals(new Choice("fire", player), Parser.parseChoice("start fire", player));
        assertEquals(new Choice("fire", player), Parser.parseChoice("make fire", player));
        assertEquals(new Choice("fire", player), Parser.parseChoice("build a fire", player));
        assertEquals(new Choice("fire", player), Parser.parseChoice("light a fire", player));
        assertEquals(new Choice("fire", player), Parser.parseChoice("start a fire", player));
        assertEquals(new Choice("fire", player), Parser.parseChoice("make a fire", player));
    }

    @Test
    public void testParseChoiceWater() {
        assertEquals(new Choice("water", player), Parser.parseChoice("water", player));
        assertEquals(new Choice("water", player), Parser.parseChoice("get water", player));
        assertEquals(new Choice("water", player), Parser.parseChoice("fetch water", player));
        assertEquals(new Choice("water", player), Parser.parseChoice("collect water", player));
    }

    @Test
    public void testParseChoiceMorale() {
        assertEquals(new Choice("morale", player), Parser.parseChoice("morale", player));
        assertEquals(new Choice("morale", player), Parser.parseChoice("improve morale", player));
        assertEquals(new Choice("morale", player), Parser.parseChoice("boost morale", player));
        assertEquals(new Choice("morale", player), Parser.parseChoice("photo", player));
        assertEquals(new Choice("morale", player), Parser.parseChoice("look at photo", player));
        assertEquals(new Choice("morale", player), Parser.parseChoice("look at family photo", player));
        assertEquals(new Choice("morale", player), Parser.parseChoice("harmonica", player));
        assertEquals(new Choice("morale", player), Parser.parseChoice("play harmonica", player));
        assertEquals(new Choice("morale", player), Parser.parseChoice("journal", player));
        assertEquals(new Choice("morale", player), Parser.parseChoice("write in journal", player));
    }

    @Test
    public void testParseChoiceRest() {
        assertEquals(new Choice("rest", player), Parser.parseChoice("rest", player));
        assertEquals(new Choice("rest", player), Parser.parseChoice("break", player));
        assertEquals(new Choice("rest", player), Parser.parseChoice("take rest", player));
        assertEquals(new Choice("rest", player), Parser.parseChoice("take break", player));
        assertEquals(new Choice("rest", player), Parser.parseChoice("take a rest", player));
        assertEquals(new Choice("rest", player), Parser.parseChoice("take a break", player));
        assertEquals(new Choice("rest", player), Parser.parseChoice("relax", player));
        assertEquals(new Choice("rest", player), Parser.parseChoice("nap", player));
        assertEquals(new Choice("rest", player), Parser.parseChoice("take nap", player));
        assertEquals(new Choice("rest", player), Parser.parseChoice("take a nap", player));
    }

    @Test
    public void testParseActivityChoiceGet() {
        for (Item item : ItemFactory.getAllItems()) {
            assertEquals(GetItemActivity.getInstance(), Parser.parseActivityChoice(new Choice("get", player, item)));
        }
    }

    @Test
    public void testParseActivityChoicePut() {
        for (Item item : ItemFactory.getAllItems()) {
            assertEquals(PutItemActivity.getInstance(), Parser.parseActivityChoice(new Choice("put", player, item)));
        }
    }

    @Test
    public void testParseActivityChoiceEat() {
        for (Food food : FoodFactory.getAllFood()) {
            assertEquals(EatActivity.getInstance(), Parser.parseActivityChoice(new Choice("eat", player, food)));
        }
    }

    @Test
    public void testParseActivityChoiceDrink() {
        assertEquals(DrinkWaterActivity.getInstance(), Parser.parseActivityChoice(new Choice("drink", player)));
    }

    @Test
    public void testParseActivityChoiceFish() {
        assertEquals(FishActivity.getInstance(), Parser.parseActivityChoice(new Choice("fish", player)));
    }

    @Test
    public void testParseActivityChoiceHunt() {
        assertEquals(HuntActivity.getInstance(), Parser.parseActivityChoice(new Choice("hunt", player)));
    }

    @Test
    public void testParseActivityChoiceTrap() {
        assertEquals(TrapActivity.getInstance(), Parser.parseActivityChoice(new Choice("trap", player)));
    }

    @Test
    public void testParseActivityChoiceForage() {
        assertEquals(ForageActivity.getInstance(), Parser.parseActivityChoice(new Choice("forage", player)));
    }

    @Test
    public void testParseActivityChoiceImprove() {
        assertEquals(ImproveShelterActivity.getInstance(), Parser.parseActivityChoice(new Choice("improve", player)));
    }

    @Test
    public void testParseActivityChoiceGather() {
        assertEquals(GatherFirewoodActivity.getInstance(), Parser.parseActivityChoice(new Choice("gather", player)));
    }

    @Test
    public void testParseActivityChoiceFire() {
        assertEquals(BuildFireActivity.getInstance(), Parser.parseActivityChoice(new Choice("fire", player)));
    }

    @Test
    public void testParseActivityChoiceWater() {
        assertEquals(GetWaterActivity.getInstance(), Parser.parseActivityChoice(new Choice("water", player)));
    }

    @Test
    public void testParseActivityChoiceMorale() {
        assertEquals(BoostMoraleActivity.getInstance(), Parser.parseActivityChoice(new Choice("morale", player)));
    }

    @Test
    public void testParseActivityChoiceRest() {
        assertEquals(RestActivity.getInstance(), Parser.parseActivityChoice(new Choice("rest", player)));
    }

}