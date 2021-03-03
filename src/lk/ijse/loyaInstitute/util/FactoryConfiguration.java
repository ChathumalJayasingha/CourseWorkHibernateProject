package lk.ijse.loyaInstitute.util;

import lk.ijse.loyaInstitute.entity.Course;
import lk.ijse.loyaInstitute.entity.Registration;
import lk.ijse.loyaInstitute.entity.Student;
import lk.ijse.loyaInstitute.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    public FactoryConfiguration() {
        Configuration configuration = new Configuration().
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Registration.class).
                addAnnotatedClass(Course.class).addAnnotatedClass(User.class);
        sessionFactory=configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance(){
        return factoryConfiguration==null?factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
