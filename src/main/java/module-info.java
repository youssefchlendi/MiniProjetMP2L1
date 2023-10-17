module application.miniprojetmp2l1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens application.miniprojetmp2l1 to javafx.fxml , javafx.base;
    opens application.miniprojetmp2l1.controllers.dashboard to javafx.fxml , javafx.base;
    opens application.miniprojetmp2l1.controllers.classes to javafx.fxml , javafx.base;
    opens application.miniprojetmp2l1.controllers.enseignants to javafx.fxml , javafx.base;
    opens application.miniprojetmp2l1.controllers.matieres to javafx.fxml , javafx.base;
    opens application.miniprojetmp2l1.controllers.sceances to javafx.fxml , javafx.base;
    opens application.miniprojetmp2l1.models to javafx.fxml , javafx.base;

    exports application.miniprojetmp2l1;
}