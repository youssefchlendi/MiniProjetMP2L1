package helpers;

import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NavigationHelpers {
	public void navigate(Control ctrl, String title, Pane parentNode) {
		Stage stage = (Stage) ctrl.getScene().getWindow();
		Scene scene = new Scene(parentNode);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}
}
