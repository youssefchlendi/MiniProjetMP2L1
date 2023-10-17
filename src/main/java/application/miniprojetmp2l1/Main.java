package application.miniprojetmp2l1;

import application.miniprojetmp2l1.migrations.Init;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    Init initDb = new Init();

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            initDb.run(true);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard/Dashboard.fxml")));
            primaryStage.setTitle("Systeme de gestion des emplois");
            primaryStage.getIcons().add(new Image("file:/assets/icon.png"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}