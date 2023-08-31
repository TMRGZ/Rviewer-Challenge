package com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa;

import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface JpaElementCrudRepository<D extends CmdbElementDao<I>, I extends Serializable>
        extends JpaElementReadOnlyRepository<D, I>, JpaRepository<D, I> {

    @NonNull
    @Override
    Optional<D> findById(@NonNull I id);
}
