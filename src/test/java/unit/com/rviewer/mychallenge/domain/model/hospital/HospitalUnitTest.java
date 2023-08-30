package unit.com.rviewer.mychallenge.domain.model.hospital;

import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import unit.com.rviewer.mychallenge.domain.model.common.CmdbElementUnitTest;

import java.util.stream.Stream;

public class HospitalUnitTest extends CmdbElementUnitTest<Hospital, Long> {
    static Stream<Hospital> defaultElementCreationUnitTest() {
        Hospital hospital = Hospital.builder()
                .build();

        return Stream.of(hospital);
    }

    @Override
    @MethodSource
    @ParameterizedTest
    public void defaultElementCreationUnitTest(Hospital element) {
        super.defaultElementCreationUnitTest(element);
        Assertions.assertNull(element.getName());
        Assertions.assertNull(element.getAddress());
        Assertions.assertNull(element.getHospitalDelegateList());
        Assertions.assertNull(element.getCentralHospitalId());
    }
}
