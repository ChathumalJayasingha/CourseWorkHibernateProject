package lk.ijse.loyaInstitute.bo.custom;

import lk.ijse.loyaInstitute.bo.SuperBO;
import lk.ijse.loyaInstitute.tm.CourseTM;
import lk.ijse.loyaInstitute.tm.RegistrationDetailTM;

import java.util.List;

public interface DetailsBO extends SuperBO {
    List<RegistrationDetailTM> getAllRegistrationDetails() throws Exception;
}
