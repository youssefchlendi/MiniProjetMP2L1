package application.controllers.enseignants;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Storage;
import application.controllers.*;
import dao.EnseignantDao;
import helpers.NavigationHelpers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.Enseignant;

public class EnseignantController implements Initializable, IController {

	EnseignantDao dao = new EnseignantDao();

	Enseignant selectedItem;

	TableViewSelectionModel<Enseignant> selectionModel;

	@FXML
	private TableView<Enseignant> enseignantsList;

	@FXML
	public TableColumn<Enseignant, String> matricule;

	@FXML
	public TableColumn<Enseignant, String> nom;

	@FXML
	public TableColumn<Enseignant, String> contact;

	@FXML
	public Button deleteButton;

	@FXML
	public Button updateButton;

	@FXML
	public Button addButton;
	
	@FXML
	public Button homeButton;

	NavigationHelpers nh = new NavigationHelpers();

	private ObservableList<Enseignant> data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadTeachers();
		initButtons();
	}

	private void initButtons() {
		deleteButton.setVisible(false);
		updateButton.setVisible(false);
	}

	@FXML
	public void clickItem(MouseEvent event) {
		event.consume();
		Enseignant temp = selectionModel.getSelectedItem();
		if (temp != null) {
			selectedItem = temp;
			deleteButton.setVisible(true);
			updateButton.setVisible(true);
		}
	}

	@FXML
	public void deleteButtonClicked(ActionEvent event) {
		if (selectedItem != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Supprimer enseignant");
			alert.setContentText("Êtes-vous sûr de vouloir supprimer cet enseignant (" + selectedItem.getNom() + ") ?");
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
				try {
					dao.delete(selectedItem.getMatricule());
					loadTeachers();
					selectionModel.clearSelection();
					deleteButton.setVisible(false);
					updateButton.setVisible(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}
	}

	@FXML
	public void updateButtonClicked(ActionEvent event) {
		if (selectedItem != null) {
			Pane ctrl;
			try {
				Storage.Enseignant.id = selectedItem.getMatricule();
				ctrl = FXMLLoader.load(getClass().getResource("/application/fxml/enseignants/UpsertEnseignant.fxml"));
				nh.navigate(addButton, "Modifier enseignant", ctrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadTeachers() {
		try {
			data = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		matricule.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
		nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
		contact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
		enseignantsList.setItems(data);
		selectionModel = enseignantsList.getSelectionModel();
	}

	@FXML
	public void addButtonClicked(ActionEvent ev) {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(getClass().getResource("/application/fxml/enseignants/UpsertEnseignant.fxml"));
			nh.navigate(addButton, "Ajouter enseignant", ctrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goHome() {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(getClass().getResource("/application/fxml/dashboard/Dashboard.fxml"));
			nh.navigate(homeButton, "Dashboard", ctrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
