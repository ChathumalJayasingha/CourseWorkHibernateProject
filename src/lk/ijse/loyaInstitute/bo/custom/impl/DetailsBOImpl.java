package lk.ijse.loyaInstitute.bo.custom.impl;

import lk.ijse.loyaInstitute.bo.custom.DetailsBO;
import lk.ijse.loyaInstitute.dao.DAOFactory;
import lk.ijse.loyaInstitute.dao.custom.QueryDAO;
import lk.ijse.loyaInstitute.entity.Course;
import lk.ijse.loyaInstitute.entity.CustomEntity;
import lk.ijse.loyaInstitute.tm.CourseTM;
import lk.ijse.loyaInstitute.tm.RegistrationDetailTM;
import lk.ijse.loyaInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DetailsBOImpl implements DetailsBO {
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.Type.QUERY);
    @Override
    public List<RegistrationDetailTM> getAllRegistrationDetails() throws Exception {
        List<CustomEntity> registrationDetail = queryDAO.getRegistrationDetail();
        List<RegistrationDetailTM>tm=new ArrayList<>();
        for (CustomEntity c : registrationDetail) {
            RegistrationDetailTM registrationDetailTM = new RegistrationDetailTM(c.getRegId(), c.getRegDate(), c.getRegFee(), c.getStudentId(), c.getStudentName(), c.getCourseCode(), c.getCourseName());
            tm.add(registrationDetailTM);
        }
        return tm;
    }
}
