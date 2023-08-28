package unit.com.rviewer.mychallenge.infrastructure.persistence.dao.common;

import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

import java.io.Serializable;

public abstract class CmdbElementDaoUnitTest<D extends CmdbElementDao<I>, I extends Serializable> {

    @ParameterizedTest
    public void defaultElementDaoCreationUnitTest(D elementDao) {
        // then
        Assertions.assertNull(elementDao.getId());
        Assertions.assertTrue(elementDao.getActive());
    }
}
