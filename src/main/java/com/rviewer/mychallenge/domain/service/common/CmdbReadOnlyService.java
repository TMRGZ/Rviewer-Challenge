package com.rviewer.mychallenge.domain.service.common;

import com.rviewer.mychallenge.domain.model.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.ElementReadOnlyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class CmdbReadOnlyService<E extends CmdbElement<I>, R extends ElementReadOnlyRepository<E, I>, I> {

    final R repository;

    public E getById(I id) {
        return repository.findById(id)
                .orElse(null);
    }

    public List<E> getAll() {
        return repository.findAll();
    }
}
