package com.rviewer.mychallenge.domain.service.common;


import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.ElementCrudRepository;

public abstract class CmdbCrudService<E extends CmdbElement<I>, R extends ElementCrudRepository<E, I>, I>
        extends CmdbReadOnlyService<E, R, I> {

    protected CmdbCrudService(R repository) {
        super(repository);
    }

    public E save(E element) {
        return repository.save(element);
    }

    public void delete(E element) {
        repository.delete(element);
    }
}