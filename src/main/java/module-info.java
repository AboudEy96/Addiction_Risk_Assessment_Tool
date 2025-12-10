module dev.aboudey.addictionriskassessmenttool {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens dev.aboudey.addictionriskassessmenttool to javafx.fxml;
    exports dev.aboudey.addictionriskassessmenttool;
}