package com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.imperative;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.imperative.ImperativeElementCrudRepository;
import com.rviewer.mychallenge.infrastructure.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public abstract class ImperativeElementCrudRepositoryImpl<
        E extends CmdbElement<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable>
        extends ImperativeElementReadOnlyRepositoryImpl<E, D, I> implements ImperativeElementCrudRepository<E, I> {

    private final JpaElementCrudRepository<D, I> repository;

    protected ImperativeElementCrudRepositoryImpl(GenericDaoMapper<E, D, I> mapper, JpaElementCrudRepository<D, I> repository) {
        super(mapper, repository);
        this.repository = repository;
    }

    @Override
    @Transactional
    public E save(E element) {
        D dao = mapper.mapToDao(element);
        return mapper.mapToModel(repository.save(dao));
    }

    @Override
    @Transactional
    public void delete(E element) {
        D dao = mapper.mapToDao(element);
        dao.setActive(false);
        repository.save(dao);
    }
}
