package com.rviewer.mychallenge.domain.repository.common;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;

import java.util.List;
import java.util.Optional;

public interface ElementReadOnlyRepository<E extends CmdbElement<I>, I> {

    Optional<E> findById(I id);

    List<E> findAll();

    List<E> findHistory(I id);

}
