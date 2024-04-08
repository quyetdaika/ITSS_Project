package app.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowSaleOrderDetailApp extends Application {
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
