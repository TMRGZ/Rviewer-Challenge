package unit.com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.infrastructure.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.ElementReadOnlyRepositoryImpl;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementReadOnlyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mockito;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class ElementReadOnlyRepositoryImplUnitTest<
        E extends CmdbElement<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable> {

    private ElementReadOnlyRepositoryImpl<E, D, I> repository;
    private GenericDaoMapper<E, D, I> mapper;
    private JpaElementReadOnlyRepository<D, I> jpaElementReadOnlyRepository;

    public void setup(
            ElementReadOnlyRepositoryImpl<E, D, I> repository,
            GenericDaoMapper<E, D, I> mapper,
            JpaElementReadOnlyRepository<D, I> jpaElementReadOnlyRepository
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.jpaElementReadOnlyRepository = jpaElementReadOnlyRepository;
    }

    Class<E> getElementClass() {
        var typeReference = new ParameterizedTypeReference<E>() {
        };
        return (Class<E>) GenericTypeResolver.resolveType(typeReference.getType(), getClass());
    }

    Class<D> getElementDaoClass() {
        var typeReference = new ParameterizedTypeReference<D>() {
        };
        return (Class<D>) GenericTypeResolver.resolveType(typeReference.getType(), getClass());
    }

    @MethodSource
    @ParameterizedTest
    public void findByIdUnitTest(I id) {
        // given
        D foundDao = ReflectionUtils.newInstance(getElementDaoClass());
        foundDao.setId(id);
        Mockito.when(jpaElementReadOnlyRepository.findById(id)).thenReturn(Optional.of(foundDao));
        // and
        E mappedModel = ReflectionUtils.newInstance(getElementClass());
        mappedModel.setId(foundDao.getId());
        Mockito.when(mapper.mapToModel(foundDao)).thenReturn(mappedModel);

        // when
        Optional<E> foundByIdOptional = repository.findById(id);

        // then
        Mockito.verify(jpaElementReadOnlyRepository).findById(id);
        Mockito.verify(mapper).mapToModel(foundDao);
        // and
        Assertions.assertTrue(foundByIdOptional.isPresent());
        E foundById = foundByIdOptional.get();
        Assertions.assertEquals(id, foundById.getId());
    }

    @MethodSource
    @ParameterizedTest
    public void findById_ButNotFound_UnitTest(I id) {
        // given
        Mockito.when(jpaElementReadOnlyRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Optional<E> foundByIdOptional = repository.findById(id);

        // then
        Mockito.verify(jpaElementReadOnlyRepository).findById(id);
        Mockito.verify(mapper, Mockito.never()).mapToModel(Mockito.any());
        // and
        Assertions.assertTrue(foundByIdOptional.isEmpty());
    }

    @MethodSource
    @ParameterizedTest
    public void findAllUnitTest(List<D> elementDaoList) {
        // given
        Mockito.when(jpaElementReadOnlyRepository.findAll()).thenReturn(elementDaoList);
        // and
        elementDaoList.stream()
                .filter(CmdbElementDao::getActive)
                .forEach(elementDao -> {
                    E elementModel = ReflectionUtils.newInstance(getElementClass());
                    elementModel.setId(elementDao.getId());
                    Mockito.when(mapper.mapToModel(elementDao)).thenReturn(elementModel);
                });

        // when
        List<E> allFound = repository.findAll();

        // then
        Mockito.verify(jpaElementReadOnlyRepository).findAll();
        elementDaoList.stream()
                .filter(CmdbElementDao::getActive)
                .forEach(elementDao -> Mockito.verify(mapper).mapToModel(elementDao));
        elementDaoList.stream()
                .filter(elementDao -> !elementDao.getActive())
                .forEach(elementDao -> Mockito.verify(mapper, Mockito.never()).mapToModel(elementDao));
        // and
        List<I> allFoundId = allFound.stream().map(CmdbElement::getId).toList();
        elementDaoList.forEach(elementDao ->
                Assertions.assertEquals(elementDao.getActive(), allFoundId.contains(elementDao.getId())));
    }

    @MethodSource
    @ParameterizedTest
    public void findHistoryUnitTest(I id, Revisions<Integer, D> revisionList) {
        // given
        Mockito.when(jpaElementReadOnlyRepository.findRevisions(id)).thenReturn(revisionList);
        // and
        revisionList.stream()
                .map(Revision::getEntity)
                .forEach(revision -> {
                    E elementModel = ReflectionUtils.newInstance(getElementClass());
                    elementModel.setId(revision.getId());
                    Mockito.when(mapper.mapToModel(revision)).thenReturn(elementModel);
                });

        // when
        List<E> historyList = repository.findHistory(id);

        // then
        Mockito.verify(jpaElementReadOnlyRepository).findRevisions(id);
        revisionList.forEach(revision -> Mockito.verify(mapper).mapToModel(revision.getEntity()));
        // and
        Assertions.assertEquals(revisionList.getContent().size(), historyList.size());
    }


}
