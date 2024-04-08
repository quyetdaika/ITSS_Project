package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.SaleOrder;
import mysqlsubsystem.MySQLSaleOrderDB;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SaleOrderViewController implements Initializable {
    @FXML
    private TableView<SaleOrder> saleOrderTable;

    @FXML
    private TableColumn<SaleOrder, String> idColumn;

    @FXML
    private TableColumn<SaleOrder, Date> dateColumn;

    @FXML
    private TableColumn<SaleOrder, String> statusColumn;

    private ObservableList<SaleOrder> saleOrders;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saleOrders = FXCollections.observableArrayList(new MySQLSaleOrderDB().getSaleOrders());

        idColumn.setCellValueFactory(new PropertyValueFactory<SaleOrder, String>("saleOrderID"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<SaleOrder, Date>("orderedDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<SaleOrder, String>("status"));

        saleOrderTable.setItems(saleOrders);
    }
}
