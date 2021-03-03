package lk.ijse.loyaInstitute.dao.custom;

import lk.ijse.loyaInstitute.dao.SuperDAO;
import lk.ijse.loyaInstitute.entity.CustomEntity;
import lk.ijse.loyaInstitute.tm.RegistrationDetailTM;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomEntity> getRegistrationDetail() throws Exception;
}
