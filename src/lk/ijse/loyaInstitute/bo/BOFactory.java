package lk.ijse.loyaInstitute.bo;


import lk.ijse.loyaInstitute.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }


    public enum Type{COURSE,Student,REGISTRATION,DETAILS,USER}

    public Object getBO(Type type) {
        switch (type) {
            case Student:return new StudentBOImpl();
            case COURSE:return  new CourseBOImpl();
            case REGISTRATION:return new RegistrationBOImpl();
            case DETAILS:return new DetailsBOImpl();
            case USER:return new UserBOImpl();
            default: return null;
        }
    }
}
