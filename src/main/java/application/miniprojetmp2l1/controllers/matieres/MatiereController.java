package application.miniprojetmp2l1.controllers.matieres;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import application.miniprojetmp2l1.Storage;
import application.miniprojetmp2l1.controllers.IController;
import application.miniprojetmp2l1.dao.MatieresDao;
import application.miniprojetmp2l1.helpers.NavigationHelpers;
import application.miniprojetmp2l1.models.Matiere;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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

	@FXML
	public TextField idFilter;

	@FXML
	public TextField nomFilter;

	NavigationHelpers nh = new NavigationHelpers();

	private ObservableList<Matiere> data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadMatieres();
		initButtons();
		initFilters();
	}

	public void initFilters() {
		// add listener to the filter fields
		idFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
		nomFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
	}

	public void filter() {
		// filter the data based on the field values
		data = dao.filter(idFilter.getText(), nomFilter.getText());
		matieresList.setItems(data);
	}
	
	@FXML
	public void clearIdFilter(ActionEvent ignoredEv) {
		idFilter.clear();
	}
	
	@FXML
	public void clearNomFilter(ActionEvent ignoredEv) {
		nomFilter.clear();
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
	public void deleteButtonClicked(ActionEvent ignoredEvent) {
		if (selectedItem != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Supprimer matiere");
			alert.setContentText("Êtes-vous sûr de vouloir supprimer cette matiere ("+ selectedItem.getNom() + ") ?");
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
	public void updateButtonClicked(ActionEvent ignoredEvent) {
		if (selectedItem != null) {
			Pane ctrl;
			try {
				Storage.Matiere.id = selectedItem.getId();
				ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/miniprojetmp2l1/matieres/UpsertMatieres.fxml")));
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
	public void addButtonClicked(ActionEvent ignoredEv) {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/miniprojetmp2l1/matieres/UpsertMatieres.fxml")));
			nh.navigate(addButton, "Ajouter matiere", ctrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void goHome() {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/miniprojetmp2l1/dashboard/Dashboard.fxml")));
			nh.navigate(homeButton, "Dashboard", ctrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
