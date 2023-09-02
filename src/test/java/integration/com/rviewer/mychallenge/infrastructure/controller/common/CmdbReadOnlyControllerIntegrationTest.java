package integration.com.rviewer.mychallenge.infrastructure.controller.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementReadOnlyRepository;
import integration.com.rviewer.mychallenge.infrastructure.controller.BaseControllerIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.core.ParameterizedTypeReference;

import java.io.Serializable;

public abstract class CmdbReadOnlyControllerIntegrationTest<
        D extends CmdbElementDto<I>,
        E extends CmdbElementDao<I>,
        I extends Serializable>
        extends BaseControllerIntegrationTest {


    private String allElementsEndpoint;
    private String elementEndpoint;

    private String elementHistoryEndpoint;

    private JpaElementReadOnlyRepository<E, I> repository;

    public void setup(
            String baseEndpoint,
            JpaElementReadOnlyRepository<E, I> repository
    ) {
        this.repository = repository;
        createEndpoints(baseEndpoint);
    }

    private void createEndpoints(String baseEndpoint) {
        allElementsEndpoint = baseEndpoint;
        elementEndpoint = allElementsEndpoint + "/{id}";
        elementHistoryEndpoint = elementEndpoint + "/history";
    }

    @Test
    public void getAllElementsIntegrationTest() {
        // given
        var call = webTestClient.get().uri(uriBuilder -> uriBuilder.path(allElementsEndpoint).build());

        // when
        var response = call.exchange();

        // then
        var responseBody = response.expectStatus().isOk()
                .expectBodyList(new ParameterizedTypeReference<D>() {
                })
                .returnResult().getResponseBody();
        // and
        Assertions.assertNotNull(responseBody);
        // and
        var activeDaoListId = repository.findAll().stream()
                .filter(CmdbElementDao::getActive)
                .map(CmdbElementDao::getId)
                .toList();
        var dtoListId = responseBody.stream()
                .map(CmdbElementDto::getId)
                .toList();
        Assertions.assertTrue(activeDaoListId.containsAll(dtoListId) && dtoListId.containsAll(activeDaoListId));

    }

    @MethodSource
    @ParameterizedTest
    public void getElementIntegrationTest(I id) {
        // given
        var call = webTestClient.get().uri(uriBuilder -> uriBuilder.path(elementEndpoint).build(id));

        // when
        var response = call.exchange();

        // then
        var responseBody = response.expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<D>() {
                })
                .returnResult().getResponseBody();
        // and
        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(id, responseBody.getId());
        // and
        Assertions.assertTrue(repository.findById(id).isPresent());
    }

    @MethodSource
    @ParameterizedTest
    public void getElementHistoryIntegrationTest(I id) {
        // given
        var call = webTestClient.get().uri(uriBuilder -> uriBuilder.path(elementHistoryEndpoint).build(id));

        // when
        var response = call.exchange();

        // then
        var responseBody = response.expectStatus().isOk()
                .expectBodyList(new ParameterizedTypeReference<D>() {
                })
                .returnResult().getResponseBody();
        // and
        Assertions.assertNotNull(responseBody);
        // and
        var revisionNumber = repository.findRevisions(id).toList().size();
        Assertions.assertEquals(revisionNumber, responseBody.size());
    }
}
