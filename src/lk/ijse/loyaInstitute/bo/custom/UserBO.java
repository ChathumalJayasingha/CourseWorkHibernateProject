package lk.ijse.loyaInstitute.bo.custom;

import lk.ijse.loyaInstitute.bo.SuperBO;
import lk.ijse.loyaInstitute.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    void addUser(String ID, String password)throws Exception;
    void updateUser(String ID, String password)throws Exception;
    List<UserDTO> getAllUsers() throws Exception;
}
