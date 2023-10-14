package application.controllers.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.controllers.*;
import dao.ClassesDao;
import dao.EnseignantDao;
import dao.MatieresDao;
import dao.SceancesDao;
import helpers.NavigationHelpers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DashboardController implements Initializable, IController {

	@FXML
	public Label enseignantsCount;
	
	@FXML
	public Label matieresCount;
	
	@FXML
	public Label classesCount;
	
	@FXML
	public Label sceancesCount;
	
	private EnseignantDao EDao = new EnseignantDao();
	
	private MatieresDao MDao = new MatieresDao();
	
	private ClassesDao CDao = new ClassesDao();
	
	private SceancesDao SDao = new SceancesDao();
	
	public NavigationHelpers nh = new NavigationHelpers();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		enseignantsCount.setText(String.valueOf(EDao.count()));
		matieresCount.setText(String.valueOf(MDao.count()));
		classesCount.setText(String.valueOf(CDao.count()));
		sceancesCount.setText(String.valueOf(SDao.count()));
	}

	public void goToPage(String pageName, String pagePath) {
		
			Pane ctrl;
			try {
				ctrl = FXMLLoader.load(getClass().getResource(pagePath));
				nh.navigate(enseignantsCount, pageName, ctrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@FXML
	public void goToEnseignants() {
		goToPage("Gérer les enseignants", "/application/fxml/enseignants/ManageEnseignants.fxml");
	}

	@FXML
	public void goToMatieres() {
		goToPage("Gérer les matières", "/application/fxml/matieres/ManageMatieres.fxml");
	}

	@FXML
	public void goToClasses() {
		goToPage("Gérer les classes", "/application/fxml/classes/ManageClasses.fxml");
	}

	@FXML
	public void goToSceances() {
		goToPage("Gérer les séances", "/application/fxml/sceances/ManageSceances.fxml");
	}
	

}
