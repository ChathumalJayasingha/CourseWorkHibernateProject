package lk.ijse.loyaInstitute.dto;

public class UserDTO {
    private String ID;
    private String password;

    public UserDTO(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public UserDTO() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
