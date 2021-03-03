package lk.ijse.loyaInstitute.bo.custom;

import lk.ijse.loyaInstitute.bo.SuperBO;
import lk.ijse.loyaInstitute.entity.Course;
import lk.ijse.loyaInstitute.entity.Student;
import lk.ijse.loyaInstitute.tm.CourseTM;
import lk.ijse.loyaInstitute.tm.RegistrationTM;

import java.util.Date;
import java.util.List;

public interface RegistrationDetailBO extends SuperBO {
     Integer getLastRegistrationID() throws Exception;
     void registration(Integer regName, String regDate, Double regFee, Student student, Course course)throws Exception;
     List<RegistrationTM> getAllRegistration() throws Exception;
}
