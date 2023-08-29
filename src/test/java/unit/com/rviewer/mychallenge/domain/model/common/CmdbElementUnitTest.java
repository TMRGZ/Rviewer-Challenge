package unit.com.rviewer.mychallenge.domain.model.common;

import com.rviewer.mychallenge.domain.model.common.CmdbElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

public abstract class CmdbElementUnitTest<E extends CmdbElement<I>, I> {

    @ParameterizedTest
    public void defaultElementCreationUnitTest(E element) {
        // then
        Assertions.assertNull(element.getId());
    }
}
