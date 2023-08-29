package unit.com.rviewer.mychallenge.application.service.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.mapper.common.GenericDtoMapper;
import com.rviewer.mychallenge.application.service.common.CmdbCrudApplicationService;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.service.common.CmdbCrudService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

public abstract class CmdbCrudApplicationServiceUnitTest<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I>
        extends CmdbReadOnlyApplicationServiceUnitTest<M, D, I> {

    private CmdbCrudApplicationService<M, D, I> crudApplicationService;

    private CmdbCrudService<M, I> service;


    public void setup(
            CmdbCrudApplicationService<M, D, I> crudApplicationService,
            GenericDtoMapper<M, D, I> mapper,
            CmdbCrudService<M, I> service
    ) {
        super.setup(crudApplicationService, mapper, service);
        this.crudApplicationService = crudApplicationService;
        this.service = service;
    }

    @MethodSource
    @ParameterizedTest
    public void saveElementUnitTest(D elementToSave) {
        // given
        var elementModel = ReflectionUtils.newInstance(getElementClass());
        Mockito.when(mapper.mapToModel(elementToSave)).thenReturn(elementModel);
        // and
        Mockito.when(service.save(elementModel)).thenReturn(elementModel);
        // and
        Mockito.when(mapper.mapToDto(elementModel)).thenReturn(elementToSave);

        // when
        var response = crudApplicationService.saveElement(elementToSave);

        // then
        Mockito.verify(mapper).mapToModel(elementToSave);
        Mockito.verify(service).save(elementModel);
        Mockito.verify(mapper).mapToDto(elementModel);
        // and
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @MethodSource
    @ParameterizedTest
    public void deleteElementUnitTest(I id) {
        // when
        var response = crudApplicationService.deleteElement(id);

        // then
        Mockito.verify(service).delete(id);
        // and
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

}
