package unit.com.rviewer.mychallenge.application.dto.hospital;

import com.rviewer.mychallenge.application.dto.hospital.HospitalDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import unit.com.rviewer.mychallenge.application.dto.common.CmdbElementDtoUnitTest;

import java.util.stream.Stream;

class HospitalDtoUnitTest extends CmdbElementDtoUnitTest<HospitalDto, Long> {

    static Stream<HospitalDto> defaultElementDtoCreationUnitTest() {
        HospitalDto hospitalDto = HospitalDto.builder()
                .build();

        return Stream.of(hospitalDto);
    }

    @Override
    @MethodSource
    @ParameterizedTest
    public void defaultElementDtoCreationUnitTest(HospitalDto elementDto) {
        super.defaultElementDtoCreationUnitTest(elementDto);
        Assertions.assertNull(elementDto.getName());
        Assertions.assertNull(elementDto.getAddress());
        Assertions.assertNull(elementDto.getHospitalDelegateList());
        Assertions.assertNull(elementDto.getCentralHospital());
    }

}
