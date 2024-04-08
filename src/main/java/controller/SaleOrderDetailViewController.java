package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Window;
import model.SaleOrderItem;
import mysqlsubsystem.MySQLSaleOrderDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
    private Button updateItemBtn;

    @FXML
    private Button deteleItemBtn;

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
    }

    public void updateItemBtnClicked(ActionEvent e){
        SaleOrderItem selectedItem = saleOrderDetailTable.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("updatedialog.fxml"));
            DialogPane updateDialogPane = fxmlLoader.load();

            UpdateDialogController updateDialogController = fxmlLoader.getController();
            updateDialogController.setValue(selectedItem);
            updateDialogController.showDialog(updateDialogPane);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    private void quantityHandler() {
    }

}
