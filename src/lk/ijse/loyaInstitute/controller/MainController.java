package lk.ijse.loyaInstitute.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    public AnchorPane loader;
    public JFXButton btnDefault;
    public JFXButton btnCourses;
    public JFXButton btnStudent;
    public JFXButton btnRegistration;
    public JFXButton btnRegistrationDetails;

    public void initialize() throws IOException {
        loadDefault();
    }

    private void loadDefault() throws IOException {
        setUI("Default");
    }

    private void setUI(String location) throws IOException {
        loader.getChildren().clear();
        loader.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + location + ".fxml")));
    }

    public void defaultOnAction(ActionEvent actionEvent)throws Exception {
        setUI("Default");
    }

    public void coursesOnAction(ActionEvent actionEvent)throws Exception {
        setUI("Course");
    }

    public void studentOnAction(ActionEvent actionEvent) throws Exception{
        setUI("student");
    }

    public void registrationOnAction(ActionEvent actionEvent)throws Exception {
        setUI("Registration");
    }

    public void registrationDetailOnAction(ActionEvent actionEvent)throws Exception {
        setUI("ShowDetails");
    }
}
