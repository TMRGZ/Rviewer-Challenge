package com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.ElementReadOnlyRepository;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementReadOnlyRepository;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class ElementReadOnlyRepositoryImpl<
        E extends CmdbElement<I>,
        D extends CmdbElementDao<I>,
        R extends JpaElementReadOnlyRepository<D, I>,
        M extends GenericDaoMapper<E, D, I>,
        I extends Serializable>
        implements ElementReadOnlyRepository<E, I> {

    final R repository;

    final M mapper;

    @Override
    public Optional<E> findById(I id) {
        return repository.findById(id)
                .map(mapper::mapToModel);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll().stream()
                .map(mapper::mapToModel)
                .toList();
    }
}
