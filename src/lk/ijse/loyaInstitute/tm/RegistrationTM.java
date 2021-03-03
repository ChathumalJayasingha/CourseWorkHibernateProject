package lk.ijse.loyaInstitute.tm;

public class RegistrationTM {
    private int regNo;
    private String regDate;
    private Double regFee;
    private String student;
    private String course;

    public RegistrationTM() {
    }

    public RegistrationTM(int regNo, String regDate, Double regFee, String student, String course) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.student = student;
        this.course = course;
    }

    @Override
    public String toString() {
        return "RegistrationTM{" +
                "regNo=" + regNo +
                ", regDate='" + regDate + '\'' +
                ", regFee=" + regFee +
                ", student='" + student + '\'' +
                ", course='" + course + '\'' +
                '}';
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Double getRegFee() {
        return regFee;
    }

    public void setRegFee(Double regFee) {
        this.regFee = regFee;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
