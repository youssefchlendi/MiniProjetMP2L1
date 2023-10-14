package application.controllers.matieres;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Storage;
import application.controllers.*;
import dao.MatieresDao;
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
import models.Matiere;

public class MatiereController implements Initializable, IController {

	MatieresDao dao = new MatieresDao();

	Matiere selectedItem;

	TableViewSelectionModel<Matiere> selectionModel;

	@FXML
	private TableView<Matiere> matieresList;

	@FXML
	public TableColumn<Matiere, String> id;

	@FXML
	public TableColumn<Matiere, String> nom;

	@FXML
	public Button deleteButton;

	@FXML
	public Button updateButton;

	@FXML
	public Button addButton;
	
	@FXML
	public Button homeButton;

	NavigationHelpers nh = new NavigationHelpers();

	private ObservableList<Matiere> data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadMatieres();
		initButtons();
	}

	private void initButtons() {
		deleteButton.setVisible(false);
		updateButton.setVisible(false);
	}

	@FXML
	public void clickItem(MouseEvent event) {
		event.consume();
		Matiere temp = selectionModel.getSelectedItem();
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
			alert.setTitle("Supprimer matiere");
			alert.setContentText("Êtes-vous sûr de vouloir supprimer cette matiere (" + selectedItem.getNom() + ") ?");
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
				try {
					dao.delete(selectedItem.getId());
					loadMatieres();
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
				Storage.Matiere.id = selectedItem.getId();
				ctrl = FXMLLoader.load(getClass().getResource("/application/fxml/matieres/UpsertMatieres.fxml"));
				nh.navigate(addButton, "Modifier matiere", ctrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadMatieres() {
		try {
			data = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
		matieresList.setItems(data);
		selectionModel = matieresList.getSelectionModel();
	}

	@FXML
	public void addButtonClicked(ActionEvent ev) {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(getClass().getResource("/application/fxml/matieres/UpsertMatieres.fxml"));
			nh.navigate(addButton, "Ajouter matiere", ctrl);
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
