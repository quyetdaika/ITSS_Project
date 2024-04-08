package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.SaleOrderItem;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

public class UpdateDialogController {
    @FXML
    private TextField quantityTf;

    @FXML
    private TextField unitTf;

    @FXML
    private TextField dateTf;

    @FXML
    private Label codeLbl;

    @FXML
    private Label quantityErrLbl;

    @FXML
    private Label unitErrLbl;

    @FXML
    private Label dateErrLbl;

    @FXML
    private Button updateBtn;

    @FXML
    private Button cancelBtn;

    private SaleOrderItem saleOrderItem;

    private Dialog updateItemDialog;

    public void showDialog(DialogPane updateDialogPane){
        updateItemDialog  = new Dialog();
        updateItemDialog.setTitle("Update Sale Order Item");
        updateItemDialog.setDialogPane(updateDialogPane);

        Window window = updateItemDialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> updateItemDialog.close());

        updateItemDialog.show();
    }

    public void closeDialog(){
        Stage dialogStage = (Stage) updateItemDialog.getDialogPane().getScene().getWindow();
        dialogStage.close();
    }

    public SaleOrderItem getSaleOrderItem() {
        return saleOrderItem;
    }

    public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
        this.saleOrderItem = saleOrderItem;
    }

    public void quantityTfEntered(ActionEvent actionEvent){
        checkQuantity();
    }

    public void unitTfEntered(ActionEvent actionEvent) {
        checkUnit();
    }

    public void dateTfEntered(ActionEvent actionEvent) {
        checkDate();
    }

    public void updateBtnClicked(ActionEvent actionEvent) {
    }

    public void cancelBtnClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.getDialogPane().setHeaderText("Do you want to Cancel ?");
        alert.getDialogPane().setContentText("Your process will be discard");
//        alert.initOwner();
        Optional<ButtonType> choosen = alert.showAndWait();
        if(choosen.get() == ButtonType.OK){
            closeDialog();
        }

    }

    public void checkQuantity(){
        try {
            int quantity = Integer.parseInt(quantityTf.getText());
            quantityErrLbl.setVisible(quantity <= 0);
        } catch (NumberFormatException ex) {
            quantityErrLbl.setVisible(true);
            throw new RuntimeException(ex);
        }
    }

    public void checkUnit(){
        try {
            int unit = Integer.parseInt(unitTf.getText());
            unitErrLbl.setVisible(unit <= 0);
        } catch (NumberFormatException ex) {
            unitErrLbl.setVisible(true);
            throw new RuntimeException(ex);
        }
    }

    public void checkDate(){
        try {
            Date date = Date.valueOf(dateTf.getText());
            Date currentDate = new Date(System.currentTimeMillis()); // get the current date

            // Compare the dates
            dateErrLbl.setVisible(currentDate.after(date));
        } catch (Exception e) {
            dateErrLbl.setVisible(true);
            throw new RuntimeException(e);
        }
    }

    public void setValue(SaleOrderItem selectedItem) {
        saleOrderItem = selectedItem;
        codeLbl.setText(selectedItem.getMerchandiseCode());
        quantityTf.setText(String.valueOf(selectedItem.getQuantityOrdered()));
        unitTf.setText(String.valueOf(selectedItem.getUnit()));
        dateTf.setText(String.valueOf(selectedItem.getDesiredDeliveryDate()));

        quantityTf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkQuantity();
            }
        });

        unitTf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkUnit();
            }
        });

        dateTf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkDate();
            }
        });
    }
}
