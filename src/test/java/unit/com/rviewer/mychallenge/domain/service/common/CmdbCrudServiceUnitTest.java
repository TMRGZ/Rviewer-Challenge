package unit.com.rviewer.mychallenge.domain.service.common;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.ElementCrudRepository;
import com.rviewer.mychallenge.domain.service.common.CmdbCrudService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mockito;

import java.util.Optional;

public abstract class CmdbCrudServiceUnitTest<E extends CmdbElement<I>, I> extends CmdbReadOnlyServiceUnitTest<E, I> {

    private CmdbCrudService<E, I> crudService;

    private ElementCrudRepository<E, I> repository;

    public void setup(
            CmdbCrudService<E, I> crudService,
            ElementCrudRepository<E, I> repository
    ) {
        super.setup(crudService, repository);
        this.crudService = crudService;
        this.repository = repository;
    }

    @MethodSource
    @ParameterizedTest
    public void saveUnitTest(E elementToSave) {
        // given
        Mockito.when(repository.save(elementToSave)).thenReturn(elementToSave);

        // when
        E savedElement = crudService.save(elementToSave);

        // then
        Mockito.verify(repository).save(elementToSave);
        // and
        Assertions.assertNotNull(savedElement);
    }

    @MethodSource
    @ParameterizedTest
    public void deleteUnitTest(I id) {
        // given
        E elementToDelete = ReflectionUtils.newInstance(getElementClass());
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(elementToDelete));

        // when
        crudService.delete(id);

        // then
        Mockito.verify(repository).findById(id);
        Mockito.verify(repository).delete(elementToDelete);
    }

    @MethodSource
    @ParameterizedTest
    public void delete_ButNotFound_UnitTest(I id) {
        // given
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        // when
        crudService.delete(id);

        // then
        Mockito.verify(repository).findById(id);
        Mockito.verify(repository, Mockito.never()).delete(Mockito.any());
    }

}
