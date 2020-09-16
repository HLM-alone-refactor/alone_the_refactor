package com.palehorsestudios.alone.util;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.activity.*;
import com.palehorsestudios.alone.player.Player;
import org.tartarus.snowball.ext.PorterStemmer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Parser {

    private static final PorterStemmer STEMMER =  new PorterStemmer();
    private static final Map<String, Activity> ACTIVITIES = getActivities();
    private static final Map<String, String> PARSERS = getParsers();
    private static final Map<String, String> ITEM_STEM_MAP = makeStemMapping(ItemFactory.getAllItems(), Item::getType,
            Optional.of(Item::getSynonym));
    private static final Map<String, String> FOOD_STEM_MAP = makeStemMapping(FoodFactory.getAllFood(), Food::toString);

    public static Choice parseChoice(String input, Player player) {
        // make sure input is lowercase
        input = input.toLowerCase();

        // set up needed variables
        Choice choice = null;
        String keyword = findKey(input);
        Food food;
        Item item;

        if (keyword != null) {
            if (keyword.equals("eat")) {
                try {
                    String temp = getStemMapValue(input, FOOD_STEM_MAP);
                    food = FoodFactory.getNewInstance(getStemMapValue(input, FOOD_STEM_MAP));
                    choice = new Choice(keyword, player, food);
                } catch (IllegalArgumentException e) {
                    // do nothing
                }

            } else if (keyword.equals("get") || keyword.equals("put")) {
                try {
                    String temp = getStemMapValue(input, ITEM_STEM_MAP);
                    item = ItemFactory.getNewInstance(getStemMapValue(input, ITEM_STEM_MAP));
                    choice = new Choice(keyword, player, item);
                } catch (IllegalArgumentException e) {
                    // do nothing
                }
            } else {
                choice = new Choice(keyword, player);
            }
        }
        return choice;
    }

    private static String getStemMapValue(String input, Map<String, String> map) {
        String result = null;

        // assume input is of style <action> <thing>, and only want <thing>
        String[] split = input.split(" ", 2);
        if (split.length > 1) {
            String stem = stemIt(split[1]);
            if (map.containsKey(stem)) {
                result = map.get(stem);
            }
        }
        return result;
    }

    private static String findKey(String input) {
        String key = null;
        for (String s : input.split(" ")) {
            s = stemIt(s);
            if (PARSERS.containsKey(s)) {
                key = s; // found first one
                break;
            }
        }

        return PARSERS.get(key); // returns null if key is null or not found
    }

    private static Map<String, Activity> getActivities() {
        return new HashMap<>() {{
            put("get", GetItemActivity.getInstance());
            put("put", PutItemActivity.getInstance());
            put("eat", EatActivity.getInstance());
            put("drink", DrinkWaterActivity.getInstance());
            put("fish", FishActivity.getInstance());
            put("hunt", HuntActivity.getInstance());
            put("trap", TrapActivity.getInstance());
            put("forage", ForageActivity.getInstance());
            put("improve", ImproveShelterActivity.getInstance());
            put("gather", GatherFirewoodActivity.getInstance());
            put("fire", BuildFireActivity.getInstance());
            put("water", GetWaterActivity.getInstance());
            put("morale", BoostMoraleActivity.getInstance());
            put("craft", CraftActivity.getInstance());
            put("rest", RestActivity.getInstance());
        }};
    }

    private static Map<String, String> getParsers() {
        Map<String, String> result = ACTIVITIES.keySet()
                .stream()
                .collect(Collectors.toMap(Parser::stemIt, e -> e));

        // input some synonyms for things to parse with
        addToParser(result,"rest", Set.of("nap", "break", "relax"));
        addToParser(result, "hunt", Set.of("kill"));
        addToParser(result, "improve", Set.of("camp", "shelter"));
        addToParser(result, "morale", Set.of("write", "play", "look"));

        return result;
    }

    private static void addToParser(Map<String, String> map, String target, Set<String> synonym) {
        synonym.forEach(e -> map.put(stemIt(e), target));
    }

    public static Activity parseActivityChoice(Choice choice) {
        if (choice == null) {
            return null;
        } else {
            return Objects.requireNonNullElse(ACTIVITIES.get(choice.getKeyword()), RestActivity.getInstance());
        }
    }

    public static String stemIt(String input) {
        STEMMER.setCurrent(HelperMethods.capitalize(input));
        STEMMER.stem();
        return STEMMER.getCurrent().toLowerCase();
    }


    private static <T> Map<String, String> makeStemMapping(Set<T> things, Function<T, String> function) {
        return makeStemMapping(things, function, Optional.empty());
    }
    // stem each thing and put it in a mapping. If spaces between thing's function return, add all of them to mapping
    // as well
    private static <T> Map<String, String> makeStemMapping(Set<T> things, Function<T, String> function,
                                                           Optional<Function<T, Set<String>>> otherFun) {
        Map<String, String> result = new HashMap<>();
        for (T thing : things) {
            String name = function.apply(thing);
            updateMap(result, name, name);
            otherFun.ifPresent(tSetFunction -> tSetFunction.apply(thing).forEach(e -> updateMap(result, e, name)));
        }

        // ensure their names where not overwritten when calling updateMap. May happen while trying to get many combos
        for (T thing : things) {
            String name = function.apply(thing);
            result.put(stemIt(name), name);
        }

        return result;
    }

    private static void updateMap(Map<String, String> map, String str, String target) {
        for (String s : str.split(" ")) {
            map.put(stemIt(s), target);
        }
        if (map.get(stemIt(str)) != target)
        map.put(stemIt(str), target);
    }
}
