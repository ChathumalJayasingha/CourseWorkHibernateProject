package lk.ijse.loyaInstitute.dao.custom;

import lk.ijse.loyaInstitute.dao.CrudDAO;
import lk.ijse.loyaInstitute.entity.Course;

public interface CourseDAO extends CrudDAO<Course,String> {
    String getLastCourseId() throws Exception;
}
