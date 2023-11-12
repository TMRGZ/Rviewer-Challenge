package com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.projection;

import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.projection.common.CmdbElementProjection;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface JpaElementProjectableCrudRepository<
        P extends CmdbElementProjection<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable>
        extends JpaElementProjectableReadOnlyRepository<P, D, I>, JpaElementCrudRepository<D, I> {

    @Override
    @NonNull
    Optional<D> findById(@NonNull I id);
}
