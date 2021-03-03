package lk.ijse.loyaInstitute.bo.custom.impl;

import javafx.scene.control.Button;
import lk.ijse.loyaInstitute.bo.custom.StudentBO;
import lk.ijse.loyaInstitute.dao.DAOFactory;
import lk.ijse.loyaInstitute.dao.custom.impl.StudentDAOImpl;
import lk.ijse.loyaInstitute.entity.Student;
import lk.ijse.loyaInstitute.tm.StudentTM;
import lk.ijse.loyaInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class StudentBOImpl implements StudentBO {


    StudentDAOImpl studentDAO= (StudentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.Type.STUDENT);


    @Override
    public void addStudent(String id, String name, String address, String contact, Date dob, String gender) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            studentDAO.add(new Student(id, name, address, contact, dob, gender));
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }

    }

    @Override
    public void deleteStudent(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            studentDAO.delete(id);
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }
    }

    @Override
    public void updateStudent(String name, String address, String contact, Date dob, String gender,String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            studentDAO.update(new Student(id, name, address, contact, dob, gender));
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }
    }

    @Override
    public String getNewStudentId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        String lastStudentId = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            lastStudentId = studentDAO.getLastStudentId();
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }

        if (lastStudentId == null) {
            return "S001";
        } else {
            int maxId = Integer.parseInt(lastStudentId.replace("S", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "S00" + maxId;
            } else if (maxId < 100) {
                id = "S0" + maxId;
            } else {
                id = "S" + maxId;
            }
            return id;
        }

    }

    @Override
    public List<StudentTM> getAllStudent() throws Exception {
        List<Student> allStudents = null;

        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            allStudents = studentDAO.getAll();
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }



        List<StudentTM> studentTMS = new ArrayList<>();
        for (Student student : allStudents) {
            studentTMS.add(new StudentTM(student.getId(),student.getName(),
                    student.getAddress(),student.getContact(),student.getDob(),student.getGender()));
        }
        return studentTMS;
    }
}
