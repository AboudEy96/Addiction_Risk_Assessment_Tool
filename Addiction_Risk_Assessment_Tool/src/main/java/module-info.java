module dev.aboudey.addictionriskassessmenttool {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.management;

    opens dev.aboudey.addictionriskassessmenttool to javafx.fxml;
    opens dev.aboudey.addictionriskassessmenttool.ui to javafx.fxml;

    exports dev.aboudey.addictionriskassessmenttool;
}
