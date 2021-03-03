package lk.ijse.loyaInstitute.dao.custom.impl;

import lk.ijse.loyaInstitute.dao.CrudDAOImpl;
import lk.ijse.loyaInstitute.dao.custom.CourseDAO;
import lk.ijse.loyaInstitute.entity.Course;
import lk.ijse.loyaInstitute.util.FactoryConfiguration;

public class CourseDAOImpl extends CrudDAOImpl<Course,String> implements CourseDAO {
    @Override
    public String getLastCourseId() throws Exception {
        return (String) session.createNativeQuery("SELECT code FROM Course ORDER BY code DESC LIMIT 1").uniqueResult();
    }
}
