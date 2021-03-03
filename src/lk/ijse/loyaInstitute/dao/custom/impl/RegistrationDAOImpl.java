package lk.ijse.loyaInstitute.dao.custom.impl;

import lk.ijse.loyaInstitute.dao.CrudDAOImpl;
import lk.ijse.loyaInstitute.dao.custom.RegistrationDAO;
import lk.ijse.loyaInstitute.dao.custom.StudentDAO;
import lk.ijse.loyaInstitute.entity.Registration;
import lk.ijse.loyaInstitute.entity.Student;
import lk.ijse.loyaInstitute.tm.RegistrationTM;

import java.util.List;

public class RegistrationDAOImpl extends CrudDAOImpl<Registration,Integer> implements RegistrationDAO {
    @Override
    public Integer getLastRegistrationID() throws Exception {
        return (Integer) session.createNativeQuery("SELECT regNo FROM Registration ORDER BY regNo DESC LIMIT 1").uniqueResult();
    }

}
