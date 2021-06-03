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
	requires itextpdf;
	requires java.desktop;
	requires org.apache.commons.codec;
	requires pdfbox;
	requires com.google.zxing;
	requires com.google.zxing.javase;
    opens edu.fpdual.hotelesapp.interfaz to javafx.fxml;
    opens edu.fpdual.hotelesapp.objetos;
    opens edu.fpdual.hotelesapp.conector;
    opens edu.fpdual.hotelesapp.mail;
    opens edu.fpdual.hotelesapp.manejadordb;
    exports edu.fpdual.hotelesapp.interfaz;
}
