package app.javafx;

import controller.UpdateDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ShowAllSaleOrderApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShowAllSaleOrderApp.class.getResource("saleorderdetail-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sale Order Detail");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
