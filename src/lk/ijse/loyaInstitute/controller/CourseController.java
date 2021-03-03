package lk.ijse.loyaInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.loyaInstitute.bo.BOFactory;
import lk.ijse.loyaInstitute.bo.custom.CourseBO;
import lk.ijse.loyaInstitute.bo.custom.impl.StudentBOImpl;
import lk.ijse.loyaInstitute.dto.CourseDTO;
import lk.ijse.loyaInstitute.entity.Course;
import lk.ijse.loyaInstitute.tm.CourseTM;
import lk.ijse.loyaInstitute.tm.StudentTM;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CourseController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnAddNew;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<CourseTM> tblCourse;

    @FXML
    private TableColumn clmCode;

    @FXML
    private TableColumn clmName;

    @FXML
    private TableColumn clmType;

    @FXML
    private TableColumn clmDuration;

    CourseBO courseBO= (CourseBO) BOFactory.getInstance().getBO(BOFactory.Type.COURSE);

    @FXML
    void addNewOnAction(ActionEvent event) {
        txtCode.clear();
        txtName.clear();
        txtDuration.clear();
        txtType.clear();
        tblCourse.getSelectionModel().clearSelection();
        txtName.setDisable(false);
        txtType.setDisable(false);
        txtDuration.setDisable(false);
        txtName.requestFocus();
        btnSave.setDisable(false);


        try {
            txtCode.setText(courseBO.getNewCourseId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Course?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            CourseTM selectedItem = tblCourse.getSelectionModel().getSelectedItem();

            try {
                courseBO.deleteCourse(selectedItem.getCode());
                tblCourse.getItems().remove(selectedItem);
                tblCourse.getSelectionModel().clearSelection();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete the Student", ButtonType.OK).show();
            }
        }
        loadAllCourses();
    }

    @FXML
    void saveOnAction(ActionEvent event) {

        String name = txtName.getText();
        String type = txtType.getText();
        String duration = txtDuration.getText();



        if (name.trim().length() == 0 || type.trim().length() == 0  || duration.trim().length()==0) {
            new Alert(Alert.AlertType.ERROR, "Course Name, Type ,Duration can't be empty", ButtonType.OK).show();
            return;
        }

        if (btnSave.getText().equals("SAVE")) {

            try {
                courseBO.addCourse(new CourseDTO(txtCode.getText(), txtName.getText(), txtType.getText(),txtDuration.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            addNewOnAction(event);
        } else {
            CourseTM selectedItem = tblCourse.getSelectionModel().getSelectedItem();
            try {
                courseBO.updateCourse(new CourseDTO(txtCode.getText(),txtName.getText(), txtType.getText(), txtDuration.getText()));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to update the Course", ButtonType.OK).show();
            }
            tblCourse.refresh();

        }
        loadAllCourses();

    }

    @FXML
    void initialize() {
        loadAllCourses();

        assert btnAddNew != null : "fx:id=\"btnAddNew\" was not injected: check your FXML file 'Course.fxml'.";
        assert txtCode != null : "fx:id=\"txtCode\" was not injected: check your FXML file 'Course.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'Course.fxml'.";
        assert txtType != null : "fx:id=\"txtType\" was not injected: check your FXML file 'Course.fxml'.";
        assert txtDuration != null : "fx:id=\"txtDuration\" was not injected: check your FXML file 'Course.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'Course.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'Course.fxml'.";
        assert tblCourse != null : "fx:id=\"tblCourse\" was not injected: check your FXML file 'Course.fxml'.";
        assert clmCode != null : "fx:id=\"clmCode\" was not injected: check your FXML file 'Course.fxml'.";
        assert clmName != null : "fx:id=\"clmName\" was not injected: check your FXML file 'Course.fxml'.";
        assert clmType != null : "fx:id=\"clmType\" was not injected: check your FXML file 'Course.fxml'.";
        assert clmDuration != null : "fx:id=\"clmDuration\" was not injected: check your FXML file 'Course.fxml'.";



        txtCode.setDisable(true);txtName.setDisable(true);txtType.setDisable(true);txtDuration.setDisable(true);

        btnDelete.setDisable(true); btnSave.setDisable(true);



        tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("CourseType"));
        tblCourse.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Duration"));



        tblCourse.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseTM>() {

            @Override
            public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM newValue) {
                CourseTM selectedItem = (CourseTM) tblCourse.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnSave.setText("SAVE");
                    btnDelete.setDisable(true);
                    txtCode.clear();
                    txtName.clear();
                    txtDuration.clear();
                    txtType.clear();
                    return;
                }
                btnSave.setText("UPDATE");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtCode.setDisable(false);
                txtName.setDisable(false);
                txtDuration.setDisable(false);
                txtType.setDisable(false);

                txtCode.setText(selectedItem.getCode());
                txtName.setText(selectedItem.getCourseName());
                txtType.setText(selectedItem.getCourseType());
                txtDuration.setText(selectedItem.getDuration());

            }
        });






    }



    private void loadAllCourses() {



        tblCourse.getItems().clear();
        List<CourseTM> allCourses = null;
        try {
            allCourses = courseBO.getAllCourses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<CourseTM> courseTMS = FXCollections.observableArrayList(allCourses);
        tblCourse.setItems(courseTMS);
    }


}










