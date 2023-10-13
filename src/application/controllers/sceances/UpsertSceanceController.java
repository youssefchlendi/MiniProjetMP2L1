package application.controllers.sceances;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Storage;
import application.controllers.IController;
import dao.SceancesDao;
import helpers.NavigationHelpers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import models.Sceance;

public class UpsertSceanceController implements Initializable, IController {

	SceancesDao dao = new SceancesDao();

	Sceance item;

	@FXML
	public Button cancelButton;

	@FXML
	public Button upsertButton;

	@FXML
	public TextField id;

	NavigationHelpers nh = new NavigationHelpers();

	Alert alert = new Alert(AlertType.NONE);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Storage.Sceance.id != null) {
			try {
				item = dao.get(Storage.Sceance.id);
				id.setText(item.getId().toString());
				id.setDisable(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("La sceance avec l'id " + Storage.Sceance.id + " est introuvable");
				alert.show();
				navigateToManageSceances();
			}
		}
	}

	public void navigateToManageSceances() {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(getClass().getResource("/application/fxml/sceances/ManageSceances.fxml"));
			nh.navigate(cancelButton, "Gérer les sceances", ctrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void cancelButtonClicked(ActionEvent event) {
		Storage.Sceance.id = null;
		item = null;
		id.setText("");
		navigateToManageSceances();
	}

	@FXML
	public void upsertButtonClicked(ActionEvent event) {
		if (validateForm()) {
			if(Storage.Sceance.id != null) {
				try {
					dao.update(new Sceance());
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Sceance modifiée avec success");
					alert.show();
					navigateToManageSceances();
				} catch (Exception e) {
					alert.setAlertType(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setContentText(
							"Sceance non ajoutée, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
					alert.show();
				}				
			}else {
				try {
					dao.add(new Sceance());
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Sceance ajoutée avec success");
					alert.show();
					navigateToManageSceances();
				} catch (Exception e) {
					alert.setAlertType(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setContentText(
							"Sceance non ajoutée, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
					alert.show();
				}
			}
		}
	}

	public Boolean validateForm() {
		alert.setAlertType(AlertType.WARNING);
		alert.setTitle("Invalide");
		if (id.getText().isBlank()) {
			alert.setContentText("Merci d'entrer la id de sceance");
			alert.show();
			return false;
		}

		return true;
	}

}
