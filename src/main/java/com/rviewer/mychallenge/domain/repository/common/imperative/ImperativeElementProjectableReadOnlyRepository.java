package com.rviewer.mychallenge.domain.repository.common.imperative;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.CmdbRetrieveType;

import java.util.Optional;

public interface ImperativeElementProjectableReadOnlyRepository<E extends CmdbElement<I>, I>
        extends ImperativeElementReadOnlyRepository<E, I> {

    @Override
    default Optional<E> findById(I id) {
        return findById(id, CmdbRetrieveType.COMPLETE);
    }

    Optional<E> findById(I id, CmdbRetrieveType type);

//    @Override
//    default List<E> findAll() {
//        return findAll(CmdbRetrieveType.COMPLETE);
//    }
//
//    List<E> findAll(CmdbRetrieveType type);

}
