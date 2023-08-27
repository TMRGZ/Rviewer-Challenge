package unit.com.rviewer.mychallenge.infrastructure.config;

import com.rviewer.mychallenge.domain.repository.hospital.HospitalRepository;
import com.rviewer.mychallenge.infrastructure.config.BeanConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BeanConfigUnitTest {

    @InjectMocks
    private BeanConfig beanConfig;

    @Test
    void hospitalServiceUnitTest() {
        // given
        var hospitalRepository = Mockito.mock(HospitalRepository.class);

        // when
        var hospitalService = beanConfig.hospitalService(hospitalRepository);

        // then
        Assertions.assertNotNull(hospitalService);
    }
}
