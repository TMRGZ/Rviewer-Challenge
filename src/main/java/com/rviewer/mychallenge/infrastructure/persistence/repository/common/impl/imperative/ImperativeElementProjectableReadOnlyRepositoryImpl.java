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

import java.io.Serializable;
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

    protected ImperativeElementProjectableReadOnlyRepositoryImpl(
            GenericDaoMapper<E, D, I> mapper,
            JpaElementProjectableReadOnlyRepository<P, D, I> repository,
            ModelMapper modelMapper
    ) {
        super(mapper, repository);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<E> findById(I id, CmdbRetrieveType type) {
        Class<? extends P> projection = getProjection(type);
        return type == CmdbRetrieveType.COMPLETE
                ? super.findById(id)
                : repository.findById(id, projection)
                .map(projectedElement -> modelMapper.map(projectedElement, getElementType()));
    }

//    @Override
//    public List<E> findAll(CmdbRetrieveType type) {
//        Class<P> projection = getProjection(type);
//        return repository.findAll(projection).stream()
//                .map(projectedElement -> (E) modelMapper.map(projectedElement, getElementType()))
//                .toList();
//    }

    public Map<CmdbRetrieveType, Class<? extends P>> projectionTypeMap() {
        return Map.of();
    }

    private Class<? extends P> getProjection(CmdbRetrieveType type) {
        return Optional.ofNullable(projectionTypeMap().get(type))
                .orElseThrow();
    }

    private Class<E> getElementType() {
        var typeReference = new ParameterizedTypeReference<E>() {
        };
        return (Class<E>) GenericTypeResolver.resolveType(typeReference.getType(), getClass());
    }
}
