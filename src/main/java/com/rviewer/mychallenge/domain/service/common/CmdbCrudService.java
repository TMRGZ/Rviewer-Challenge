package com.rviewer.mychallenge.domain.service.common;


import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.ElementCrudRepository;

public abstract class CmdbCrudService<E extends CmdbElement<I>, I> extends CmdbReadOnlyService<E, I> {

    private final ElementCrudRepository<E, I> repository;

    protected CmdbCrudService(ElementCrudRepository<E, I> repository) {
        super(repository);
        this.repository = repository;
    }

    public E save(E element) {
        return repository.save(element);
    }

    public void delete(I id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}