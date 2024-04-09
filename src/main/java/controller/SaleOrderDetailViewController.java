package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import model.SaleOrderItem;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class SaleOrderDetailViewController implements Initializable {
    @FXML
    private TableView<SaleOrderItem> saleOrderDetailTable;

    @FXML
    private TableColumn<SaleOrderItem, String> idColumn;

    @FXML
    private TableColumn<SaleOrderItem, Integer> quantityColumn;

    @FXML
    private TableColumn<SaleOrderItem, Integer> unitColumn;

    @FXML
    private TableColumn<SaleOrderItem, Date> dateColumn;

    private ObservableList<SaleOrderItem> orderItems;

    @FXML
    private Button addItemBtn;

    @FXML
    private Button updateItemBtn;

    @FXML
    private Button deleteItemBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        orderItems = FXCollections.observableArrayList(
                new SaleOrderItem("S001", "P001", 500, 1, Date.valueOf("2024-05-01")),
                new SaleOrderItem("S001", "L002", 400, 1, Date.valueOf("2024-06-01")),
                new SaleOrderItem("S001", "P009", 250, 1, Date.valueOf("2024-05-06")),
                new SaleOrderItem("S001", "T001", 5200, 1, Date.valueOf("2024-04-29")),
                new SaleOrderItem("S001", "P006", 600, 1, Date.valueOf("2024-05-25"))
        );

        idColumn.setCellValueFactory(new PropertyValueFactory<SaleOrderItem, String>("merchandiseCode"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<SaleOrderItem, Integer>("quantityOrdered"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<SaleOrderItem, Integer>("unit"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<SaleOrderItem, Date>("desiredDeliveryDate"));

        saleOrderDetailTable.setItems(orderItems);

        saleOrderDetailTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(updateItemBtn != null) updateItemBtn.setVisible(newValue != null);
                    if(deleteItemBtn != null) deleteItemBtn.setVisible(newValue != null);
                }
        );
    }

    public void updateItemBtnClicked(ActionEvent e){
        SaleOrderItem selectedItem = saleOrderDetailTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("updateitemdialog.fxml"));
            DialogPane updateDialogPane = fxmlLoader.load();

            UpdateItemDialogController updateDialogController = fxmlLoader.getController();
            updateDialogController.setValue(selectedItem);
            updateDialogController.getDialog(updateDialogPane).showAndWait();

            if(updateDialogController.isUpdateBtnClicked()) {
                System.out.println("index : " + orderItems.indexOf(selectedItem));
                System.out.println("New item : " + updateDialogController.getSaleOrderItem());
                orderItems.set(orderItems.indexOf(selectedItem), updateDialogController.getSaleOrderItem());
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteItemBtnClicked(ActionEvent e) {
        SaleOrderItem selectedItem = saleOrderDetailTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.getDialogPane().setHeaderText("Do you want to Detele " + selectedItem.getMerchandiseCode() + " Item ?");
        alert.getDialogPane().setContentText(selectedItem.toString());

        Optional<ButtonType> choosen = alert.showAndWait();
        if(choosen.get() == ButtonType.OK){
            orderItems.remove(selectedItem);
        }
    }

    public void addItemBtnClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("additemdialog.fxml"));
            DialogPane updateDialogPane = fxmlLoader.load();

            AddItemDialogController addDialogController = fxmlLoader.getController();
            addDialogController.setValue();
            addDialogController.getDialog(updateDialogPane).showAndWait();

            if(addDialogController.isAddBtnClicked()) {
                System.out.println("New item : " + addDialogController.getSaleOrderItem());
                orderItems.add(addDialogController.getSaleOrderItem());
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
