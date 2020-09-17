package com.palehorsestudios.alone.gui;

import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.*;

public class ItemSelectionController {
    @FXML
    private GridPane paneSelected;
    @FXML
    private Button next;
    @FXML
    private Label countdown;
    @FXML
    private CheckBox fishingLine;
    @FXML
    private CheckBox fishingHooks;
    @FXML
    private CheckBox fishingLures;
    @FXML
    private CheckBox knife;
    @FXML
    private CheckBox flintandsteel;
    @FXML
    private CheckBox bow;
    @FXML
    private CheckBox arrows;
    @FXML
    private CheckBox familyPhoto;
    @FXML
    private CheckBox parachuteChord;
    @FXML
    private CheckBox flare;
    @FXML
    private CheckBox extraBoots;
    @FXML
    private CheckBox extraPants;
    @FXML
    private CheckBox sleepingGear;
    @FXML
    private CheckBox coldWeatherGear;
    @FXML
    private CheckBox footTarp;
    @FXML
    private CheckBox matches;
    @FXML
    private CheckBox firstAid;
    @FXML
    private CheckBox flashlight;
    @FXML
    private CheckBox extraBatteries;
    @FXML
    private CheckBox gaugeWire;
    @FXML
    private CheckBox cookingPot;
    @FXML
    private CheckBox axe;
    @FXML
    private CheckBox hatchet;
    @FXML
    private CheckBox iodineTablets;
    @FXML
    private CheckBox magnumRevolver;
    @FXML
    private CheckBox cartridges;
    @FXML
    private CheckBox shovel;
    @FXML
    private CheckBox harmonica;
    @FXML
    private CheckBox lighter;
    @FXML
    private CheckBox survivalManual;
    @FXML
    private CheckBox journalandpen;

    private static int count = 0;
    private List<Item> initItems = new ArrayList<>();

    public void selectItems() {
        Map<CheckBox, Item> inventory = new HashMap<>();
        inventory.put(fishingLine, ItemFactory.getNewInstance("Fishing Line"));
        inventory.put(fishingHooks, ItemFactory.getNewInstance("Fishing Hooks"));
        inventory.put(fishingLures, ItemFactory.getNewInstance("Fishing Lures"));
        inventory.put(knife, ItemFactory.getNewInstance("Knife"));
        inventory.put(flintandsteel, ItemFactory.getNewInstance("Flint and Steel"));
        inventory.put(bow, ItemFactory.getNewInstance("Bow"));
        inventory.put(arrows, ItemFactory.getNewInstance("Arrow"));
        inventory.put(familyPhoto, ItemFactory.getNewInstance("Family Photo"));
        inventory.put(parachuteChord, ItemFactory.getNewInstance("Parachute Chord"));
        inventory.put(flare, ItemFactory.getNewInstance("Flare"));
        inventory.put(extraBoots, ItemFactory.getNewInstance("Boots"));
        inventory.put(extraPants, ItemFactory.getNewInstance("Pants"));
        inventory.put(sleepingGear, ItemFactory.getNewInstance("Sleeping Gear"));
        inventory.put(coldWeatherGear, ItemFactory.getNewInstance("Cold Weather Gear"));
        inventory.put(footTarp, ItemFactory.getNewInstance("Tarp"));
        inventory.put(matches, ItemFactory.getNewInstance("Matches"));
        inventory.put(flashlight, ItemFactory.getNewInstance("Flashlight"));
        inventory.put(firstAid, ItemFactory.getNewInstance("First Aid Kit"));
        inventory.put(extraBatteries, ItemFactory.getNewInstance("Batteries"));
        inventory.put(gaugeWire, ItemFactory.getNewInstance("Wire"));
        inventory.put(cookingPot, ItemFactory.getNewInstance("Pot"));
        inventory.put(axe, ItemFactory.getNewInstance("Axe"));
        inventory.put(hatchet, ItemFactory.getNewInstance("Hatchet"));
        inventory.put(iodineTablets, ItemFactory.getNewInstance("Iodine Tablets"));
        inventory.put(magnumRevolver, ItemFactory.getNewInstance("Pistol"));
        inventory.put(cartridges, ItemFactory.getNewInstance("Pistol Cartridge"));
        inventory.put(shovel, ItemFactory.getNewInstance("Shovel"));
        inventory.put(harmonica, ItemFactory.getNewInstance("Harmonica"));
        inventory.put(lighter, ItemFactory.getNewInstance("Lighter"));
        inventory.put(survivalManual, ItemFactory.getNewInstance("Survival Manual"));
        inventory.put(journalandpen, ItemFactory.getNewInstance("Journal"));

        for (Map.Entry<CheckBox, Item> entry : inventory.entrySet()) {
            entry
                    .getKey()
                    .selectedProperty()
                    .addListener(
                            new ChangeListener<Boolean>() {
                                public void changed(ObservableValue ov, Boolean old_val, Boolean new_val) {
                                    if (count == 9) {
                                        paneSelected.setDisable(true);
                                    } else {
                                        if (entry.getKey().isSelected()) {
                                            initItems.add(entry.getValue());
                                            count++;
                                        } else if (!entry.getKey().isSelected()
                                                && initItems.contains(entry.getValue())) {
                                            count--;
                                            initItems.remove(entry.getValue());
                                        }
                                    }
                                }
                            });
        }

    }

    public Button getNext() {
        return next;
    }

    public Label getCountdown() {
        return countdown;
    }

    public GridPane getPaneSelected() {
        return paneSelected;
    }

    public List<Item> getInitItems() {
        return initItems;
    }
}
