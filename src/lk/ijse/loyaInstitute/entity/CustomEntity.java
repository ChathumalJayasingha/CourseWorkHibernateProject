package lk.ijse.loyaInstitute.entity;

import java.sql.Date;

public class CustomEntity {
    private String regId;
    private Date regDate;
    private Double regFee;
    private String studentId;
    private String studentName;
    private String courseCode;
    private String courseName;

    public CustomEntity() {
    }

    public CustomEntity(String regId, Date regDate, Double regFee, String studentId, String studentName, String courseCode, String courseName) {
        this.regId = regId;
        this.regDate = regDate;
        this.regFee = regFee;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Double getRegFee() {
        return regFee;
    }

    public void setRegFee(Double regFee) {
        this.regFee = regFee;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "regId='" + regId + '\'' +
                ", regDate=" + regDate +
                ", regFee=" + regFee +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
