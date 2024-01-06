package application.miniprojetmp2l1.controllers.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import application.miniprojetmp2l1.controllers.IController;
import application.miniprojetmp2l1.dao.ClassesDao;
import application.miniprojetmp2l1.dao.EnseignantDao;
import application.miniprojetmp2l1.dao.MatieresDao;
import application.miniprojetmp2l1.dao.SceancesDao;
import application.miniprojetmp2l1.helpers.NavigationHelpers;
import application.miniprojetmp2l1.Assets;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DashboardController implements Initializable, IController {

	@FXML
	public Label enseignantsCount;
	
	@FXML
	public Label matieresCount;
	
	@FXML
	public Label classesCount;
	
	@FXML
	public Label sceancesCount;

	@FXML
	public ImageView background;
	
	private final EnseignantDao EDao = new EnseignantDao();
	
	private final MatieresDao MDao = new MatieresDao();
	
	private final ClassesDao CDao = new ClassesDao();
	
	private final SceancesDao SDao = new SceancesDao();
	
	public NavigationHelpers nh = new NavigationHelpers();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		enseignantsCount.setText(String.valueOf(EDao.count()));
		matieresCount.setText(String.valueOf(MDao.count()));
		classesCount.setText(String.valueOf(CDao.count()));
		sceancesCount.setText(String.valueOf(SDao.count()));

		Image image1 = new Image("file:C:/Users/pc/Desktop/MiniProjetMP2L1/src/main/java/application/miniprojetmp2l1/controllers/back.jpg");

		background.setImage(image1);

	}

	public void goToPage(String pageName, String pagePath) {
		
			Pane ctrl;
			try {
				ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pagePath)));
				nh.navigate(enseignantsCount, pageName, ctrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@FXML
	public void goToEnseignants() {
		goToPage("Gérer les enseignants", "/application/miniprojetmp2l1/enseignants/ManageEnseignants.fxml");
	}

	@FXML
	public void goToMatieres() {
		goToPage("Gérer les matières", "/application/miniprojetmp2l1/matieres/ManageMatieres.fxml");
	}

	@FXML
	public void goToClasses() {
		goToPage("Gérer les classes", "/application/miniprojetmp2l1/classes/ManageClasses.fxml");
	}

	@FXML
	public void goToSceances() {
		goToPage("Gérer les séances", "/application/miniprojetmp2l1/sceances/ManageSceances.fxml");
	}
	

}
