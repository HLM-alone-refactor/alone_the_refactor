package com.palehorsestudios.alone.gui.controller;

import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.gui.GameManager;
import com.palehorsestudios.alone.gui.ViewFactory;
import com.palehorsestudios.alone.player.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.lucene.search.Collector;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static javafx.util.Duration.seconds;

public class ItemSelectionWindowController extends BaseController implements Initializable {
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

    // Private Variables
    private static final int COUNT_DOWN = 30;
    private List<Item> playerItems = new ArrayList<>();
    private Map<CheckBox, Item> inventory = new HashMap<>();

    public ItemSelectionWindowController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @FXML
    public void playGameButton() {
        System.out.println("Play button pressed");
        // Player now managed in gameManager
        gameManager.setPlayer(new Player(playerItems));
        viewFactory.showGameWindow();
        Stage stage = (Stage) countdown.getScene().getWindow();
        viewFactory.closeStage(stage);
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
        return playerItems;
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startTimer();
        selectItems();
    }

    private void startTimer() {
        // start the count down timer
        Integer[] timeSeconds = {COUNT_DOWN};
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline
                .getKeyFrames()
                .add(
                        new KeyFrame(
                                seconds(1),
                                new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        timeSeconds[0]--;
                                        // update timerLabel
                                        getCountdown().setText(timeSeconds[0].toString());
                                        if (timeSeconds[0] <= 0) {
                                            getPaneSelected().setDisable(true);
                                            timeline.stop();
                                        }
                                    }
                                }));
        timeline.playFromStart();
    }

    private void addCheckBoxes(int colums) {
        List<Item> itemBank = ItemFactory.getAllItems()
                .stream()
                .filter(Item::isInitialItemChoice)
                .collect(Collectors.toList());
        int row = 0, col = 0;
        for (Item item: itemBank) {
            CheckBox checkBox = new CheckBox(item.getType());
            paneSelected.add(checkBox,col, row);
            inventory.put(checkBox, item);

            col = ++col % colums;
            row = col == 0 ? ++row : row;
        }

   }

   private void selectItems() {
        addCheckBoxes(5);

       for (Map.Entry<CheckBox, Item> entry : inventory.entrySet()) {
           entry
                   .getKey()
                   .selectedProperty()
                   .addListener(
                           // initItems.size() instead of count....
                           new ChangeListener<Boolean>() {
                               public void changed(ObservableValue ov, Boolean old_val, Boolean new_val) {
                                   if (playerItems.size() >= 10) {
                                       entry.getKey().setSelected(false);
                                       if (playerItems.contains(entry.getValue())) {
                                           playerItems.remove(entry.getValue());
                                       }
                                       //paneSelected.setDisable(true);
                                   } else {
                                       if (entry.getKey().isSelected()) {
                                           playerItems.add(entry.getValue());
                                       } else if (!entry.getKey().isSelected()
                                               && playerItems.contains(entry.getValue())) {
                                           playerItems.remove(entry.getValue());
                                       }
                                   }
                               }
                           });
       }

   }
}
