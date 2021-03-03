package lk.ijse.loyaInstitute.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.loyaInstitute.bo.BOFactory;
import lk.ijse.loyaInstitute.bo.custom.DetailsBO;
import lk.ijse.loyaInstitute.bo.custom.impl.DetailsBOImpl;
import lk.ijse.loyaInstitute.tm.RegistrationDetailTM;

import java.util.List;

public class ShowDetailsController {
    public TableView<RegistrationDetailTM>tblRegistrationDetails;
    public TableColumn clmRegId;
    public TableColumn clmRegDate;
    public TableColumn clmRegFee;
    public TableColumn clStudentId;
    public TableColumn clmStudentName;
    public TableColumn clmCourseCode;
    public TableColumn clmCourseName;

   DetailsBO detailsBO= (DetailsBO) BOFactory.getInstance().getBO(BOFactory.Type.DETAILS);

   public void initialize(){
        tblRegistrationDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("RegId"));
        tblRegistrationDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("RegDate"));
        tblRegistrationDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("RegFee"));
        tblRegistrationDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        tblRegistrationDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        tblRegistrationDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
        tblRegistrationDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("CourseName"));

        loadDetailsToTable();
       tblRegistrationDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RegistrationDetailTM>() {
           @Override
           public void changed(ObservableValue<? extends RegistrationDetailTM> observable, RegistrationDetailTM oldValue, RegistrationDetailTM newValue) {

           }
       });

    }

    private void loadDetailsToTable() {
        ObservableList<RegistrationDetailTM>ob= FXCollections.observableArrayList();
        try {
            List<RegistrationDetailTM> allRegistrationDetails = detailsBO.getAllRegistrationDetails();
            for (RegistrationDetailTM a : allRegistrationDetails) {
                ob.add(new RegistrationDetailTM(a.getRegId(),a.getRegDate(),a.getRegFee(),a.getStudentId(),a.getStudentName(),a.getCourseCode(),a.getCourseName()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            System.out.println(e.getClass());

        }
        tblRegistrationDetails.setItems(ob);
    }

}
