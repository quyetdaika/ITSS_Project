package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import model.SaleOrderItem;

import java.sql.Date;

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

    public void quantityTfEntered(ActionEvent actionEvent){
        try {
            int quantity = Integer.parseInt(quantityTf.getText());
            quantityErrLbl.setVisible(quantity <= 0);
        } catch (NumberFormatException ex) {
            quantityErrLbl.setVisible(true);
            throw new RuntimeException(ex);
        }
    }

    public void unitTfEntered(ActionEvent actionEvent) {
        try {
            int unit = Integer.parseInt(unitTf.getText());
            unitErrLbl.setVisible(unit <= 0);
        } catch (NumberFormatException ex) {
            unitErrLbl.setVisible(true);
            throw new RuntimeException(ex);
        }
    }

    public void dateTfEntered(ActionEvent actionEvent) {
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

    public void updateBtnClicked(ActionEvent actionEvent) {
    }

    public void cancelBtnClicked(ActionEvent actionEvent) {
    }

    public void setValue(SaleOrderItem selectedItem) {
        codeLbl.setText(selectedItem.getMerchandiseCode());
        quantityTf.setText(String.valueOf(selectedItem.getQuantityOrdered()));
        unitTf.setText(String.valueOf(selectedItem.getUnit()));
        dateTf.setText(String.valueOf(selectedItem.getDesiredDeliveryDate()));
    }
}
