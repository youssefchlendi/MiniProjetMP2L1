package application.miniprojetmp2l1.controllers.classes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import application.miniprojetmp2l1.Storage;
import application.miniprojetmp2l1.controllers.IController;
import application.miniprojetmp2l1.dao.ClassesDao;
import application.miniprojetmp2l1.helpers.NavigationHelpers;
import application.miniprojetmp2l1.models.Classe;
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

public class UpsertClasseController implements Initializable, IController {

	ClassesDao dao = new ClassesDao();

	Classe item;

	@FXML
	public Button cancelButton;

	@FXML
	public Button upsertButton;

	@FXML
	public TextField matricule;

	@FXML
	public TextField nom;

	@FXML
	public Label title;

	NavigationHelpers nh = new NavigationHelpers();

	Alert alert = new Alert(AlertType.NONE);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Storage.Classe.id != null && !Storage.Classe.id.isBlank()) {
			try {
				item = dao.get(Storage.Classe.id);
				matricule.setText(item.getMatricule());
				nom.setText(item.getNom());
				matricule.setDisable(true);
				title.setText("Modifier la classe");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("La classe avec l'id " + Storage.Classe.id + " est introuvable");
				alert.show();
				navigateToManageClasses();
			}
		}else {
			title.setText("Ajouter une classe");
		}
	}

	public void navigateToManageClasses() {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/miniprojetmp2l1/classes/ManageClasses.fxml")));
			nh.navigate(cancelButton, "Gérer les classes", ctrl);
			clearData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void cancelButtonClicked(ActionEvent ignoredEvent) {
		navigateToManageClasses();
	}

	@FXML
	public void upsertButtonClicked(ActionEvent ignoredEvent) {
		if (validateForm()) {
			if(Storage.Classe.id != null) {
				try {
					dao.update(new Classe(Storage.Classe.id, nom.getText()));
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Classe modifiée avec success");
					alert.show();
					navigateToManageClasses();
				} catch (Exception e) {
					alert.setAlertType(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setContentText(
							"Classe non ajoutée, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
					alert.show();
				}				
			}else {
				try {
					dao.add(new Classe(matricule.getText(), nom.getText()));
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Classe ajoutée avec success");
					alert.show();
					navigateToManageClasses();
				} catch (Exception e) {
					alert.setAlertType(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setContentText(
							"Classe non ajoutée, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
					alert.show();
				}
			}
		}
	}

	private void clearData(){
		Storage.Classe.id = null;
		item = null;
		matricule.setText("");
		nom.setText("");
	}

	public Boolean validateForm() {
		alert.setAlertType(AlertType.WARNING);
		alert.setTitle("Invalide");
		if (matricule.getText().isBlank()) {
			alert.setContentText("Merci d'entrer la matricule de classe");
			alert.show();
			return false;
		}
		if (nom.getText().isBlank()) {
			alert.setContentText("Merci d'entrer le nom de classe");
			alert.show();
			return false;
		}

		return true;
	}

}
