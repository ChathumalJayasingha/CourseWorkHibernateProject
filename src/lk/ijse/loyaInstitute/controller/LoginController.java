package lk.ijse.loyaInstitute.controller;

import com.jfoenix.controls.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.loyaInstitute.bo.BOFactory;
import lk.ijse.loyaInstitute.bo.custom.UserBO;
import lk.ijse.loyaInstitute.dto.UserDTO;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class LoginController {
    public JFXButton btnLogin;
    public JFXTextField txtID;
    
    public AnchorPane root;
    public ImageView imgIJSE;
    public Label lblIJSEEnterAndExit;
    public JFXPasswordField txtPassword;
    public JFXToggleButton btnToggle;
    public JFXTextField txtPasswordField;

    public void logingOnAction(ActionEvent actionEvent) throws Exception {

        String id=null;
        String password=null;
        UserBO userBO= (UserBO) BOFactory.getInstance().getBO(BOFactory.Type.USER);
        try {
            List<UserDTO> allUsers = userBO.getAllUsers();
            UserDTO userDTO = allUsers.get(0);
             id = userDTO.getID();
             password = userDTO.getPassword();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }




        if (txtID.getText().trim().length()>0 && txtPassword.getText().trim().length()>0){

            if (txtID.getText().trim().equals(id) && txtPassword.getText().trim().equals(password)){

                Stage window = (Stage) this.root.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/Main.fxml"))));
                window.centerOnScreen();

            }else {
                TrayNotification notification = new TrayNotification();
                notification.setAnimationType(AnimationType.SLIDE);
                notification.setTitle("Try Again");
                notification.setMessage("Enter Again Please");
                notification.setNotificationType(NotificationType.WARNING);
                notification.showAndDismiss(Duration.millis(1));

            }
        }else {
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("Empty");
            notification.setMessage("User ID or Password EMPTY!");
            notification.setNotificationType(NotificationType.ERROR);
            notification.showAndDismiss(Duration.millis(1));
        }
    }


    public void OnMouseEnterIJSEIcon(MouseEvent mouseEvent) {
            lblIJSEEnterAndExit.setText("DEVELOPED BY CHATHUMAL JAYASINGHA");
    }

    public void OnMouseEXITIJSEIcon(MouseEvent mouseEvent) {
        lblIJSEEnterAndExit.setText("IJSE");
    }




        public void initialize(){

        btnToggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (btnToggle.isSelected()){
                    txtPasswordField.setText(txtPassword.getText());
                    txtPasswordField.setVisible(true);
                    txtPassword.setVisible(false);
                    return;

                }
                txtPassword.setText(txtPasswordField.getText());
                txtPassword.setVisible(true);
                txtPasswordField.setVisible(false);
                txtPasswordField.setPromptText("Password");
            }
        });

        }
}
