package integration.com.rviewer.mychallenge.infrastructure.controller.hospital;

import com.rviewer.mychallenge.application.dto.hospital.HospitalDto;
import com.rviewer.mychallenge.infrastructure.persistence.dao.hospital.HospitalDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.hospital.jpa.JpaHospitalRepository;
import integration.com.rviewer.mychallenge.infrastructure.controller.common.CmdbCrudControllerIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

class HospitalControllerIntegrationTest extends CmdbCrudControllerIntegrationTest<HospitalDto, HospitalDao, Long> {

    private static final String BASE_ENDPOINT = "/hospital";

    @Autowired
    private JpaHospitalRepository hospitalRepository;

    static Stream<Long> getElementIntegrationTest() {
        return Stream.of(101L);
    }

    static Stream<Long> getElementHistoryIntegrationTest() {
        return Stream.of(101L);
    }

    static Stream<HospitalDto> saveElementIntegrationTest() {
        return Stream.of(
                HospitalDto.builder()
                        .name("NAME")
                        .address("ADDRESS")
                        .build()
        );
    }

    static Stream<Long> deleteElementIntegrationTest() {
        return Stream.of(100L);
    }

    @BeforeEach
    void setup() {
        super.setup(BASE_ENDPOINT, hospitalRepository);
    }
}
