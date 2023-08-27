package unit.com.rviewer.mychallenge.infrastructure.controller.hospital;

import com.rviewer.mychallenge.application.dto.hospital.HospitalDto;
import com.rviewer.mychallenge.application.service.hospital.HospitalApplicationService;
import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import com.rviewer.mychallenge.infrastructure.controller.hospital.HospitalController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import unit.com.rviewer.mychallenge.infrastructure.controller.common.CmdbCrudControllerUnitTest;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class HospitalControllerUnitTest extends CmdbCrudControllerUnitTest<Hospital, HospitalDto, Long> {

    @InjectMocks
    private HospitalController hospitalController;

    @Mock
    private HospitalApplicationService hospitalApplicationService;

    static Stream<Long> getElementUnitTest() {
        return Stream.of(1L);
    }

    static Stream<Long> getElementHistoryUnitTest() {
        return Stream.of(1L);
    }

    static Stream<HospitalDto> saveElementUnitTest() {
        return Stream.of(HospitalDto.builder().build());
    }

    static Stream<Long> deleteElementUnitTest() {
        return Stream.of(1L);
    }

    @BeforeEach
    protected void setup() {
        super.setup(hospitalController, hospitalApplicationService);
    }
}
