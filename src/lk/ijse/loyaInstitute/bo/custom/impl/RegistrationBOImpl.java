package lk.ijse.loyaInstitute.bo.custom.impl;

import lk.ijse.loyaInstitute.bo.custom.RegistrationDetailBO;
import lk.ijse.loyaInstitute.dao.DAOFactory;
import lk.ijse.loyaInstitute.dao.custom.RegistrationDAO;
import lk.ijse.loyaInstitute.entity.Course;
import lk.ijse.loyaInstitute.entity.Registration;
import lk.ijse.loyaInstitute.entity.Student;
import lk.ijse.loyaInstitute.tm.RegistrationTM;
import lk.ijse.loyaInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationDetailBO {
    RegistrationDAO registrationDAO= (RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.REGISTRATION);

    @Override
    public Integer getLastRegistrationID() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        registrationDAO.setSession(session);
        Integer lastRegistrationId = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            lastRegistrationId = registrationDAO.getLastRegistrationID();
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }

        if (lastRegistrationId == null) {
            return 1;
        } else {
            int code = (lastRegistrationId);
            code = code + 1;
            return code;
        }
    }

    @Override
    public void registration(Integer regName, String regDate, Double regFee, Student student, Course course) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        registrationDAO.setSession(session);
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            registrationDAO.add(new Registration(regName,  regDate,  regFee,  student,  course));
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }




    }

    @Override
    public List<RegistrationTM> getAllRegistration() throws Exception {
        List<Registration> registrations = null;

        Session session = FactoryConfiguration.getInstance().getSession();
        registrationDAO.setSession(session);
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            registrations = registrationDAO.getAll();
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }


        List<RegistrationTM>registrationTMS=new ArrayList<>();
        for (Registration registration : registrations) {
            registrationTMS.add(new RegistrationTM(registration.getRegNo(),registration.getRegDate(),registration.getRegFee(),registration.getStudent().getId(),registration.getCourse().getCode()));
        } return registrationTMS;
    }
}
