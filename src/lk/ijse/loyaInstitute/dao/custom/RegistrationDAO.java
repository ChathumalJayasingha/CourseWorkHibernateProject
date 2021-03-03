package lk.ijse.loyaInstitute.dao.custom;

import lk.ijse.loyaInstitute.dao.CrudDAO;
import lk.ijse.loyaInstitute.entity.Course;
import lk.ijse.loyaInstitute.entity.SuperEntity;
import lk.ijse.loyaInstitute.entity.Registration;
import lk.ijse.loyaInstitute.tm.CourseTM;
import lk.ijse.loyaInstitute.tm.RegistrationTM;

import java.util.List;

public interface RegistrationDAO extends CrudDAO<Registration,Integer> {
    Integer getLastRegistrationID() throws Exception;
}
