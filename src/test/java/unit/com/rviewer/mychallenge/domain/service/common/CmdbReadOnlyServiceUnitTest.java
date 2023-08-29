package unit.com.rviewer.mychallenge.domain.service.common;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.ElementReadOnlyRepository;
import com.rviewer.mychallenge.domain.service.common.CmdbReadOnlyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mockito;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ParameterizedTypeReference;

import java.util.ArrayList;
import java.util.Optional;

public abstract class CmdbReadOnlyServiceUnitTest<E extends CmdbElement<I>, I> {

    private CmdbReadOnlyService<E, I> readOnlyService;

    private ElementReadOnlyRepository<E, I> repository;

    public void setup(
            CmdbReadOnlyService<E, I> readOnlyService,
            ElementReadOnlyRepository<E, I> repository
    ) {
        this.readOnlyService = readOnlyService;
        this.repository = repository;
    }

    Class<E> getElementClass() {
        var typeReference = new ParameterizedTypeReference<E>() {
        };
        return (Class<E>) GenericTypeResolver.resolveType(typeReference.getType(), getClass());
    }

    @MethodSource
    @ParameterizedTest
    public void getByIdUnitTest(I id) {
        // given
        var mappedElement = ReflectionUtils.newInstance(getElementClass());
        mappedElement.setId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(mappedElement));

        // when
        var byId = readOnlyService.getById(id);

        // then
        Mockito.verify(repository).findById(id);
        // and
        Assertions.assertEquals(mappedElement, byId);
    }

    @MethodSource
    @ParameterizedTest
    public void getById_ButNotFound_UnitTest(I id) {
        // given
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        // when
        var byId = readOnlyService.getById(id);

        // then
        Mockito.verify(repository).findById(id);
        // and
        Assertions.assertNull(byId);
    }

    @Test
    public void getAllUnitTest() {
        // given
        var elementList = new ArrayList<E>();
        Mockito.when(repository.findAll()).thenReturn(elementList);

        // when
        var all = readOnlyService.getAll();

        // then
        Mockito.verify(repository).findAll();
        // and
        Assertions.assertEquals(elementList, all);
    }

    @MethodSource
    @ParameterizedTest
    public void getHistoryUnitTest(I id) {
        // given
        var historyList = new ArrayList<E>();
        Mockito.when(repository.findHistory(id)).thenReturn(historyList);

        // when
        var foundHistories = readOnlyService.getHistory(id);

        // then
        Mockito.verify(repository).findHistory(id);
        // and
        Assertions.assertEquals(historyList, foundHistories);

    }

}
