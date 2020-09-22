package com.palehorsestudios.alone.gui.controller;

import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.gui.GameManager;
import com.palehorsestudios.alone.gui.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CraftItemsWindowController extends BaseController implements Initializable {

    @FXML
    private GridPane itemsPane;

    public CraftItemsWindowController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCraftItems(4);
    }


    private void addCraftItems(int columns) {
        List<Item> craftableItems = ItemFactory.getAllItems()
                .stream()
                .filter(Item::isCraftable)
                .collect(Collectors.toList());
        int row = 0, col = 0;
        for (Item item: craftableItems) {
            Button craftable = new Button(item.getType());
            craftable.setTooltip(new Tooltip(item.getDescription()));
            itemsPane.add(craftable,col, row);

            col = ++col % columns;
            row = col == 0 ? ++row : row;
        }
    }
}
