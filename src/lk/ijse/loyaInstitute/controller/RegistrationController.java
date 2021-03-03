package lk.ijse.loyaInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.loyaInstitute.bo.custom.RegistrationDetailBO;
import lk.ijse.loyaInstitute.bo.custom.StudentBO;
import lk.ijse.loyaInstitute.entity.Course;
import lk.ijse.loyaInstitute.entity.Student;
import lk.ijse.loyaInstitute.tm.CourseTM;
import lk.ijse.loyaInstitute.tm.RegistrationTM;
import lk.ijse.loyaInstitute.tm.StudentTM;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrationController {
    public Label lblAddress;
    public Label lblContact;
    public Label lblDOB;
    public Label lblGender;
    public Label lblName;
    public Label lblCourseName;
    public Label lblType;
    public Label lblDuration;
    public TableView<RegistrationTM>tblRegistration;
    public TableColumn<RegistrationTM,String>clmRegID;
    public TableColumn <RegistrationTM, Date>clmRegDate;
    public TableColumn <RegistrationTM,Double>clmRegFee;
    public TableColumn <RegistrationTM,String>clmStudentCode;
    public TableColumn<RegistrationTM,String> clmCourseCode;
    public JFXTextField txtRegID;
    public JFXTextField txtRegFee;
    public JFXButton btnRegister;
    public Label lblDate;

    StudentBO studentBO= (StudentBO) BOFactory.getInstance().getBO(BOFactory.Type.Student);
    CourseBO courseBO= (CourseBO) BOFactory.getInstance().getBO(BOFactory.Type.COURSE);
    RegistrationDetailBO registrationBO= (RegistrationDetailBO) BOFactory.getInstance().getBO(BOFactory.Type.REGISTRATION);
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXComboBox<StudentTM> cmbStudent;

    @FXML
    private JFXComboBox<CourseTM> cmbCourse;

    @FXML
    void initialize() {

        lblDate.setText(String.valueOf(Date.valueOf(LocalDate.now())));
       cmbStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentTM>() {
           @Override
           public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM newValue) {
               lblName.setText(newValue.getName());
               lblAddress.setText(newValue.getAddress());
               lblContact.setText(newValue.getContact());
               lblDOB.setText(String.valueOf(newValue.getDob()));
               lblGender.setText(newValue.getGender());
           }
       });
       cmbCourse.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseTM>() {
           @Override
           public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM newValue) {
               lblCourseName.setText(newValue.getCourseName());
               lblDuration.setText(newValue.getDuration());
               lblType.setText(newValue.getCourseType());
           }
       });

       loadAllCombo();

       txtRegID.setDisable(true);

        try {
            int lastRegistrationID = registrationBO.getLastRegistrationID();
            txtRegID.setText(String.valueOf(lastRegistrationID));
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblRegistration.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("regNo"));
        tblRegistration.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("regDate"));
        tblRegistration.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("regFee"));
        tblRegistration.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("student"));
        tblRegistration.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("course"));

        tblRegistration.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RegistrationTM>() {
            @Override
            public void changed(ObservableValue<? extends RegistrationTM> observable, RegistrationTM oldValue, RegistrationTM newValue) {

            }
        });
        loadRegistration();


    }

    private void loadRegistration() {
        ObservableList<RegistrationTM>tms=FXCollections.observableArrayList();

        try {
            List<RegistrationTM> allRegistration = registrationBO.getAllRegistration();
            for (RegistrationTM tm : allRegistration) {
                tms.add(new RegistrationTM(tm.getRegNo(),tm.getRegDate(),tm.getRegFee(),tm.getStudent(),tm.getCourse()));
            }tblRegistration.setItems(tms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllCombo() {
        cmbStudent.getItems().clear();
        try {
            cmbStudent.setItems(FXCollections.observableArrayList(studentBO.getAllStudent()));
            cmbCourse.setItems(FXCollections.observableArrayList(courseBO.getAllCourses()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void registerONAction(ActionEvent actionEvent) {
            if (!cmbCourse.getSelectionModel().isEmpty()){
                if (!cmbStudent.getSelectionModel().isEmpty()){
                    if (!txtRegFee.getText().isEmpty()){


                                ObservableList<StudentTM> studentTMS = cmbStudent.getItems();
                                Student student1=null;
                                for (StudentTM item : studentTMS) {
                                    student1= new Student(item.getId(),item.getName(),item.getAddress(),item.getContact(),item.getDob(),item.getGender());

                                }
                                ObservableList<CourseTM> courseTMS = cmbCourse.getItems();
                                Course course=null;
                                for (CourseTM c : courseTMS) {
                                    course=new Course(c.getCode(),c.getCourseName(),c.getCourseType(),c.getDuration());
                                }
                                try {
                                    registrationBO.registration(Integer.parseInt(txtRegID.getText()),lblDate.getText(),Double.parseDouble(txtRegFee.getText()),student1,course);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                        loadRegistration();

                                txtRegID.clear();
                                txtRegFee.clear();
                                cmbCourse.getItems().clear();
                                cmbStudent.getItems().clear();
                                loadAllCombo();
                                lblName.setText("");
                                lblAddress.setText("");
                                lblContact.setText("");
                                lblDOB.setText("");
                                lblGender.setText("");
                                lblCourseName.setText("");
                                lblDuration.setText("");
                                lblType.setText("");
                        try {
                            txtRegID.setText(String.valueOf(registrationBO.getLastRegistrationID()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }else {
                        new Alert(Alert.AlertType.WARNING,"Enter Fee First").show();
                    }
                }else {
                    new Alert(Alert.AlertType.WARNING,"Choose Student First").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"Choose Course First").show();
            }
    }




}

