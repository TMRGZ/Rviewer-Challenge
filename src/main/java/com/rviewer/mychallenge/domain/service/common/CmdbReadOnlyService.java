package com.rviewer.mychallenge.domain.service.common;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.ElementReadOnlyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class CmdbReadOnlyService<E extends CmdbElement<I>, I> {

    private final ElementReadOnlyRepository<E, I> repository;

    public E getById(I id) {
        return repository.findById(id)
                .orElse(null);
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public List<E> getHistory(I id) {
        return repository.findHistory(id);
    }
}
