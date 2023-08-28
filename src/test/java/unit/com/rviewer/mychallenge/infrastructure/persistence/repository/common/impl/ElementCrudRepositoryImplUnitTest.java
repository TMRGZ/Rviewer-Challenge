package unit.com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.infrastructure.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.ElementCrudRepositoryImpl;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementCrudRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public abstract class ElementCrudRepositoryImplUnitTest<
        E extends CmdbElement<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable>
        extends ElementReadOnlyRepositoryImplUnitTest<E, D, I> {

    private ElementCrudRepositoryImpl<E, D, I> repository;
    private GenericDaoMapper<E, D, I> mapper;
    private JpaElementCrudRepository<D, I> jpaElementCrudRepository;

    public void setup(
            ElementCrudRepositoryImpl<E, D, I> repository,
            GenericDaoMapper<E, D, I> mapper,
            JpaElementCrudRepository<D, I> jpaElementCrudRepository
    ) {
        super.setup(repository, mapper, jpaElementCrudRepository);
        this.repository = repository;
        this.mapper = mapper;
        this.jpaElementCrudRepository = jpaElementCrudRepository;
    }

    @MethodSource
    @ParameterizedTest
    public void saveUnitTest(E elementToSave, Class<D> daoClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // given
        D mappedDao = daoClass.getDeclaredConstructor().newInstance();
        Mockito.when(mapper.mapToDao(elementToSave)).thenReturn(mappedDao);
        // and
        Mockito.when(jpaElementCrudRepository.save(mappedDao)).thenReturn(mappedDao);
        // and
        Mockito.when(mapper.mapToModel(mappedDao)).thenReturn(elementToSave);

        // then
        repository.save(elementToSave);

        // when
        Mockito.verify(mapper).mapToDao(elementToSave);
        Mockito.verify(jpaElementCrudRepository).save(mappedDao);
        Mockito.verify(mapper).mapToModel(mappedDao);
    }

    @MethodSource
    @ParameterizedTest
    public void deleteUnitTest(E elementToDelete, Class<D> daoClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // given
        D mappedDao = daoClass.getDeclaredConstructor().newInstance();
        Mockito.when(mapper.mapToDao(elementToDelete)).thenReturn(mappedDao);
        // and
        Mockito.when(jpaElementCrudRepository.save(mappedDao)).thenReturn(mappedDao);

        // then
        repository.delete(elementToDelete);

        // when
        Mockito.verify(mapper).mapToDao(elementToDelete);
        Mockito.verify(jpaElementCrudRepository).save(mappedDao);
        // and
        Assertions.assertFalse(mappedDao.getActive());
    }
}
