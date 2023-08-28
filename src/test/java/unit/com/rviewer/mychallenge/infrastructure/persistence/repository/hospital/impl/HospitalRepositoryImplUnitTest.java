package unit.com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.impl;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.infrastructure.mapper.hospital.HospitalDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.impl.HospitalRepositoryImpl;
import com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.jpa.JpaHospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.history.Revisions;
import unit.com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.ElementCrudRepositoryImplUnitTest;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class HospitalRepositoryImplUnitTest extends ElementCrudRepositoryImplUnitTest<Hospital, HospitalDao, Long> {

    @InjectMocks
    private HospitalRepositoryImpl hospitalRepository;

    @Mock
    private HospitalDaoMapper mapper;

    @Mock
    private JpaHospitalRepository repository;

    static Stream<Arguments> findByIdUnitTest() {
        return Stream.of(Arguments.of(1L, Hospital.class, HospitalDao.class));
    }

    static Stream<Long> findById_ButNotFound_UnitTest() {
        return Stream.of(1L);
    }

    static Stream<Arguments> findAllUnitTest() {
        var elementDaoList = List.of(
                HospitalDao.builder().id(1L).build(),
                HospitalDao.builder().id(2L).active(false).build()
        );

        return Stream.of(
                Arguments.of(elementDaoList, Hospital.class)
        );
    }

    static Stream<Arguments> findHistoryUnitTest() {
        var revisionList = Mockito.mock(Revisions.class);
        return Stream.of(Arguments.of(1L, revisionList, Hospital.class));
    }

    static Stream<Arguments> saveUnitTest() {
        return Stream.of(Arguments.of(Hospital.builder().build(), HospitalDao.class));
    }

    static Stream<Arguments> deleteUnitTest() {
        return Stream.of(Arguments.of(Hospital.builder().build(), HospitalDao.class));
    }

    @BeforeEach
    void setup() {
        super.setup(hospitalRepository, mapper, repository);
    }

}
