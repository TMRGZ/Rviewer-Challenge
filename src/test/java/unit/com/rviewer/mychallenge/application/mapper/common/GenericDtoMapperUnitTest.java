package unit.com.rviewer.mychallenge.application.mapper.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.mapper.common.GenericDtoMapper;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public abstract class GenericDtoMapperUnitTest<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I> {

    private GenericDtoMapper<M, D, I> mapper;

    public void setup(
            GenericDtoMapper<M, D, I> mapper
    ) {
        this.mapper = mapper;
    }

    @MethodSource
    @ParameterizedTest
    public void mapToModelUnitTest(D dto) {
        // when
        M mappedModel = mapper.mapToModel(dto);

        // then
        mapToModelUnitTestAssertions(dto, mappedModel);
    }

    public void mapToModelUnitTestAssertions(D dto, M mappedModel) {
        Assertions.assertEquals(dto.getId(), mappedModel.getId());
    }

    @MethodSource
    @ParameterizedTest
    public void mapToDtoUnitTest(M model) {
        // when
        D mappedDto = mapper.mapToDto(model);

        // then
        mapToDtoUnitTestAssertions(model, mappedDto);
    }

    public void mapToDtoUnitTestAssertions(M model, D mappedDto) {
        Assertions.assertEquals(model.getId(), mappedDto.getId());
    }
}
