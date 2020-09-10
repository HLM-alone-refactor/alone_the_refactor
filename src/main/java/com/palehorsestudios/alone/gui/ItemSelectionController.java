package com.palehorsestudios.alone.gui;

import com.palehorsestudios.alone.Item;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemSelectionController {
  @FXML private GridPane paneSelected;
  @FXML private Button next;
  @FXML private Label countdown;
  @FXML private CheckBox fishingLine;
  @FXML private CheckBox fishingHooks;
  @FXML private CheckBox fishingLures;
  @FXML private CheckBox knife;
  @FXML private CheckBox flintandsteel;
  @FXML private CheckBox bow;
  @FXML private CheckBox arrows;
  @FXML private CheckBox familyPhoto;
  @FXML private CheckBox parachuteChord;
  @FXML private CheckBox flare;
  @FXML private CheckBox extraBoots;
  @FXML private CheckBox extraPants;
  @FXML private CheckBox sleepingGear;
  @FXML private CheckBox coldWeatherGear;
  @FXML private CheckBox footTarp;
  @FXML private CheckBox matches;
  @FXML private CheckBox firstAid;
  @FXML private CheckBox flashlight;
  @FXML private CheckBox extraBatteries;
  @FXML private CheckBox gaugeWire;
  @FXML private CheckBox cookingPot;
  @FXML private CheckBox axe;
  @FXML private CheckBox hatchet;
  @FXML private CheckBox iodineTablets;
  @FXML private CheckBox magnumRevolver;
  @FXML private CheckBox cartridges;
  @FXML private CheckBox shovel;
  @FXML private CheckBox harmonica;
  @FXML private CheckBox lighter;
  @FXML private CheckBox survivalManual;
  @FXML private CheckBox journalandpen;

  private static int count = 0;
  private Set<Item> initItems = new HashSet<>();

  public void selectItems() {
    Map<CheckBox, Item> inventory = new HashMap<>();
    inventory.put(fishingLine, Item.FISHING_LINE);
    inventory.put(fishingHooks, Item.FISHING_HOOKS);
    inventory.put(fishingLures, Item.FISHING_LURES);
    inventory.put(knife, Item.KNIFE);
    inventory.put(flintandsteel, Item.FLINT_AND_STEEL);
    inventory.put(bow, Item.BOW);
    inventory.put(arrows, Item.ARROWS);
    inventory.put(familyPhoto, Item.FAMILY_PHOTO);
    inventory.put(parachuteChord, Item.PARACHUTE_CHORD);
    inventory.put(flare, Item.FLARE);
    inventory.put(extraBoots, Item.EXTRA_BOOTS);
    inventory.put(extraPants, Item.EXTRA_PANTS);
    inventory.put(sleepingGear, Item.SLEEPING_GEAR);
    inventory.put(coldWeatherGear, Item.COLD_WEATHER_GEAR);
    inventory.put(footTarp, Item.TARP);
    inventory.put(matches, Item.MATCHES);
    inventory.put(firstAid, Item.FIRST_AID_KIT);
    inventory.put(flashlight, Item.FLASHLIGHT);
    inventory.put(extraBatteries, Item.BATTERIES);
    inventory.put(gaugeWire, Item.WIRE);
    inventory.put(cookingPot, Item.POT);
    inventory.put(axe, Item.AXE);
    inventory.put(hatchet, Item.HATCHET);
    inventory.put(iodineTablets, Item.IODINE_TABLETS);
    inventory.put(magnumRevolver, Item.PISTOL);
    inventory.put(cartridges, Item.PISTOL_CARTRIDGES);
    inventory.put(shovel, Item.SHOVEL);
    inventory.put(harmonica, Item.HARMONICA);
    inventory.put(lighter, Item.LIGHTER);
    inventory.put(survivalManual, Item.SURVIVAL_MANUAL);
    inventory.put(journalandpen, Item.JOURNAL);

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

  public Set<Item> getInitItems() {
    return initItems;
  }
}
