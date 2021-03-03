package lk.ijse.loyaInstitute.bo.custom.impl;

import lk.ijse.loyaInstitute.bo.custom.UserBO;
import lk.ijse.loyaInstitute.dao.DAOFactory;
import lk.ijse.loyaInstitute.dao.custom.UserDAO;
import lk.ijse.loyaInstitute.dto.UserDTO;
import lk.ijse.loyaInstitute.entity.Student;
import lk.ijse.loyaInstitute.entity.User;
import lk.ijse.loyaInstitute.tm.StudentTM;
import lk.ijse.loyaInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public  class UserBOImpl implements UserBO {

    UserDAO userDAO= (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.USER);

    @Override
    public void addUser(String ID, String password) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userDAO.add(new User(ID,password));
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(String ID, String password) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userDAO.update(new User(ID,password));
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<User> allUsers = null;

        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            allUsers = userDAO.getAll();
            transaction.commit();
        }catch (Throwable t){
            transaction.rollback();
            throw t;
        }finally {
            session.close();
        }



        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : allUsers) {
            userDTOS.add(new UserDTO(user.getID(),user.getPassword()));
        }
        return userDTOS;
    }


}
