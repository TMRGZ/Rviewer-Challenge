package unit.com.rviewer.mychallenge.infrastructure.controller.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.service.common.CmdbCrudApplicationService;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.infrastructure.controller.common.CmdbCrudController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;


public abstract class CmdbCrudControllerUnitTest<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I>
        extends CmdbReadOnlyControllerUnitTest<M, D, I> {

    private CmdbCrudController<M, D, I> crudController;

    private CmdbCrudApplicationService<M, D, I> crudApplicationService;


    public void setup(
            CmdbCrudController<M, D, I> crudController,
            CmdbCrudApplicationService<M, D, I> crudApplicationService
    ) {
        super.setup(crudController, crudApplicationService);
        this.crudController = crudController;
        this.crudApplicationService = crudApplicationService;
    }


    @MethodSource
    @ParameterizedTest
    void saveElementUnitTest(D element) {
        // when
        crudController.saveElement(element);

        // then
        Mockito.verify(crudApplicationService).saveElement(element);
    }

    @MethodSource
    @ParameterizedTest
    void deleteElementUnitTest(I id) {
        // when
        crudController.deleteElement(id);

        // then
        Mockito.verify(crudApplicationService).deleteElement(id);
    }
}
