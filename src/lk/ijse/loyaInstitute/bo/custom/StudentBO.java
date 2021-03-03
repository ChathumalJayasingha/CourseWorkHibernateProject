package lk.ijse.loyaInstitute.bo.custom;

import lk.ijse.loyaInstitute.bo.SuperBO;
import lk.ijse.loyaInstitute.tm.StudentTM;

import java.sql.Date;
import java.util.List;

public interface StudentBO extends SuperBO {

    void addStudent(String id, String name, String address, String contact, Date dob, String gender)throws Exception;
    void deleteStudent(String id)throws Exception;
    void updateStudent(String name, String address, String contact, Date dob, String gender,String id)throws Exception;
    String getNewStudentId()throws Exception;
     List<StudentTM> getAllStudent() throws Exception;
}
