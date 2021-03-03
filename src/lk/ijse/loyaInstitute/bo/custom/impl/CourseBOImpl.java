package lk.ijse.loyaInstitute.bo.custom.impl;

import lk.ijse.loyaInstitute.bo.custom.CourseBO;
import lk.ijse.loyaInstitute.dao.DAOFactory;
import lk.ijse.loyaInstitute.dao.custom.CourseDAO;
import lk.ijse.loyaInstitute.dao.custom.impl.StudentDAOImpl;
import lk.ijse.loyaInstitute.dto.CourseDTO;
import lk.ijse.loyaInstitute.entity.Course;
import lk.ijse.loyaInstitute.entity.Student;
import lk.ijse.loyaInstitute.tm.CourseTM;
import lk.ijse.loyaInstitute.tm.StudentTM;
import lk.ijse.loyaInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO= (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.COURSE);


//    @Override
//    public void addCourse(String code, String CourseName, String CourseType, String duration) throws Exception {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        courseDAO.setSession(session);
//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();
//            courseDAO.add(new Course(code,CourseName,CourseType,duration));
//            transaction.commit();
//        } catch (Throwable t) {
//            transaction.rollback();
//            throw t;
//        } finally {
//            session.close();
//        }
//    }

    @Override
    public void addCourse(CourseDTO courseDTO) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            courseDAO.add(new Course(courseDTO.getCode(),courseDTO.getCourseName(),courseDTO.getCourseType(),courseDTO.getDuration()));
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteCourse(String code) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            courseDAO.delete(code);
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }
    }

    @Override
    public void updateCourse(CourseDTO courseDTO) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            courseDAO.update(new Course(courseDTO.getCode(),courseDTO.getCourseName(),courseDTO.getCourseType(),courseDTO.getDuration()));
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }
    }

//    @Override
//    public void updateCourse(String CourseName, String CourseType, String duration, String code) throws Exception {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        courseDAO.setSession(session);
//        Transaction transaction = null;
//        try{
//            transaction = session.beginTransaction();
//            courseDAO.update(new Course(code,CourseName,CourseType,duration));
//            transaction.commit();
//        }catch (Throwable t){
//            transaction.rollback();
//            throw t;
//        }finally {
//            session.close();
//        }
//    }

    @Override
    public String getNewCourseId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        String lastCourseCode = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            lastCourseCode = courseDAO.getLastCourseId();
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }

        if (lastCourseCode == null) {
            return "CT001";
        } else {
            int maxId = Integer.parseInt(lastCourseCode.replace("CT", ""));
            maxId = maxId + 1;
            String code = "";
            if (maxId < 10) {
                code = "CT00" + maxId;
            } else if (maxId < 100) {
                code = "CT0" + maxId;
            } else {
                code = "CT" + maxId;
            }
            return code;
        }
    }

    @Override
    public List<CourseTM> getAllCourses() throws Exception {
        List<Course> allCourses = null;

        Session session = FactoryConfiguration.getInstance().getSession();
        courseDAO.setSession(session);
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            allCourses = courseDAO.getAll();
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }



        List<CourseTM> courseTMS = new ArrayList<>();
        for (Course course : allCourses) {
            courseTMS.add(new CourseTM(course.getCode(),course.getCourseName(),course.getCourseType(),course.getDuration()));
        }
        return courseTMS;
    }
}
