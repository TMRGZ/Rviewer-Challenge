package com.rviewer.mychallenge.infrastructure.persistence.controller.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.service.common.CmdbCrudApplicationService;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class CmdbCrudController<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I> extends CmdbReadOnlyController<M, D, I> {

    private final CmdbCrudApplicationService<M, D, I> service;

    public CmdbCrudController(CmdbCrudApplicationService<M, D, I> service) {
        super(service);
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<D> saveElement(@RequestBody D elementToSave) {
        return service.saveElement(elementToSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable I id) {
        return service.deleteElement(id);
    }
}
