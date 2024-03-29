package application.miniprojetmp2l1.controllers.sceances;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import application.miniprojetmp2l1.Storage;
import application.miniprojetmp2l1.controllers.IController;
import application.miniprojetmp2l1.dao.ClassesDao;
import application.miniprojetmp2l1.dao.EnseignantDao;
import application.miniprojetmp2l1.dao.MatieresDao;
import application.miniprojetmp2l1.dao.SceancesDao;
import application.miniprojetmp2l1.helpers.NavigationHelpers;
import application.miniprojetmp2l1.models.Classe;
import application.miniprojetmp2l1.models.Enseignant;
import application.miniprojetmp2l1.models.Matiere;
import application.miniprojetmp2l1.models.Sceance;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SceanceController implements Initializable, IController {

	SceancesDao dao = new SceancesDao();
	MatieresDao mdao = new MatieresDao();
	ClassesDao cdao = new ClassesDao();
	EnseignantDao edao = new EnseignantDao();

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
	public TableColumn<Sceance, String> enseignantContact;

	@FXML
	public Button deleteButton;

	@FXML
	public Button updateButton;

	@FXML
	public Button addButton;

	@FXML
	public Button homeButton;

	@FXML
	public ComboBox<Matiere> matiereFilter;

	@FXML
	public ComboBox<Classe> classeFilter;

	@FXML
	public ComboBox<Enseignant> enseignantFilter;

	@FXML
	public ImageView background;

	NavigationHelpers nh = new NavigationHelpers();

	private ObservableList<Sceance> data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image1 = new Image("file:C:/Users/pc/Desktop/MiniProjetMP2L1/src/main/java/application/miniprojetmp2l1/controllers/back.jpg");
		background.setImage(image1);
		loadSceances();
		initButtons();
		try {
			initFilters();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initFilters() throws SQLException {
		classeFilter.getItems().addAll(cdao.getAll());
		matiereFilter.getItems().addAll(mdao.getAll());
		enseignantFilter.getItems().addAll(edao.getAll());

		classeFilter.setConverter(new StringConverter<>() {
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

		matiereFilter.setConverter(new StringConverter<>() {
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

		enseignantFilter.setConverter(new StringConverter<>() {
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

		// listen to change t o all filters
		classeFilter.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> filterList());
		matiereFilter.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> filterList());
		enseignantFilter.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> filterList());
	}

	public void filterList() {
		String classeMatricule;
		String matiereId;
		String enseignantMatricule;

		if (classeFilter.getSelectionModel().getSelectedItem() != null) {
			classeMatricule = classeFilter.getSelectionModel().getSelectedItem().getMatricule();
		} else {
			classeMatricule = "";
		}

		if (matiereFilter.getSelectionModel().getSelectedItem() != null) {
			matiereId = matiereFilter.getSelectionModel().getSelectedItem().getId();
		} else {
			matiereId = "";
		}

		if (enseignantFilter.getSelectionModel().getSelectedItem() != null) {
			enseignantMatricule = enseignantFilter.getSelectionModel().getSelectedItem().getMatricule();
		} else {
			enseignantMatricule = "";
		}

		try {
			data = dao.filter(matiereId, classeMatricule, enseignantMatricule);
			sceancesList.setItems(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public void deleteButtonClicked(ActionEvent ignoredEvent) {
		if (selectedItem != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Supprimer sceance");
			alert.setContentText("Êtes-vous sûr de vouloir supprimer cette sceance ("+ selectedItem.getId() + ") ?");
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
	public void updateButtonClicked(ActionEvent ignoredEvent) {
		if (selectedItem != null) {
			Pane ctrl;
			try {
				Storage.Sceance.id = selectedItem.getId();
				ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/miniprojetmp2l1/sceances/UpsertSceances.fxml")));
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
		enseignantContact.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEnseignant().getContact()));
		sceancesList.setItems(data);
		selectionModel = sceancesList.getSelectionModel();
	}

	@FXML
	public void addButtonClicked(ActionEvent ignoredEv) {
		Pane ctrl;
		try {
			ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/miniprojetmp2l1/sceances/UpsertSceances.fxml")));
			nh.navigate(addButton, "Ajouter sceance", ctrl);
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

	@FXML
	public void resetClasseFilter(ActionEvent ignoredEv) {
		classeFilter.getSelectionModel().clearSelection();
	}

	@FXML
	public void resetMatiereFilter(ActionEvent ignoredEv) {
		matiereFilter.getSelectionModel().clearSelection();
	}

	@FXML
	public void resetEnseignantFilter(ActionEvent ignoredEv) {
		enseignantFilter.getSelectionModel().clearSelection();
	}

}
