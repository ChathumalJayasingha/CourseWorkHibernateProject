package lk.ijse.loyaInstitute.bo.custom;

import lk.ijse.loyaInstitute.dto.CourseDTO;
import lk.ijse.loyaInstitute.tm.CourseTM;
import lk.ijse.loyaInstitute.tm.StudentTM;

import java.sql.Date;
import java.util.List;

public interface CourseBO {
    void addCourse(CourseDTO courseDTO)throws Exception;
    void deleteCourse(String code)throws Exception;
    void updateCourse(CourseDTO courseDTO)throws Exception;
    String getNewCourseId()throws Exception;
    List<CourseTM> getAllCourses() throws Exception;
}
