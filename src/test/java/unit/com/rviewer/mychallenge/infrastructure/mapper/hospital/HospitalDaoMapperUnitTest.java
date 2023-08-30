package unit.com.rviewer.mychallenge.infrastructure.mapper.hospital;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.infrastructure.mapper.hospital.HospitalDaoMapperImpl;
import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import unit.com.rviewer.mychallenge.infrastructure.mapper.common.GenericDaoMapperUnitTest;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class HospitalDaoMapperUnitTest extends GenericDaoMapperUnitTest<Hospital, HospitalDao, Long> {

    @InjectMocks
    private HospitalDaoMapperImpl hospitalDaoMapper;


    static Stream<HospitalDao> mapToModelUnitTest() {
        return Stream.of(
                HospitalDao.builder()
                        .id(1L)
                        .name("NAME")
                        .address("ADDRESS")
                        .centralHospital(HospitalDao.builder().id(2L).build())
                        .hospitalDelegateList(List.of())
                        .build()
        );
    }

    static Stream<Hospital> mapToDaoUnitTest() {
        return Stream.of(
                Hospital.builder()
                        .id(1L)
                        .name("NAME")
                        .address("ADDRESS")
                        .centralHospitalId(1L)
                        .hospitalDelegateList(List.of())
                        .build()
        );
    }

    @BeforeEach
    void setup() {
        super.setup(hospitalDaoMapper);
    }

    @Override
    public void mapToModelUnitTestAssertions(HospitalDao dao, Hospital mappedModel) {
        super.mapToModelUnitTestAssertions(dao, mappedModel);

        Assertions.assertEquals(dao.getName(), mappedModel.getName());
        Assertions.assertEquals(dao.getAddress(), mappedModel.getAddress());
        Assertions.assertEquals(dao.getCentralHospital().getId(), mappedModel.getCentralHospitalId());
        Assertions.assertEquals(dao.getHospitalDelegateList().size(), mappedModel.getHospitalDelegateList().size());

        for (int i = 0; i < dao.getHospitalDelegateList().size(); i++) {
            mapToModelUnitTestAssertions(
                    dao.getHospitalDelegateList().get(i),
                    mappedModel.getHospitalDelegateList().get(i)
            );
        }
    }

    @Override
    public void mapToDaoUnitTestAssertions(Hospital model, HospitalDao mappedDao) {
        super.mapToDaoUnitTestAssertions(model, mappedDao);
        Assertions.assertEquals(model.getName(), mappedDao.getName());
        Assertions.assertEquals(model.getAddress(), mappedDao.getAddress());
        Assertions.assertEquals(model.getCentralHospitalId(), mappedDao.getCentralHospital().getId());
        Assertions.assertEquals(model.getHospitalDelegateList().size(), mappedDao.getHospitalDelegateList().size());

        for (int i = 0; i < model.getHospitalDelegateList().size(); i++) {
            mapToDaoUnitTestAssertions(
                    model.getHospitalDelegateList().get(i),
                    mappedDao.getHospitalDelegateList().get(i)
            );
        }
    }
}
