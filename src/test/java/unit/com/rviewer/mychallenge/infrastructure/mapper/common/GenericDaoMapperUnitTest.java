package unit.com.rviewer.mychallenge.infrastructure.mapper.common;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.infrastructure.mapper.common.GenericDaoMapper;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.Serializable;

public abstract class GenericDaoMapperUnitTest<
        M extends CmdbElement<I>,
        D extends CmdbElementDao<I>,
        I extends Serializable> {

    private GenericDaoMapper<M, D, I> mapper;

    public void setup(
            GenericDaoMapper<M, D, I> mapper
    ) {
        this.mapper = mapper;
    }

    @MethodSource
    @ParameterizedTest
    public void mapToModelUnitTest(D dao) {
        // when
        var mappedModel = mapper.mapToModel(dao);

        // then
        mapToModelUnitTestAssertions(dao, mappedModel);
    }

    public void mapToModelUnitTestAssertions(D dao, M mappedModel) {
        Assertions.assertEquals(dao.getId(), mappedModel.getId());
    }

    @MethodSource
    @ParameterizedTest
    public void mapToDaoUnitTest(M model) {
        // when
        var mappedDao = mapper.mapToDao(model);

        // then
        mapToDaoUnitTestAssertions(model, mappedDao);
    }

    public void mapToDaoUnitTestAssertions(M model, D mappedDao) {
        Assertions.assertEquals(model.getId(), mappedDao.getId());
    }
}
