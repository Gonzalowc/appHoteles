module edu.fpdual.hotelesapp.hotelesapp {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.base;
	requires javafx.base;
	requires javafx.graphics;
	requires java.mail;
	requires activation;
	requires lombok;

    opens edu.fpdual.hotelesapp.hotelesapp to javafx.fxml;
    exports edu.fpdual.hotelesapp.hotelesapp;
}
