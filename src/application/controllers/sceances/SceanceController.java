package application.controllers.sceances;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Storage;
import application.controllers.IController;
import dao.SceancesDao;
import helpers.NavigationHelpers;
import javafx.beans.property.SimpleStringProperty;
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
import models.Sceance;

public class SceanceController implements Initializable, IController {

	SceancesDao dao = new SceancesDao();

	Sceance selectedItem;

	TableViewSelectionModel<Sceance> selectionModel;

	@FXML
	private TableView<Sceance> sceancesList;

	@FXML
	public TableColumn<Sceance, String> id;
	
	@FXML
	public TableColumn<Sceance, String> jour;

	@FXML
	public TableColumn<Sceance, String> heureDebut;

	@FXML
	public TableColumn<Sceance, String> heureFin;

	@FXML
	public TableColumn<Sceance, String> classe;

	@FXML
	public TableColumn<Sceance, String> matiere;

	@FXML
	public TableColumn<Sceance, String> enseignant;

	@FXML
	public Button deleteButton;

	@FXML
	public Button updateButton;

	@FXML
	public Button addButton;

	NavigationHelpers nh = new NavigationHelpers();

	private ObservableList<Sceance> data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadSceances();
		initButtons();
	}

	private void initButtons() {
		deleteButton.setVisible(false);
		updateButton.setVisible(false);
	}

	@FXML
	public void clickItem(MouseEvent event) {
		event.consume();
		Sceance temp = selectionModel.getSelectedItem();
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
			alert.setTitle("Supprimer sceance");
			alert.setContentText("Êtes-vous sûr de vouloir supprimer cette sceance (" + selectedItem.getId() + ") ?");
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
				try {
					dao.delete(selectedItem.getId());
					loadSceances();
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
				Storage.Sceance.id = selectedItem.getId();
				ctrl = FXMLLoader.load(getClass().getResource("/application/fxml/sceances/UpsertSceances.fxml"));
				nh.navigate(addButton, "Modifier sceance", ctrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadSceances() {
		try {
			data = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		jour.setCellValueFactory(new PropertyValueFactory<>("jour"));
		heureDebut.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
		heureFin.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
		classe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClasse().getNom()));
		matiere.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatiere().getNom()));
		enseignant.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEnseignant().getNom()));
		sceancesList.setItems(data);
		selectionModel = sceancesList.getSelectionModel();
	}

	@FXML
	public void addButtonClicked(ActionEvent ev) {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(getClass().getResource("/application/fxml/sceances/UpsertSceances.fxml"));
			nh.navigate(addButton, "Ajouter sceance", ctrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
