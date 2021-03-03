package lk.ijse.loyaInstitute.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements SuperEntity {
    @Id
    private String ID;
    private String password;

    public User() {
    }

    public User(String ID, String password) {
        this.ID = ID;
        this.password = password;
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
        return "User{" +
                "ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
