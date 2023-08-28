package unit.com.rviewer.mychallenge.infrastructure.persistence.dao.hospital;

import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import unit.com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDaoUnitTest;

import java.util.stream.Stream;

class HospitalDaoUnitTest extends CmdbElementDaoUnitTest<HospitalDao, Long> {

    static Stream<HospitalDao> defaultElementDaoCreationUnitTest() {
        HospitalDao hospitalDao = HospitalDao.builder()
                .build();

        return Stream.of(hospitalDao);
    }

    @Override
    @MethodSource
    @ParameterizedTest
    public void defaultElementDaoCreationUnitTest(HospitalDao elementDao) {
        super.defaultElementDaoCreationUnitTest(elementDao);
        Assertions.assertNull(elementDao.getName());
        Assertions.assertNull(elementDao.getAddress());
        Assertions.assertNotNull(elementDao.getHospitalDelegateList());
        Assertions.assertTrue(elementDao.getHospitalDelegateList().isEmpty());
        Assertions.assertNull(elementDao.getCentralHospital());
    }
}
