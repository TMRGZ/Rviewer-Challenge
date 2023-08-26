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
        R extends JpaElementCrudRepository<D, I>,
        I extends Serializable,
        M extends GenericDaoMapper<E, D, I>>
        extends ElementReadOnlyRepositoryImpl<E, D, R, M, I>
        implements ElementCrudRepository<E, I> {

    protected ElementCrudRepositoryImpl(R repository, M mapper) {
        super(repository, mapper);
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
