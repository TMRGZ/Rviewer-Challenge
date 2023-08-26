package com.rviewer.mychallenge.infrastructure.persistence.controller.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.service.common.CmdbReadOnlyApplicationService;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
public abstract class CmdbReadOnlyController<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I> {

    private final CmdbReadOnlyApplicationService<M, D, I> service;

    @GetMapping
    public ResponseEntity<List<D>> getAllElements() {
        return service.getAllElements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getElement(@PathVariable I id) {
        return service.getElement(id);
    }
}
