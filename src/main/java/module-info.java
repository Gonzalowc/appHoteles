module edu.fpdual.hotelesapp.hotelesapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.fpdual.hotelesapp.hotelesapp to javafx.fxml;
    exports edu.fpdual.hotelesapp.hotelesapp;
}
