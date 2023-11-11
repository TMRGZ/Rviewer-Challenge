package com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.imperative;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.CmdbRetrieveType;
import com.rviewer.mychallenge.domain.repository.common.imperative.ImperativeElementProjectableReadOnlyRepository;
import com.rviewer.mychallenge.infrastructure.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.projection.common.CmdbElementProjection;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.projection.JpaElementProjectableReadOnlyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class ImperativeElementProjectableReadOnlyRepositoryImpl<
        E extends CmdbElement<I>,
        P extends CmdbElementProjection<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable>
        extends ImperativeElementReadOnlyRepositoryImpl<E, D, I> implements ImperativeElementProjectableReadOnlyRepository<E, I> {

    private final JpaElementProjectableReadOnlyRepository<P, D, I> repository;

    private final ModelMapper modelMapper;

    private final Map<CmdbRetrieveType, Class<? extends P>> availableProjections;

    protected ImperativeElementProjectableReadOnlyRepositoryImpl(
            GenericDaoMapper<E, D, I> mapper,
            JpaElementProjectableReadOnlyRepository<P, D, I> repository,
            ModelMapper modelMapper) {
        super(mapper, repository);
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.availableProjections = generateProjectionMap();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(I id, CmdbRetrieveType type) {
        return switch (type) {
            case COMPLETE -> super.findById(id);
            default -> {
                Class<? extends P> projection = getProjection(type);
                yield repository.findById(id, projection)
                        .map(projectedElement -> modelMapper.map(projectedElement, getElementType()));
            }
        };
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll(CmdbRetrieveType type) {
        return switch (type) {
            case COMPLETE -> super.findAll();
            default -> {
                Class<? extends P> projection = getProjection(type);
                yield repository.findAllBy(projection).stream()
                        .map(projectedElement -> modelMapper.map(projectedElement, getElementType()))
                        .toList();
            }
        };
    }

    private Map<CmdbRetrieveType, Class<? extends P>> generateProjectionMap() {
        Map<CmdbRetrieveType, Class<? extends P>> projectionMap = new EnumMap<>(CmdbRetrieveType.class);
        projectionMap.put(CmdbRetrieveType.BASIC, basicProjection());
        projectionMap.put(CmdbRetrieveType.COMPLETE, null);
        projectionMap.putAll(additionalProjections());
        return projectionMap;
    }

    private Class<? extends P> getProjection(CmdbRetrieveType type) {
        return Optional.ofNullable(availableProjections.get(type))
                .orElseThrow();
    }

    public Map<CmdbRetrieveType, Class<? extends P>> additionalProjections() {
        return Map.of();
    }

    public abstract Class<? extends P> basicProjection();

    private Class<E> getElementType() {
        var typeReference = new ParameterizedTypeReference<E>() {
        };
        return (Class<E>) GenericTypeResolver.resolveType(typeReference.getType(), getClass());
    }
}
