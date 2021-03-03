package lk.ijse.loyaInstitute.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DefaultController {
    public Text txtPrograms;
    @FXML
    private ImageView imgDEP;

    @FXML
    private ImageView imgCMJD;

    @FXML
    private ImageView imgRWAD;

    @FXML
    private ImageView imgABSD;

    @FXML
    private ImageView imgGDSE;

    @FXML
    void ABSDOnMouseEnter(MouseEvent event) {
        txtPrograms.setText("Advances Business Solution Develop");
    }

    @FXML
    void ABSDOnMouseExit(MouseEvent event) {
        txtPrograms.setText("");
    }

    @FXML
    void CMJDOnMouseEnter(MouseEvent event) {
        txtPrograms.setText("CMJD Professional");
    }

    @FXML
    void CMJDOnMouseExit(MouseEvent event) {
        txtPrograms.setText("");
    }

    @FXML
    void DEPOnMouseEnter(MouseEvent event) {
            txtPrograms.setText("Direct Entry Program");
    }

    @FXML
    void DEPOnMouseExit(MouseEvent event) {
        txtPrograms.setText("");
    }

    @FXML
    void GDSEOnMouseEnter(MouseEvent event) {
        txtPrograms.setText("Graduate Diploma in Software Engineering");
    }

    @FXML
    void GDSEOnMouseExit(MouseEvent event) {
        txtPrograms.setText("");
    }

    @FXML
    void RWADOnMouseEnter(MouseEvent event) {
        txtPrograms.setText("Repaid Web App Develop");
    }

    @FXML
    void RWADOnMouseExit(MouseEvent event) {
        txtPrograms.setText("");
    }

}
