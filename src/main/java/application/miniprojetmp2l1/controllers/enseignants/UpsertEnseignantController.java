package application.miniprojetmp2l1.controllers.enseignants;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import application.miniprojetmp2l1.Storage;
import application.miniprojetmp2l1.controllers.IController;
import application.miniprojetmp2l1.dao.EnseignantDao;
import application.miniprojetmp2l1.helpers.NavigationHelpers;
import application.miniprojetmp2l1.models.Enseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class UpsertEnseignantController implements Initializable, IController {

	EnseignantDao dao = new EnseignantDao();

	Enseignant item;

	@FXML
	public Button cancelButton;

	@FXML
	public Button upsertButton;

	@FXML
	public TextField matricule;

	@FXML
	public TextField nom;

	@FXML
	public TextField contact;

	@FXML
	public Label title;

	NavigationHelpers nh = new NavigationHelpers();

	Alert alert = new Alert(AlertType.NONE);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Storage.Enseignant.id != null && !Storage.Enseignant.id.isBlank()) {
			try {
				item = dao.get(Storage.Enseignant.id);
				matricule.setText(item.getMatricule());
				nom.setText(item.getNom());
				contact.setText(item.getContact());
				matricule.setDisable(true);
				title.setText("Modifier l'enseignant");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("L'utilisateur avec l'id " + Storage.Enseignant.id + " est introuvable");
				alert.show();
				navigateToManageEnseignants();
			}
		}else {
			title.setText("Ajouter un enseignant");
		}
	}

	public void navigateToManageEnseignants() {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/miniprojetmp2l1/enseignants/ManageEnseignants.fxml")));
			nh.navigate(cancelButton, "Gérer les enseignants", ctrl);
			clearData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void cancelButtonClicked(ActionEvent ignoredEvent) {
		navigateToManageEnseignants();
	}

	@FXML
	public void upsertButtonClicked(ActionEvent ignoredEvent) {
		if (validateForm()) {
			if(Storage.Enseignant.id != null) {
				try {
					dao.update(new Enseignant(Storage.Enseignant.id, nom.getText(), contact.getText()));
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Enseignant modifié avec success");
					alert.show();
					navigateToManageEnseignants();
				} catch (Exception e) {
					alert.setAlertType(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setContentText(
							"Enseignant non ajouté, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
					alert.show();
				}				
			}else {
				try {
					dao.add(new Enseignant(matricule.getText(), nom.getText(), contact.getText()));
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Enseignant ajouté avec success");
					alert.show();
					navigateToManageEnseignants();
				} catch (Exception e) {
					alert.setAlertType(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setContentText(
							"Enseignant non ajouté, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
					alert.show();
				}
			}
		}
	}

	public void clearData(){
		Storage.Enseignant.id = null;
		item = null;
		matricule.setText("");
		nom.setText("");
		contact.setText("");
	}

	public Boolean validateForm() {
		alert.setAlertType(AlertType.WARNING);
		alert.setTitle("Invalide");
		if (matricule.getText().isBlank()) {
			alert.setContentText("Merci d'entrer la matricule de l'enseignant");
			alert.show();
			return false;
		}
		if (nom.getText().isBlank()) {
			alert.setContentText("Merci d'entrer le nom de l'enseignant");
			alert.show();
			return false;
		}
		if (contact.getText().isBlank()) {
			alert.setContentText("Merci d'entrer le contact de l'enseignant");
			alert.show();
			return false;
		}
		return true;
	}

}
