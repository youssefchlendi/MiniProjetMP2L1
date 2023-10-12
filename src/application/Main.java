package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import migrations.Init;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

	Init initDb = new Init();

	@Override
	public void start(Stage primaryStage) {
		try {
			initDb.run(true);
			Parent root = FXMLLoader.load(getClass().getResource("fxml/ManageEnseignants.fxml"));
			primaryStage.setTitle("Systeme de gestion des emplois");
			primaryStage.getIcons().add(new Image("file:/assets/icon.png"));
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			/*
			 * BorderPane root = new BorderPane(); Scene scene = new Scene(root,400,400);
			 * scene.getStylesheets().add(getClass().getResource("application.css").
			 * toExternalForm()); primaryStage.setScene(scene); primaryStage.show();
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
