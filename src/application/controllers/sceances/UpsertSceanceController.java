package application.controllers.sceances;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;

import application.Storage;
import application.controllers.IController;
import dao.ClassesDao;
import dao.EnseignantDao;
import dao.MatieresDao;
import dao.SceancesDao;
import helpers.NavigationHelpers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import models.Classe;
import models.Enseignant;
import models.Matiere;
import models.Sceance;

public class UpsertSceanceController implements Initializable, IController {

	SceancesDao dao = new SceancesDao();
	MatieresDao mdao = new MatieresDao();
	ClassesDao cdao = new ClassesDao();
	EnseignantDao edao = new EnseignantDao();

	Sceance item;

	@FXML
	public Button cancelButton;

	@FXML
	public Button upsertButton;

	@FXML
	public ComboBox<String> jour;

	@FXML
	public TextField heureDebut;

	@FXML
	public TextField heureFin;

	@FXML
	public ComboBox<Classe> classes;

	@FXML
	public ComboBox<Matiere> matieres;

	@FXML
	public ComboBox<Enseignant> enseignants;

	NavigationHelpers nh = new NavigationHelpers();

	Alert alert = new Alert(AlertType.NONE);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		jour.getItems().addAll(Storage.Sceance.days);

		try {
			initComboBoxes();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		initItem();
	}

	private void initItem() {
		if (Storage.Sceance.id != null) {
			try {
				item = dao.get(Storage.Sceance.id);
				jour.setValue(item.getJour());
				heureDebut.setText(item.getHeureDebut().toString());
				heureFin.setText(item.getHeureFin().toString());
				classes.setValue(item.getClasse());
				matieres.setValue(item.getMatiere());
				enseignants.setValue(item.getEnseignant());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("La sceance avec l'id " + Storage.Sceance.id + " est introuvable");
				alert.show();
				navigateToManageSceances();
			}
		}
	}

	private void initComboBoxes() throws SQLException {
		classes.getItems().addAll(cdao.getAll());
		matieres.getItems().addAll(mdao.getAll());
		enseignants.getItems().addAll(edao.getAll());

		classes.setConverter(new StringConverter<Classe>() {
			@Override
			public String toString(Classe object) {
				return (object != null) ? object.getNom() : "";
			}

			@Override
			public Classe fromString(String string) {
				// Implement if needed
				return null;
			}
		});

		matieres.setConverter(new StringConverter<Matiere>() {
			@Override
			public String toString(Matiere object) {
				return (object != null) ? object.getNom() : "";
			}

			@Override
			public Matiere fromString(String string) {
				// Implement if needed
				return null;
			}
		});

		enseignants.setConverter(new StringConverter<Enseignant>() {
			@Override
			public String toString(Enseignant object) {
				return (object != null) ? object.getNom() : "";
			}

			@Override
			public Enseignant fromString(String string) {
				// Implement if needed
				return null;
			}
		});
	}

	public void navigateToManageSceances() {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(getClass().getResource("/application/fxml/sceances/ManageSceances.fxml"));
			nh.navigate(cancelButton, "Gérer les sceances", ctrl);
			clearData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void cancelButtonClicked(ActionEvent event) {
		navigateToManageSceances();
		clearData();
	}

	@FXML
	public void upsertButtonClicked(ActionEvent event) {
		if (validateForm()) {
			if (Storage.Sceance.id != null) {
				try {
					dao.update(new Sceance(item.getId(), jour.getValue(), LocalTime.parse(heureDebut.getText()), LocalTime.parse(heureFin.getText()),
							classes.getValue(), matieres.getValue(), enseignants.getValue()));
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Sceance modifiée avec success");
					alert.show();
					navigateToManageSceances();
				} catch (Exception e) {
					alert.setAlertType(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setContentText(
							"Sceance non modifiée, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
					alert.show();
				}
			} else {
				try {
					dao.add(new Sceance(jour.getValue(), LocalTime.parse(heureDebut.getText()), LocalTime.parse(heureFin.getText()), classes.getValue(),
							matieres.getValue(), enseignants.getValue()));
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
					e.printStackTrace();
				}
			}
		}
	}

	private void clearData(){
		Storage.Sceance.id = null;
		item = null;
		jour.setValue(null);
		heureDebut.setText("");
		heureFin.setText("");
		classes.setValue(null);
		matieres.setValue(null);
		enseignants.setValue(null);
	}

	public Boolean validateForm() {
		alert.setAlertType(AlertType.WARNING);
		alert.setTitle("Invalide");

		if (jour.getValue() == null) {
			alert.setContentText("Veuillez choisir un jour");
			alert.show();
			return false;
		}

		if (heureDebut.getText().isEmpty()) {
			alert.setContentText("Veuillez entrer une heure de début");
			alert.show();
			return false;
		}

		if (heureFin.getText().isEmpty()) {
			alert.setContentText("Veuillez entrer une heure de fin");
			alert.show();
			return false;
		}

		try {
			LocalTime.parse(heureDebut.getText());
		} catch (Exception e) {
			alert.setContentText("Veuillez entrer une heure de début valide");
			alert.show();
			return false;
		}

		try {
			LocalTime.parse(heureFin.getText());
		} catch (Exception e) {
			alert.setContentText("Veuillez entrer une heure de fin valide");
			alert.show();
			return false;
		}

		if (LocalTime.parse(heureDebut.getText()).isAfter(LocalTime.parse(heureFin.getText()))) {
			alert.setContentText("L'heure de début doit être avant l'heure de fin");
			alert.show();
			return false;
		}

		if (classes.getValue() == null) {
			alert.setContentText("Veuillez choisir une classe");
			alert.show();
			return false;
		}

		if (matieres.getValue() == null) {
			alert.setContentText("Veuillez choisir une matière");
			alert.show();
			return false;
		}

		if (enseignants.getValue() == null) {
			alert.setContentText("Veuillez choisir un enseignant");
			alert.show();
			return false;
		}

		return true;
	}

}
