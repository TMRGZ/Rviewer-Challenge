package com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.ElementCrudRepository;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementCrudRepository;

import java.io.Serializable;

public abstract class ElementCrudRepositoryImpl<
        E extends CmdbElement<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable>
        extends ElementReadOnlyRepositoryImpl<E, D, I>
        implements ElementCrudRepository<E, I> {

    private final JpaElementCrudRepository<D, I> repository;

    public ElementCrudRepositoryImpl(GenericDaoMapper<E, D, I> mapper, JpaElementCrudRepository<D, I> repository) {
        super(mapper, repository);
        this.repository = repository;
    }


    @Override
    public E save(E element) {
        D dao = mapper.mapToDao(element);
        return mapper.mapToModel(repository.save(dao));
    }

    @Override
    public void delete(E element) {
        D dao = mapper.mapToDao(element);
        repository.delete(dao);
    }
}
