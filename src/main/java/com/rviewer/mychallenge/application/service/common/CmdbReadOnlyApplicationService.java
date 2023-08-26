package com.rviewer.mychallenge.application.service.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.mapper.common.GenericDtoMapper;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.service.common.CmdbReadOnlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
public abstract class CmdbReadOnlyApplicationService<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I> {

    final GenericDtoMapper<M, D, I> mapper;
    private final CmdbReadOnlyService<M, I> service;

    public ResponseEntity<List<D>> getAllElements() {
        List<D> elementListDto = service.getAll().stream()
                .map(mapper::mapToDto)
                .toList();
        return ResponseEntity.ok(elementListDto);
    }

    public ResponseEntity<D> getElement(I id) {
        M element = service.getById(id);
        return ResponseEntity.ok(mapper.mapToDto(element));
    }
}
