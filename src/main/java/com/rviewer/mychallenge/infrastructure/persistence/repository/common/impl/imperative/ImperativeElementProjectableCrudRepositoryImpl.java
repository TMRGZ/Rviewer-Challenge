package com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.imperative;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.CmdbRetrieveType;
import com.rviewer.mychallenge.domain.repository.common.imperative.ImperativeElementProjectableCrudRepository;
import com.rviewer.mychallenge.infrastructure.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.projection.common.CmdbElementProjection;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.projection.JpaElementProjectableCrudRepository;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public abstract class ImperativeElementProjectableCrudRepositoryImpl<
        E extends CmdbElement<I>,
        P extends CmdbElementProjection<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable>
        extends ImperativeElementProjectableReadOnlyRepositoryImpl<E, P, D, I>
        implements ImperativeElementProjectableCrudRepository<E, I> {

    private final JpaElementProjectableCrudRepository<P, D, I> repository;

    protected ImperativeElementProjectableCrudRepositoryImpl(
            GenericDaoMapper<E, D, I> mapper,
            JpaElementProjectableCrudRepository<P, D, I> repository,
            ModelMapper modelMapper) {
        super(mapper, repository, modelMapper);
        this.repository = repository;
    }

    @Override
    public E save(E element) {
        throw new RuntimeException();
    }

    @Override
    @Transactional
    public E save(E element, CmdbRetrieveType type) {
        return switch (type) {
            case COMPLETE -> basicSave(element);
            default -> differentialSave(element, type);
        };
    }

    private E basicSave(E element) {
        D dao = mapper.mapToDao(element);
        return mapper.mapToModel(repository.save(dao));
    }

    private E differentialSave(E element, CmdbRetrieveType type) {
        return repository.findById(element.getId())
                .map(e -> {
                    var projectedElement = modelMapper.map(element, super.getProjection(type));
                    modelMapper.map(projectedElement, e);
                    return e;
                })
                .map(repository::save)
                .map(mapper::mapToModel)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(E element) {
        D dao = mapper.mapToDao(element);
        dao.setActive(false);
        repository.save(dao);
    }
}
