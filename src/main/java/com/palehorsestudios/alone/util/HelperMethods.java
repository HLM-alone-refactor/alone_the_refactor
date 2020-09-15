package com.palehorsestudios.alone.util;

import org.tartarus.snowball.ext.PorterStemmer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HelperMethods {

    /**
     * Helper method for rounding double values. Thank you to https://www.baeldung.com/java-round-decimal-number
     *
     * @param value  Value to be rounded.
     * @param places Desired decimal places.
     * @return Rounded value.
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String getLargestFoodUnit(double grams) {
        StringBuilder sb = new StringBuilder();
        if (grams > 456) {
            double foodWeightInPounds = HelperMethods.round(grams / 436, 1);
            sb.append(foodWeightInPounds).append(" lb");
        }
        // if food weight greater than an ounce, display in ounces
        else if (grams > 28) {
            double foodWeightInOunces = HelperMethods.round(grams / 28, 1);
            sb.append(foodWeightInOunces).append(" oz");
        }
        // else display in grams
        else {
            sb.append(grams).append(" g");
        }
        return sb.toString();
    }

    public static String titleize(String input) {
        return Arrays.stream(input.split(" "))
                .map(HelperMethods::capitalize)
                .collect(Collectors.joining(" "))
                .replaceAll(" And ", "and")
                .replaceAll(" Is ", " is ")
                .replaceAll(" Of ", " of ");
    }
    public static String capitalize(String input) {
        return input.substring(0,1).toUpperCase() + input.substring(1);
    }
}
