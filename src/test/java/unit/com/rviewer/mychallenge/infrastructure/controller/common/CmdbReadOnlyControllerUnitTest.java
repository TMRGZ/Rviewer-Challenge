package unit.com.rviewer.mychallenge.infrastructure.controller.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.application.service.common.CmdbReadOnlyApplicationService;
import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import com.rviewer.mychallenge.infrastructure.controller.common.CmdbReadOnlyController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public abstract class CmdbReadOnlyControllerUnitTest<M extends CmdbElement<I>, D extends CmdbElementDto<I>, I> {

    private CmdbReadOnlyController<M, D, I> readOnlyController;

    private CmdbReadOnlyApplicationService<M, D, I> readOnlyApplicationService;

    public void setup(
            CmdbReadOnlyController<M, D, I> readOnlyController,
            CmdbReadOnlyApplicationService<M, D, I> readOnlyApplicationService
    ) {
        this.readOnlyController = readOnlyController;
        this.readOnlyApplicationService = readOnlyApplicationService;
    }

    @Test
    void getAllElementsUnitTest() {
        // when
        readOnlyController.getAllElements();

        // then
        Mockito.verify(readOnlyApplicationService).getAllElements();
    }

    @MethodSource
    @ParameterizedTest
    void getElementUnitTest(I id) {
        // when
        readOnlyController.getElement(id);

        // then
        Mockito.verify(readOnlyApplicationService).getElement(id);
    }

    @MethodSource
    @ParameterizedTest
    void getElementHistoryUnitTest(I id) {
        // when
        readOnlyController.getElementHistory(id);

        // then
        Mockito.verify(readOnlyApplicationService).getElementHistory(id);
    }
}
