package lk.ijse.loyaInstitute.dao.custom.impl;

import lk.ijse.loyaInstitute.dao.custom.QueryDAO;
import lk.ijse.loyaInstitute.entity.CustomEntity;
import lk.ijse.loyaInstitute.tm.RegistrationDetailTM;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    private Session session;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<CustomEntity> getRegistrationDetail(){
        List<CustomEntity>list =null;
        try {
            NativeQuery sqlQuery = session.createSQLQuery("select Registration.regNo,Registration.regDate as RegDate,Registration.regFee,Student.id as studentId,Student.name as studentName,Course.code as courseCode,Course.courseName as courseName from Student natural join Registration,Course").addEntity(CustomEntity.class);
            list = sqlQuery.list();
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;


    }
}
