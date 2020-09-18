package com.palehorsestudios.alone.gui.controller;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.activity.*;
import com.palehorsestudios.alone.dayencounter.BearEncounterDay;
import com.palehorsestudios.alone.dayencounter.DayEncounter;
import com.palehorsestudios.alone.dayencounter.RescueHelicopterDay;
import com.palehorsestudios.alone.gui.GameManager;
import com.palehorsestudios.alone.gui.ViewFactory;
import com.palehorsestudios.alone.gui.model.PlayerStatus;
import com.palehorsestudios.alone.gui.model.Status;
import com.palehorsestudios.alone.nightencounter.BearEncounterNight;
import com.palehorsestudios.alone.nightencounter.NightEncounter;
import com.palehorsestudios.alone.nightencounter.RainStorm;
import com.palehorsestudios.alone.nightencounter.RescueHelicopterNight;
import com.palehorsestudios.alone.player.Player;
import com.palehorsestudios.alone.player.SuccessRate;
import com.palehorsestudios.alone.util.HelperMethods;
import com.palehorsestudios.alone.util.Parser;
import com.palehorsestudios.alone.util.Saving;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.palehorsestudios.alone.gui.model.Status.*;

public class GameWindowController extends BaseController implements Initializable {
    @FXML
    private TextArea curActivity;
    @FXML
    private TextArea playerInput;
    @FXML
    private TextArea dailyLog;
    @FXML
    private TextField dateAndTime;
    @FXML
    private Button enterBtn;

    @FXML
    private TextField weight;
    @FXML
    private TextField hydration;
    @FXML
    private TextField morale;
    @FXML
    private ListView<String> carriedItems;
    @FXML
    private TextField integrity;
    @FXML
    private TextField firewood;
    @FXML
    private TextField water;
    @FXML
    private ListView<String> foodCache;
    @FXML
    private ListView<String> equipment;

    @FXML
    private MediaView mediaView;

    // private Vars
    private String currentInput;
    private Player player;
    private static List<Item> initItems;
    private final InputSignal inputSignal = new InputSignal();
    public static class InputSignal { }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        runGameThread();
    }

    /*
        Menu Options
    */
    @FXML
    void restartGameMenuAction() {
        viewFactory.showSelectItemsWindow();
        Stage stage = (Stage) integrity.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void quitGameAction() {
        Stage stage = (Stage) integrity.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void aboutMenuAction() {
        viewFactory.showAboutWindow();
    }

    @FXML
    void craftingMenuAction(ActionEvent event) {
        viewFactory.showCraftingWindow();
    }

    @FXML
    void saveGameAction() {
        Saving saving = new Saving();
        saving.saveState(gameManager.getDefaultGameFile(), gameManager.getPlayer(), getDailyLog().getText());
    }

    @FXML
    void saveAsGameAction() {

        File file = viewFactory.getFileChooser().showSaveDialog((Stage) integrity.getScene().getWindow());

        if (file != null) {
            gameManager.setDefaultGameFile(file);
            Saving saving = new Saving();

            saving.saveState(file, gameManager.getPlayer(), getDailyLog().getText());
        }
    }

    public GameWindowController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
        this.player = gameManager.getPlayer();
    }

    private void runGameThread() {
        EventHandler<ActionEvent> eventHandler =
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        currentInput = getPlayerInput().getText().trim();
                        notifyInput();
                        getPlayerInput().clear();
                        getPlayerInput().requestFocus();
                    }
                };

        EventHandler<KeyEvent> enterPressedHandler =
                keyEvent -> {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        currentInput = getPlayerInput().getText().trim();
                        notifyInput();
                        getPlayerInput().clear();
                        getPlayerInput().requestFocus();
                    }
                };

        getPlayerInput().setOnKeyPressed(enterPressedHandler);

        getEnterButton().setOnAction(eventHandler);
        Thread gameLoop =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                executeGameLoop();
                            }
                        });

        // don't let thread prevent JVM shutdown
        gameLoop.setDaemon(true);
        gameLoop.start();
    }

    // game thread logic, so we should also wrap the UI access calls
    private void executeGameLoop() {
        // flag for encounter results
        Status status = STILL_ALIVE;
        // encounter results
        String encounterResults = "Killed by the encounter";
        // must run in ui thread
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        getNarrative(new File("resources/parserHelp.txt"));
                        getNarrative(new File("resources/scene1.txt"));
                    }
                });
        final int[] day = {1};
        final String[] dayHalf = {"Morning"};
        getDateAndTime().setText("Day " + day[0] + " " + dayHalf[0]);

        Parser parser = new Parser();
        while (!player.isDead() && !player.isRescued() && !player.isRescued(day[0])) {
            // update the UI fields
            updateUI();
            String input = getInput();
            // hmm had to use main here?
            Choice choice = parser.parseChoice(input, player);
            Activity activity = parser.parseActivityChoice(choice);
            if (activity == null) {
                getNarrative(new File("resources/parserHelp.txt"));
            } else if (activity == EatActivity.getInstance()
                    || activity == DrinkWaterActivity.getInstance()
                    || activity == GetItemActivity.getInstance()
                    || activity == PutItemActivity.getInstance()
                    || activity == BuildFireActivity.getInstance()) {
                String activityResult = activity.act(choice);
                getDailyLog().appendText("Day " + day[0] + " " + dayHalf[0] + ": " + activityResult + "\n");
            } else {
                final int[] seed = {(int) Math.floor(Math.random() * 10)};
                PlayerStatus dayResultObject;
                String dayTextOutput;
                if (seed[0] > 7) {
                    DayEncounter[] dayEncounters = new DayEncounter[]{
                            BearEncounterDay.getInstance(),
                            RescueHelicopterDay.getInstance()};
                    int randomDayEncounterIndex = (int) Math.floor(Math.random() * dayEncounters.length);
                    dayResultObject = dayEncounters[randomDayEncounterIndex].encounter(player);
                    status = dayResultObject.getStatusUpdate();
                    dayTextOutput = dayResultObject.getTextOutput();
                } else {
                    dayTextOutput = activity.act(choice);
                }
                getDailyLog().appendText("Day " + day[0] + " " + dayHalf[0] + ": " + dayTextOutput + "\n");
                if (dayHalf[0].equals("Morning")) {
                    dayHalf[0] = "Afternoon";
                } else {
                    if (!player.isDead() && !player.isRescued(day[0])) {
                        seed[0] = (int) Math.floor(Math.random() * 10);
                        PlayerStatus nightResultObject;
                        String nightTextOutput;
                        if (seed[0] > 7) {
                            NightEncounter[] nightEncounters =
                                    new NightEncounter[]{
                                            RainStorm.getInstance(),
                                            BearEncounterNight.getInstance(),
                                            RescueHelicopterNight.getInstance()};
                            int randomNightEncounterIndex =
                                    (int) Math.floor(Math.random() * nightEncounters.length);
                            // Get gamestate object from enounter
                            nightResultObject = nightEncounters[randomNightEncounterIndex].encounter(player);
                            status = nightResultObject.getStatusUpdate();
                            nightTextOutput = nightResultObject.getTextOutput();
                        } else {
                            nightResultObject = overnightStatusUpdate(player);
                            status = nightResultObject.getStatusUpdate();
                            nightTextOutput = nightResultObject.getTextOutput();
                        }
                        if (!player.isDead() || !player.isRescued()) {
                            getDailyLog().appendText("Day " + day[0] + " Night: " + nightTextOutput + "\n");
                            dayHalf[0] = "Morning";
                            day[0]++;
                        }
                    }
                }
                getDateAndTime().setText("Day " + day[0] + " " + dayHalf[0]);
            }
            playStatusMedia(status);
        }
    }

    private void playStatusMedia(Status status) {
        switch (status) {
            case STILL_ALIVE -> {
                System.out.println("Still alive");
            }
            case EATEN_BY_BEAR -> {
                System.out.println("eaten by bear. you lose");
                setMediaViewAndPlayVideo("resources/clips/dark_souls.mp4");
            }
            case RESCUED -> {
                System.out.println("You were rescued!!");
                setMediaViewAndPlayVideo("resources/clips/the_nod.mp4");
            }
            case MISSED_RESCUE -> {
                System.out.println("You missed your chance of rescue, so sad!");
            }
            case HARD_RAIN -> {
                System.out.println("Last night there was a torrential downpour!!!");
            }
        }
    }

    private void setMediaViewAndPlayVideo(String fileName) {
        Media media = new Media(Paths.get(fileName).toUri().toString());
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setPreserveRatio(false);
        mediaView.setMediaPlayer(player);
        player.play();
    }

    public void updateUI() {
        // update player status
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        if (!getWeight().getText().isEmpty()
                                && !getWeight().getText().isBlank()
                                && !getHydration().getText().isEmpty()
                                && !getHydration().getText().isBlank()
                                && !getMorale().getText().isEmpty()
                                && !getMorale().getText().isBlank()
                                && !getIntegrity().getText().isEmpty()
                                && !getIntegrity().getText().isBlank()
                                && !getFirewood().getText().isEmpty()
                                && !getFirewood().getText().isBlank()
                                && !getWater().getText().isEmpty()
                                && !getWater().getText().isBlank()) {
                            try {
                                double currentWeight = Double.parseDouble(getWeight().getText());
                                if (currentWeight < player.getWeight()) {
                                    getWeight().setStyle("-fx-text-inner-color: green;");
                                } else if (currentWeight > player.getWeight()) {
                                    getWeight().setStyle("-fx-text-inner-color: red;");
                                } else {
                                    getWeight().setStyle("-fx-text-inner-color: black;");
                                }
                                int currentHydration = Integer.parseInt(getHydration().getText());
                                if (currentHydration < player.getHydration()) {
                                    getHydration().setStyle("-fx-text-inner-color: green;");
                                } else if (currentHydration > player.getHydration()) {
                                    getHydration().setStyle("-fx-text-inner-color: red;");
                                } else {
                                    getHydration().setStyle("-fx-text-inner-color: black;");
                                }
                                int currentMorale = Integer.parseInt(getMorale().getText());
                                if (currentMorale < player.getMorale()) {
                                    getMorale().setStyle("-fx-text-inner-color: green;");
                                } else if (currentMorale > player.getMorale()) {
                                    getMorale().setStyle("-fx-text-inner-color: red;");
                                } else {
                                    getMorale().setStyle("-fx-text-inner-color: black;");
                                }
                                double currentIntegrity =
                                        Double.parseDouble(getIntegrity().getText());
                                if (currentIntegrity < player.getShelter().getIntegrity()) {
                                    getIntegrity().setStyle("-fx-text-inner-color: green;");
                                } else if (currentIntegrity > player.getShelter().getIntegrity()) {
                                    getIntegrity().setStyle("-fx-text-inner-color: red;");
                                } else {
                                    getIntegrity().setStyle("-fx-text-inner-color: black;");
                                }
                                double currentFirewood = Double.parseDouble(getFirewood().getText());
                                if (currentFirewood < player.getShelter().getFirewood()) {
                                    getFirewood().setStyle("-fx-text-inner-color: green;");
                                } else if (currentFirewood > player.getShelter().getFirewood()) {
                                    getFirewood().setStyle("-fx-text-inner-color: red;");
                                } else {
                                    getFirewood().setStyle("-fx-text-inner-color: black;");
                                }
                                int currentWater = Integer.parseInt(getWater().getText());
                                if (currentWater < player.getShelter().getWaterTank()) {
                                    getWater().setStyle("-fx-text-inner-color: green;");
                                } else if (currentWater > player.getShelter().getWaterTank()) {
                                    getWater().setStyle("-fx-text-inner-color: red;");
                                } else {
                                    getWater().setStyle("-fx-text-inner-color: black;");
                                }
                            } catch (Exception e) {
                            }
                        }
                        getWeight().setText(String.valueOf(HelperMethods.round(player.getWeight(), 1)));
                        getHydration().setText(String.valueOf(player.getHydration()));
                        getMorale().setText(String.valueOf(player.getMorale()));
                        getIntegrity().setText(String.valueOf((player.getShelter().getIntegrity())));
                        getFirewood().setText(String.valueOf((player.getShelter().getFirewood())));
                        getWater().setText(String.valueOf((player.getShelter().getWaterTank())));
                    }
                });

        // clear item in the list view
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getCarriedItems().getItems().clear();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        // add new carried items to items list view
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (Item item : player.getItems()) {
                                getCarriedItems().getItems().add(item.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        // clear food cache list view
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getFoodCache().getItems().clear();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        // add new food cache to food list view
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (Map.Entry<Food, Double> entry : player.getShelter().getFoodCache().entrySet()) {
                                getFoodCache().getItems()
                                        .add(HelperMethods.getLargestFoodUnit(entry.getValue()) + " " + entry.getKey());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        // clear equipment list view
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getEquipment().getItems().clear();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        // add new equipment to equipment list view
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (Map.Entry<Item, Integer> entry : player.getShelter().getEquipment().entrySet()) {
                                getEquipment().getItems().add(entry.getValue() + " " + entry.getKey().getType());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void getNarrative(File file) {
        try {
            String narrative = Files.lines(file.toPath()).collect(Collectors.joining("\n"));
             getCurActivity().appendText(narrative);
            //getCurActivity().appendText(narrative);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(
                    "Whoops! We seemed to have misplaced the next segment of the story. We're working on it!");
        }
    }
    public static PlayerStatus overnightStatusUpdate(Player player) {
        String result;
        SuccessRate successRate;
        Status status = STILL_ALIVE;
        double overnightPreparedness = player.getShelter().getIntegrity();
        if (player.getShelter().hasFire()) {
            overnightPreparedness += 10;
        }
        if (overnightPreparedness < 10) {
            successRate = SuccessRate.HIGH;
            result = "It was a long cold night. I have to light a fire tonight!";
            player.updateMorale(-3);
        } else if (overnightPreparedness < 17) {
            successRate = SuccessRate.MEDIUM;
            result =
                    "It was sure nice to have a fire last night, but this shelter doesn't provide much protection from the elements.";
            player.updateMorale(1);
        } else {
            successRate = SuccessRate.LOW;
            result =
                    "Last night was great! I feel refreshed and ready to take on whatever comes my way today.";
            player.updateMorale(2);
        }
        double caloriesBurned = ActivityLevel.MEDIUM.getCaloriesBurned(successRate);
        player.updateWeight(-caloriesBurned);
        if (player.getWeight() < 180.0 * 0.8) {
            result = result + " But you die of losing too much weight! /n/n Game Over!";
            status = STARVED;
        }
        int hydrationCost = ActivityLevel.MEDIUM.getHydrationCost(successRate);
        player.setHydration(player.getHydration() - hydrationCost);
        if (player.getHydration() < 0) {
            result = result + " But you die of thirst! /n/n Game Over!";
            status = DEHYDRATION;
        }
        player.getShelter().setFire(false);
        return new PlayerStatus(status,result);
    }

    // call from game logic thread to get the input
    public String getInput() {
        waitInput();
        return currentInput;
    }

    public void notifyInput() {
        synchronized (inputSignal) {
            inputSignal.notify();
        }
    }

    public void waitInput() {
        synchronized (inputSignal) {
            try {
                inputSignal.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* GETTERS AND SETTERS*/
    public TextField getWeight() {
        return weight;
    }

    public TextField getHydration() {
        return hydration;
    }

    public TextField getMorale() {
        return morale;
    }

    public ListView<String> getCarriedItems() {
        return carriedItems;
    }

    public TextField getIntegrity() {
        return integrity;
    }

    public TextField getFirewood() {
        return firewood;
    }

    public TextField getWater() {
        return water;
    }

    public ListView<String> getFoodCache() {
        return foodCache;
    }

    public ListView<String> getEquipment() {
        return equipment;
    }


    public TextArea getCurActivity() {
        return curActivity;
    }

    public TextArea getPlayerInput() {
        return playerInput;
    }

    public TextArea getDailyLog() {
        return dailyLog;
    }

    public TextField getDateAndTime() {
        return dateAndTime;
    }

    public Button getEnterButton() {
        return enterBtn;
    }

}
