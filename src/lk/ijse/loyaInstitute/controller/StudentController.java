package lk.ijse.loyaInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.loyaInstitute.bo.BOFactory;
import lk.ijse.loyaInstitute.bo.SuperBO;
import lk.ijse.loyaInstitute.bo.custom.StudentBO;
import lk.ijse.loyaInstitute.bo.custom.UserBO;
import lk.ijse.loyaInstitute.bo.custom.impl.StudentBOImpl;
import lk.ijse.loyaInstitute.dto.UserDTO;
import lk.ijse.loyaInstitute.tm.StudentTM;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class StudentController {
    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtDate;
    public TextField txtGender;
    public Button btnSAve;
    public Button btnAdd;
    public Button btnDelete;
    public TableView<StudentTM> tblStudent;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableColumn clmContact;
    public TableColumn clmDOB;
    public TableColumn clmGender;
    public JFXTextField txtId;
    public JFXTextField txtPassword;
    public JFXPasswordField txtpswField;
    public JFXButton btnPSWSAVE;
    public JFXButton btnPSWUpdate;
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.Type.Student);
    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.Type.USER);
    public void initialize(){


        txtDate.setText(String.valueOf(Date.valueOf(LocalDate.now())));


        txtID.setDisable(true);txtName.setDisable(true);txtAddress.setDisable(true);
        txtContact.setDisable(true);txtDate.setDisable(true);txtGender.setDisable(true);

        btnDelete.setDisable(true); btnSAve.setDisable(true);

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentTM>()   {

            @Override
            public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM newValue) {
                StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

                if (selectedItem==null){
                    btnSAve.setText("Save");
                    btnDelete.setDisable(true);
                    txtID.clear();  txtName.clear();    txtAddress.clear();
                    txtContact.clear(); txtDate.clear();    txtGender.clear();
                    return;
                }
                btnSAve.setText("Update");
                btnSAve.setDisable(false);
                btnDelete.setDisable(false);
                txtID.setDisable(false); txtName.setDisable(false);    txtAddress.setDisable(false);
                txtContact.setDisable(false); txtDate.setDisable(false);    txtGender.setDisable(false);

                txtID.setText(selectedItem.getId()); txtName.setText(selectedItem.getName());    txtAddress.setText(selectedItem.getAddress());
                txtContact.setText(selectedItem.getContact()); txtDate.setText(String.valueOf(selectedItem.getDob()));   txtGender.setText(selectedItem.getGender());


            }
        });

        loadAllStudents();
        loadAllUserDetails();


    }



    private void loadAllStudents() {



        tblStudent.getItems().clear();
        List<StudentTM> allStudents = null;
        try {
            allStudents = studentBO.getAllStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<StudentTM> customers = FXCollections.observableArrayList(allStudents);
        tblStudent.setItems(customers);
    }

    public void saveONAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String date = txtDate.getText();
        String gender = txtGender.getText();
        if (!name.isEmpty()||address.isEmpty()||contact.isEmpty()||date.isEmpty()||gender.isEmpty()){

            if (name.trim().length() == 0 || address.trim().length() == 0|| contact.trim().length() == 0||
                    date.trim().length() == 0|| gender.trim().length() == 0) {
                new Alert(Alert.AlertType.ERROR, "Student Name, Address,Contact,Date,Gender can't be empty", ButtonType.OK).show();
                return;
            }

            if (btnSAve.getText().equals("SAVE")) {

                try {
                    studentBO.addStudent(txtID.getText(),name, address,contact, Date.valueOf(date),gender);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                addONAction(actionEvent);
            } else {
                StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
                try {
                    studentBO.updateStudent(txtName.getText(), txtAddress.getText(), txtContact.getText(), Date.valueOf(txtDate.getText()),txtGender.getText(),selectedItem.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Failed to update the customer", ButtonType.OK).show();
                }
                tblStudent.refresh();
                addONAction(actionEvent);
            }
            loadAllStudents();
        }else {
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.SLIDE);
            notification.setTitle("Empty Field ");
            notification.setMessage("Enter Again Please");
            notification.setNotificationType(NotificationType.WARNING);
            notification.showAndDismiss(Duration.millis(1));
        }





    }

    public void addONAction(ActionEvent actionEvent) {

        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        tblStudent.getSelectionModel().clearSelection();
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtDate.setDisable(false);
        txtGender.setDisable(false);
        txtName.requestFocus();
        btnSAve.setDisable(false);

        try {
            txtID.setText(studentBO.getNewStudentId());
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void deleteONAction(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Student?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

            try {
                studentBO.deleteStudent(selectedItem.getId());
                tblStudent.getItems().remove(selectedItem);
                tblStudent.getSelectionModel().clearSelection();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete the Student", ButtonType.OK).show();
            }
        }
        loadAllStudents();
    }


    public void pasSaveOnAction(ActionEvent actionEvent) {



        String id = txtId.getText().trim();
        String password = txtpswField.getText().trim();

        try {
            userBO.addUser(id,password);
        } catch (Exception e) {
            System.out.println("User");
        }



    }

    public void pasUpdateOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        String password = txtpswField.getText();

        try {
            userBO.updateUser(txtId.getText(),txtpswField.getText());
        } catch (Exception e) {
            System.out.println("update");
        }
    }
    private void loadAllUserDetails() {
        try {
            List<UserDTO> allUsers = userBO.getAllUsers();
            UserDTO userDTO = allUsers.get(0);
            txtId.setText(userDTO.getID());
            txtpswField.setText(userDTO.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }btnPSWSAVE.setDisable(true);

    }
}
