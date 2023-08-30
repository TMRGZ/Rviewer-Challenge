package unit.com.rviewer.mychallenge.application.mapper.hospital;

import com.rviewer.mychallenge.application.dto.hospital.HospitalDto;
import com.rviewer.mychallenge.application.mapper.hospital.HospitalDtoMapperImpl;
import com.rviewer.mychallenge.domain.model.hospital.Hospital;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import unit.com.rviewer.mychallenge.application.mapper.common.GenericDtoMapperUnitTest;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class HospitalDtoMapperUnitTest extends GenericDtoMapperUnitTest<Hospital, HospitalDto, Long> {

    @InjectMocks
    private HospitalDtoMapperImpl hospitalDtoMapper;

    static Stream<HospitalDto> mapToModelUnitTest() {
        return Stream.of(
                HospitalDto.builder()
                        .id(1L)
                        .name("NAME")
                        .address("ADDRESS")
                        .centralHospitalId(2L)
                        .hospitalDelegateList(List.of(HospitalDto.builder().id(3L).hospitalDelegateList(List.of()).build()))
                        .build()
        );
    }

    static Stream<Hospital> mapToDtoUnitTest() {
        return Stream.of(
                Hospital.builder()
                        .id(1L)
                        .name("NAME")
                        .address("ADDRESS")
                        .centralHospitalId(2L)
                        .hospitalDelegateList(List.of(Hospital.builder().id(3L).hospitalDelegateList(List.of()).build()))
                        .build()
        );
    }

    @BeforeEach
    void setup() {
        super.setup(hospitalDtoMapper);
    }

    @Override
    public void mapToModelUnitTestAssertions(HospitalDto dto, Hospital mappedModel) {
        super.mapToModelUnitTestAssertions(dto, mappedModel);

        Assertions.assertEquals(dto.getName(), mappedModel.getName());
        Assertions.assertEquals(dto.getAddress(), mappedModel.getAddress());
        Assertions.assertEquals(dto.getCentralHospitalId(), mappedModel.getCentralHospitalId());
        Assertions.assertEquals(dto.getHospitalDelegateList().size(), mappedModel.getHospitalDelegateList().size());

        for (int i = 0; i < dto.getHospitalDelegateList().size(); i++) {
            mapToModelUnitTestAssertions(
                    dto.getHospitalDelegateList().get(i),
                    mappedModel.getHospitalDelegateList().get(i)
            );
        }
    }

    @Override
    public void mapToDtoUnitTestAssertions(Hospital model, HospitalDto mappedDto) {
        super.mapToDtoUnitTestAssertions(model, mappedDto);

        Assertions.assertEquals(model.getName(), mappedDto.getName());
        Assertions.assertEquals(model.getAddress(), mappedDto.getAddress());
        Assertions.assertEquals(model.getCentralHospitalId(), mappedDto.getCentralHospitalId());
        Assertions.assertEquals(model.getHospitalDelegateList().size(), mappedDto.getHospitalDelegateList().size());

        for (int i = 0; i < model.getHospitalDelegateList().size(); i++) {
            mapToDtoUnitTestAssertions(
                    model.getHospitalDelegateList().get(i),
                    mappedDto.getHospitalDelegateList().get(i)
            );
        }
    }
}
