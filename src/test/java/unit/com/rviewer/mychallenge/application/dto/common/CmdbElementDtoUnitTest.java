package unit.com.rviewer.mychallenge.application.dto.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

public abstract class CmdbElementDtoUnitTest<D extends CmdbElementDto<I>, I> {

    @ParameterizedTest
    public void defaultElementDtoCreationUnitTest(D elementDto) {
        // then
        Assertions.assertNull(elementDto.getId());
    }

}
