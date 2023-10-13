package application.controllers.matieres;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Storage;
import application.controllers.IController;
import dao.MatieresDao;
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
import models.Matiere;

public class UpsertMatiereController implements Initializable, IController {

	MatieresDao dao = new MatieresDao();

	Matiere item;

	@FXML
	public Button cancelButton;

	@FXML
	public Button upsertButton;

	@FXML
	public TextField id;

	@FXML
	public TextField nom;

	NavigationHelpers nh = new NavigationHelpers();

	Alert alert = new Alert(AlertType.NONE);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Storage.Matiere.id != null && !Storage.Matiere.id.isBlank()) {
			try {
				item = dao.get(Storage.Matiere.id);
				id.setText(item.getId());
				nom.setText(item.getNom());
				id.setDisable(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("La matiere avec l'id " + Storage.Matiere.id + " est introuvable");
				alert.show();
				navigateToManageMatieres();
			}
		}
	}

	public void navigateToManageMatieres() {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(getClass().getResource("/application/fxml/matieres/ManageMatieres.fxml"));
			nh.navigate(cancelButton, "Gérer les matieres", ctrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void cancelButtonClicked(ActionEvent event) {
		Storage.Matiere.id = null;
		item = null;
		id.setText("");
		nom.setText("");
		navigateToManageMatieres();
	}

	@FXML
	public void upsertButtonClicked(ActionEvent event) {
		if (validateForm()) {
			if(Storage.Matiere.id != null) {
				try {
					dao.update(new Matiere(Storage.Matiere.id, nom.getText()));
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Matiere modifiée avec success");
					alert.show();
					navigateToManageMatieres();
				} catch (Exception e) {
					alert.setAlertType(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setContentText(
							"Matiere non ajoutée, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
					alert.show();
				}				
			}else {
				try {
					dao.add(new Matiere(id.getText(), nom.getText()));
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Matiere ajoutée avec success");
					alert.show();
					navigateToManageMatieres();
				} catch (Exception e) {
					alert.setAlertType(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setContentText(
							"Matiere non ajoutée, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
					alert.show();
				}
			}
		}
	}

	public Boolean validateForm() {
		alert.setAlertType(AlertType.WARNING);
		alert.setTitle("Invalide");
		if (id.getText().isBlank()) {
			alert.setContentText("Merci d'entrer la id de matiere");
			alert.show();
			return false;
		}
		if (nom.getText().isBlank()) {
			alert.setContentText("Merci d'entrer le nom de matiere");
			alert.show();
			return false;
		}

		return true;
	}

}
