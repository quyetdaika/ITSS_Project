package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.SaleOrderItem;

import java.sql.Date;
import java.util.Optional;

public class AddItemDialogController {
    @FXML
    public Button addBtn;
    public Button cancelBtn;
    public TextField codeTf;
    public Label codeErrLbl;

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
    private SaleOrderItem saleOrderItem;

    private Dialog updateItemDialog;

    private boolean addBtnClicked;

    public Dialog getDialog(DialogPane updateDialogPane){
        updateItemDialog  = new Dialog();
        updateItemDialog.setTitle("Update Sale Order Item");
        updateItemDialog.setDialogPane(updateDialogPane);

        Window window = updateItemDialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> updateItemDialog.close());

        return updateItemDialog;
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

    public void codeTfEntered(ActionEvent actionEvent) {
        checkMerchandiseCode();
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

    public boolean isAddBtnClicked() {
        return addBtnClicked;
    }

    private String getNewItemString() {
        String changedValue = "";
        changedValue += "Merchandise code : " + codeTf.getText() + "\n";
        changedValue += "Quantity : " + Integer.parseInt(quantityTf.getText()) + "\n";
        changedValue += "Unit : " + Integer.parseInt(unitTf.getText()) + "\n";
        changedValue += "Desired Delivery Date : " + Date.valueOf(dateTf.getText()) + "\n";
        return changedValue;
    }

    private void updateItemNewValue() {
        this.saleOrderItem.setMerchandiseCode(codeTf.getText());
        this.saleOrderItem.setQuantityOrdered(Integer.parseInt(quantityTf.getText()));
        this.saleOrderItem.setUnit(Integer.parseInt(unitTf.getText()));
        this.saleOrderItem.setDesiredDeliveryDate(Date.valueOf(dateTf.getText()));
    }

    public void addBtnClicked(ActionEvent actionEvent) {
        if(checkMerchandiseCode() && checkQuantity() && checkUnit() && checkDate()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Do you want to Add this new item ?");
            String alertContentText = getNewItemString();
            alert.getDialogPane().setContentText(alertContentText);

            Optional<ButtonType> choosen = alert.showAndWait();
            if(choosen.get() == ButtonType.OK){
                updateItemNewValue();
                addBtnClicked = true;
                closeDialog();
            }
        }
    }

    public void cancelBtnClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.getDialogPane().setHeaderText("Do you want to Cancel ?");
        alert.getDialogPane().setContentText("Your process will be discard");

        Optional<ButtonType> choosen = alert.showAndWait();
        if(choosen.get() == ButtonType.OK){
            closeDialog();
        }

    }

    public boolean checkMerchandiseCode(){
        boolean checkRes = false;
        try {
            String merchandiseCode = codeTf.getText();
            checkRes = !merchandiseCode.isEmpty();
            codeErrLbl.setVisible(merchandiseCode.isEmpty());
        } catch (NumberFormatException ex) {
            codeTf.setVisible(true);
//            throw new RuntimeException(ex);
        }
        return checkRes;
    }

    public boolean checkQuantity(){
        boolean checkRes = false;
        try {
            int quantity = Integer.parseInt(quantityTf.getText());
            checkRes = quantity > 0;
            quantityErrLbl.setVisible(quantity <= 0);
        } catch (NumberFormatException ex) {
            quantityErrLbl.setVisible(true);
//            throw new RuntimeException(ex);
        }
        return checkRes;
    }

    public boolean checkUnit(){
        boolean checkRes = false;
        try {
            int unit = Integer.parseInt(unitTf.getText());
            checkRes = unit > 0;
            unitErrLbl.setVisible(unit <= 0);
        } catch (NumberFormatException ex) {
            unitErrLbl.setVisible(true);
//            throw new RuntimeException(ex);
        }
        return checkRes;
    }

    public boolean checkDate(){
        boolean checkRes = false;
        try {
            Date date = Date.valueOf(dateTf.getText());
            Date currentDate = new Date(System.currentTimeMillis()); // get the current date

            checkRes = date.after(currentDate);
            // Compare the dates
            dateErrLbl.setVisible(currentDate.after(date));
        } catch (Exception e) {
            dateErrLbl.setVisible(true);
//            throw new RuntimeException(e);
        }
        return checkRes;
    }

    public void setValue() {
        this.saleOrderItem = new SaleOrderItem();

        codeTf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkMerchandiseCode();
            }
        });

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

        addBtnClicked = false;
    }
}
