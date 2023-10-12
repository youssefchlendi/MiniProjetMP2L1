package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.EnseignantDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Enseignant;

public class EnseignantController implements Initializable {

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
	public Button addButton;

	private ObservableList<Enseignant> data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadStudents();
		initButtons();
	}

	private void initButtons() {
		deleteButton.setVisible(false);
	}

	@FXML
	public void clickItem(MouseEvent event) {
		event.consume();
		Enseignant temp = selectionModel.getSelectedItem();
		if (temp != null) {
			selectedItem = temp;
			deleteButton.setVisible(true);
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
					loadStudents();
					selectionModel.clearSelection();
					deleteButton.setVisible(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}
	}

	private void loadStudents() {
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
		Stage stage = (Stage) addButton.getScene().getWindow();
		try {
			AnchorPane myNewScene = FXMLLoader.load(getClass().getResource("/application/fxml/AddEnseignant.fxml"));
			Scene scene = new Scene(myNewScene);
			stage.setScene(scene);
			stage.setTitle("Ajouter enseignant");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
