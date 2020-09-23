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

    private PorterStemmer STEMMER ; // object to find the stem of words
    private Map<String, Activity> ACTIVITIES; // activity map
    private Map<String, String> PARSERS; // stem to correct version (for getting correct activity)
    private Map<String, String> ITEM_STEM_MAP; // stem to correct item type
    private Map<String, String> FOOD_STEM_MAP; // stem to correct food type


    public Parser() {
        STEMMER = new PorterStemmer();
        ACTIVITIES = getActivities();
        PARSERS = getParsers();
        ITEM_STEM_MAP = makeStemMapping(ItemFactory.getAllItems(), Item::getType,
                Optional.of(Item::getSynonym));
        FOOD_STEM_MAP = makeStemMapping(FoodFactory.getAllFood(), Food::toString);
    }
    /**
     * @param choice
     * @return
     */
    public Activity parseActivityChoice(Choice choice) {
        if (choice == null) {
            return null;
        } else {
            return Objects.requireNonNullElse(ACTIVITIES.get(choice.getKeyword()), RestActivity.getInstance());
        }
    }


    /**
     * Parses the given input to get a choice object. Filters out to find the keyword needed for getting the correct
     * activity.
     *
     * @param input  String to parse
     * @param player
     * @return constructed choice from parsing. If not an activity, returns null.
     */
    public  Choice parseChoice(String input, Player player) {
        // make sure input is lowercase
        input = input.toLowerCase();

        // find the activity key from activity map
        String keyword = findKey(input);

        // set up needed variables
        Choice choice = null;

        if (keyword != null) {
            Food food = null;
            Item item = null;
            switch (keyword) {
                case "eat" -> {
                    try {
                        food = FoodFactory.getNewInstance(getStemMapValue(input, FOOD_STEM_MAP));
                    } catch (IllegalArgumentException e) {
                        return null;
                    }
                }
                case "get", "put" , "craft" -> {
                    try {
                        item = ItemFactory.getNewInstance(getStemMapValue(input, ITEM_STEM_MAP));
                    } catch (IllegalArgumentException e) {
                        return null;
                    }
                }
            }

            choice = new Choice(keyword, player, item, food);
        }
        return choice;
    }

    /*
    Get the value from given map via the input. This assumes input is '<action> <thing>' where trying to figure out what
    <thing> is.
     */
    private String getStemMapValue(String input, Map<String, String> map) {
        String result = null;

        // assume input is of style <action> <thing>, and only want <thing>
        String[] split = input.split(findKey(input), 2);
        if (split.length > 1) {
            String stem = stemIt(split[1]);
            if (map.containsKey(stem)) {
                result = map.get(stem);
            }
        }
        return result;
    }

    /*
    Finds the key from PARSERS to be used for correct lookup in activities map. If the input has 'get', checks if rest
    of input is another key in activites, and switches to that one if found.

    e.g.    'get water' -> water
            'get knife' -> get
            'get fish' -> fish
            'get fishing line' -> get
     */
    private  String findKey(String input) {
        String key = null;
        String[] words = input.split(" ");
        for (String s : words) {
            s = stemIt(s);
            if (PARSERS.containsKey(s)) {
                key = s; // found it
                if (key.equals("get") && words.length > 1) { // check if latter half is some other activity
                    String latter = input.split("get", 2)[1]; // get the string after "get"
                    latter = stemIt(latter);
                    if (PARSERS.containsKey(latter)) { // check if other activity and set if true
                        key = latter;
                    }
                }
                break; // we done here **drops mic**
            }
        }

        return PARSERS.get(key); // returns null if key is null or not found
    }

    /*
    makes activities map to be used. These are all of the keywords to refer to an activity
     */
    private  Map<String, Activity> getActivities() {
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

    /*
    make the stem map from stem -> activity keyword. Add synonyms of keywords to add more versatility in how to reference
    activities.
     */
    private Map<String, String> getParsers() {
        // get all of the keywords in activities, and stem each one
        Map<String, String> result = ACTIVITIES.keySet()
                .stream()
                .collect(Collectors.toMap(e -> stemIt(e),e -> e));

        // input some synonyms for things to parse with
        addToParser(result, "rest", Set.of("nap", "break", "relax"));
        addToParser(result, "hunt", Set.of("kill"));
        addToParser(result, "improve", Set.of("camp", "shelter"));
        addToParser(result, "morale", Set.of("write", "play", "look"));
        addToParser(result, "gather", Set.of("firewood"));

        return result;
    }

    /*
    adds each synonym to the given map, stem(synonym) is key, target is the activity it is mapping to
     */
    private void addToParser(Map<String, String> map, String target, Set<String> synonym) {
        synonym.forEach(e -> map.put(stemIt(e), target));
    }

    /*
    Stems the given string and returns its stemmed value. Ensure lowercase (some strings behave erratic with cases)
     */
    public String stemIt(String input) {
        input = input.strip().toLowerCase();
        STEMMER.setCurrent(HelperMethods.capitalize(input));
        STEMMER.stem();
        return STEMMER.getCurrent().toLowerCase();
    }


    /*
    Refers to other mapping function
     */
    private <T> Map<String, String> makeStemMapping(Set<T> things, Function<T, String> function) {
        return makeStemMapping(things, function, Optional.empty());
    }

    // stem each thing and put it in a mapping. If spaces between thing's function's return, add all of them to mapping
    // as well
    private <T> Map<String, String> makeStemMapping(Set<T> things, Function<T, String> function,
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

    /*
    splits str on white spaces and attempts to add it to the given map (stem each part for the key), maps to passed
    target string. Also adds entire str to map for multi word possibilities.
     */
    private void updateMap(Map<String, String> map, String str, String target) {
        for (String s : str.split(" ")) {
            map.put(stemIt(s), target);
        }
        if (!map.containsKey(stemIt(str)) || !map.get(stemIt(str)).equals(target))
            map.put(stemIt(str), target);
    }
}
