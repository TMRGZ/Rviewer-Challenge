package unit.com.rviewer.mychallenge.application.service.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.mapper.common.GenericDtoMapper;
import com.rviewer.mychallenge.application.service.common.CmdbReadOnlyApplicationService;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.service.common.CmdbReadOnlyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mockito;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class CmdbReadOnlyApplicationServiceUnitTest<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I> {

    GenericDtoMapper<M, D, I> mapper;
    private CmdbReadOnlyApplicationService<M, D, I> readOnlyApplicationService;
    private CmdbReadOnlyService<M, I> service;

    public void setup(
            CmdbReadOnlyApplicationService<M, D, I> readOnlyApplicationService,
            GenericDtoMapper<M, D, I> mapper,
            CmdbReadOnlyService<M, I> service
    ) {
        this.readOnlyApplicationService = readOnlyApplicationService;
        this.mapper = mapper;
        this.service = service;
    }

    Class<M> getElementClass() {
        var typeReference = new ParameterizedTypeReference<M>() {
        };
        return (Class<M>) GenericTypeResolver.resolveType(typeReference.getType(), getClass());
    }

    Class<D> getElementDtoClass() {
        var typeReference = new ParameterizedTypeReference<D>() {
        };
        return (Class<D>) GenericTypeResolver.resolveType(typeReference.getType(), getClass());
    }

    @Test
    public void getAllElementsUnitTest() {
        // given
        var element = ReflectionUtils.newInstance(getElementClass());
        var elementList = List.of(element);
        Mockito.when(service.getAll()).thenReturn(elementList);
        // and
        elementList.forEach(m -> {
            var elementDto = ReflectionUtils.newInstance(getElementDtoClass());
            elementDto.setId(m.getId());
            Mockito.when(mapper.mapToDto(m)).thenReturn(elementDto);
        });

        // when
        var response = readOnlyApplicationService.getAllElements();

        // then
        Mockito.verify(service).getAll();
        elementList.forEach(m -> Mockito.verify(mapper).mapToDto(m));
        // and
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        // and
        Assertions.assertNotNull(response.getBody());
        var responseBody = response.getBody();
        var responseBodyId = responseBody.stream().map(CmdbElementDto::getId).toList();
        var elementListId = elementList.stream().map(CmdbElement::getId).toList();
        Assertions.assertEquals(elementListId, responseBodyId);
    }

    @MethodSource
    @ParameterizedTest
    public void getElementUnitTest(I id) {
        // given
        var element = ReflectionUtils.newInstance(getElementClass());
        element.setId(id);
        Mockito.when(service.getById(id)).thenReturn(element);
        // and
        var elementDto = ReflectionUtils.newInstance(getElementDtoClass());
        elementDto.setId(element.getId());
        Mockito.when(mapper.mapToDto(element)).thenReturn(elementDto);

        // when
        var response = readOnlyApplicationService.getElement(id);

        // then
        Mockito.verify(service).getById(id);
        Mockito.verify(mapper).mapToDto(element);
        // and
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        // and
        Assertions.assertNotNull(response.getBody());
        var responseBody = response.getBody();
        Assertions.assertEquals(id, responseBody.getId());
    }

    @MethodSource
    @ParameterizedTest
    public void getElementHistoryUnitTest(I id) {
        // given
        var historyElement = ReflectionUtils.newInstance(getElementClass());
        historyElement.setId(id);
        var historyElementList = List.of(historyElement);
        Mockito.when(service.getHistory(id)).thenReturn(historyElementList);
        // and
        historyElementList.forEach(m -> {
            var historyElementDto = ReflectionUtils.newInstance(getElementDtoClass());
            historyElementDto.setId(m.getId());
            Mockito.when(mapper.mapToDto(m)).thenReturn(historyElementDto);
        });

        // when
        var response = readOnlyApplicationService.getElementHistory(id);

        // then
        Mockito.verify(service).getHistory(id);
        historyElementList.forEach(m -> Mockito.verify(mapper).mapToDto(m));
        // and
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        // and
        Assertions.assertNotNull(response.getBody());
        var responseBody = response.getBody();
        responseBody.forEach(d -> Assertions.assertEquals(id, d.getId()));
    }

}
