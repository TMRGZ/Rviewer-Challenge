package unit.com.rviewer.mychallenge.application.service.hospital;

import com.rviewer.mychallenge.application.dto.hospital.HospitalDto;
import com.rviewer.mychallenge.application.mapper.hospital.HospitalDtoMapper;
import com.rviewer.mychallenge.application.service.hospital.HospitalApplicationService;
import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.domain.service.hospital.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import unit.com.rviewer.mychallenge.application.service.common.CmdbCrudApplicationServiceUnitTest;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class HospitalApplicationServiceUnitTest
        extends CmdbCrudApplicationServiceUnitTest<Hospital, HospitalDto, Long> {

    @InjectMocks
    private HospitalApplicationService hospitalApplicationService;

    @Mock
    private HospitalDtoMapper hospitalDtoMapper;

    @Mock
    private HospitalService hospitalService;

    static Stream<Long> getElementUnitTest() {
        return Stream.of(1L);
    }

    static Stream<Long> getElementHistoryUnitTest() {
        return Stream.of(1L);
    }

    static Stream<HospitalDto> saveElementUnitTest() {
        return Stream.of(
                HospitalDto.builder().build()
        );
    }

    static Stream<Long> deleteElementUnitTest() {
        return Stream.of(1L);
    }

    @BeforeEach
    void setup() {
        super.setup(hospitalApplicationService, hospitalDtoMapper, hospitalService);
    }
}
