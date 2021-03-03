package lk.ijse.loyaInstitute;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/Course.fxml"))));
//        primaryStage.show();
        Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("view/login.fxml")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
