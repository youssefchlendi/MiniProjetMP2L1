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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class EnseignantController implements Initializable, IController {

	EnseignantDao dao = new EnseignantDao();

	Enseignant selectedItem;

	TableView.TableViewSelectionModel<Enseignant> selectionModel;

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

	@FXML
	public TextField matriculeFilter;

	@FXML
	public TextField nomFilter;

	@FXML
	public TextField contactFilter;

	NavigationHelpers nh = new NavigationHelpers();

	private ObservableList<Enseignant> data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadTeachers();
		initButtons();
		initFilters();
	}

	private void initFilters() {
		// add listener to the filter fields
		matriculeFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
		nomFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
		contactFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
	}

	public void filter() {
		data = dao.filter(matriculeFilter.getText(), nomFilter.getText(), contactFilter.getText());
		enseignantsList.setItems(data);
	}

	@FXML
	public void clearMatriculeFilter(ActionEvent ignoredEv) {
		matriculeFilter.clear();
	}

	@FXML
	public void clearNomFilter(ActionEvent ignoredEv) {
		nomFilter.clear();
	}

	@FXML
	public void clearContactFilter(ActionEvent ignoredEv) {
		contactFilter.clear();
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
	public void deleteButtonClicked(ActionEvent ignoredEvent) {
		if (selectedItem != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Supprimer enseignant");
			alert.setContentText("Êtes-vous sûr de vouloir supprimer cet enseignant ("+ selectedItem.getNom() + ") ?");
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
	public void updateButtonClicked(ActionEvent ignoredEvent) {
		if (selectedItem != null) {
			Pane ctrl;
			try {
				Storage.Enseignant.id = selectedItem.getMatricule();
				ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/miniprojetmp2l1/enseignants/UpsertEnseignant.fxml")));
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
	public void addButtonClicked(ActionEvent ignoredEv) {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/miniprojetmp2l1/enseignants/UpsertEnseignant.fxml")));
			nh.navigate(addButton, "Ajouter enseignant", ctrl);
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
