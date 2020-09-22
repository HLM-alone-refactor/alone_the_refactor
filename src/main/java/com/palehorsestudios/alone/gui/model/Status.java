package com.palehorsestudios.alone.gui.model;

public enum Status {
    STILL_ALIVE("still alive"),
    EATEN_BY_BEAR("eaten by a bear"),
    DOWN_BUT_NOT_DEFEATED(""),
    STARVED("died from starvation"),
    DEHYDRATION("died from dehydration"),
    LOST_WILL_TO_LIVE("lost the will to live"),
    HARD_RAIN("there was a hard rain"),
    MISSED_RESCUE("missed the rescue"),
    MEAT_ITS_WHATS_FOR_DINNER("killed the bear"),
    RESCUED("were rescued");

    String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
