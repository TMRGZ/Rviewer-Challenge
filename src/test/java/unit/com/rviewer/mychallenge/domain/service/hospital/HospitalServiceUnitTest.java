package unit.com.rviewer.mychallenge.domain.service.hospital;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.repository.hospital.HospitalRepository;
import com.rviewer.mychallenge.domain.service.hospital.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import unit.com.rviewer.mychallenge.domain.service.common.CmdbCrudServiceUnitTest;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class HospitalServiceUnitTest extends CmdbCrudServiceUnitTest<Hospital, Long> {

    @InjectMocks
    private HospitalService hospitalService;

    @Mock
    private HospitalRepository hospitalRepository;

    static Stream<Long> getByIdUnitTest() {
        return Stream.of(1L);
    }

    static Stream<Long> getById_ButNotFound_UnitTest() {
        return Stream.of(1L);
    }

    static Stream<Long> getHistoryUnitTest() {
        return Stream.of(1L);
    }

    static Stream<Hospital> saveUnitTest() {
        return Stream.of(
                Hospital.builder().build()
        );
    }

    static Stream<Long> deleteUnitTest() {
        return Stream.of(1L);
    }

    static Stream<Long> delete_ButNotFound_UnitTest() {
        return Stream.of(1L);
    }

    @BeforeEach
    void setup() {
        super.setup(hospitalService, hospitalRepository);
    }
}
