package lk.ijse.loyaInstitute.dao;


import lk.ijse.loyaInstitute.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance(){
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

     public enum  Type{
        STUDENT,COURSE,REGISTRATION,QUERY,USER

    }

    public SuperDAO getDAO(Type type){
        switch (type){
            case STUDENT:return new StudentDAOImpl();
            case COURSE:return new CourseDAOImpl();
            case REGISTRATION:return new RegistrationDAOImpl();
            case QUERY:return new QueryDAOImpl();
            case USER:return new UserDAOImpl();
            default:return null;
        }
    }



}
