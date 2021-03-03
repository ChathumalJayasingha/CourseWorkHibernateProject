package lk.ijse.loyaInstitute.dao.custom;

import lk.ijse.loyaInstitute.dao.CrudDAO;
import lk.ijse.loyaInstitute.entity.Student;

public interface StudentDAO extends CrudDAO<Student,String> {
    String getLastStudentId() throws Exception;
}
