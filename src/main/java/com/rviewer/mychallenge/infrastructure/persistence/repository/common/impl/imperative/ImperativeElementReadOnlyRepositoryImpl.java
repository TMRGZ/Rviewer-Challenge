package com.rviewer.mychallenge.infrastructure.persistence.repository.common.impl.imperative;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.domain.repository.common.imperative.ImperativeElementReadOnlyRepository;
import com.rviewer.mychallenge.infrastructure.config.CachingConfig;
import com.rviewer.mychallenge.infrastructure.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementReadOnlyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.history.Revision;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheResolver = CachingConfig.CACHE_RESOLVER_NAME)
@RequiredArgsConstructor
public abstract class ImperativeElementReadOnlyRepositoryImpl<
        E extends CmdbElement<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable>
        implements ImperativeElementReadOnlyRepository<E, I> {

    final GenericDaoMapper<E, D, I> mapper;
    private final JpaElementReadOnlyRepository<D, I> repository;

    @Override
    @Cacheable
    @Transactional
    public Optional<E> findById(I id) {
        return repository.findById(id)
                .map(mapper::mapToModel);
    }
    @Override
    @Cacheable(key = "'all'")
    @Transactional
    public List<E> findAll() {
        return repository.findAll().stream()
                .filter(CmdbElementDao::getActive)
                .map(mapper::mapToModel)
                .toList();
    }
    @Override
    @Cacheable(key = "id + '-history'")
    @Transactional
    public List<E> findHistory(I id) {
        return repository.findRevisions(id).stream()
                .map(Revision::getEntity)
                .map(mapper::mapToModel)
                .toList();
    }
}
