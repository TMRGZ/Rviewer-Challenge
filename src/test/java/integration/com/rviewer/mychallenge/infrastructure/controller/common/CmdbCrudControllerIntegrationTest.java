package integration.com.rviewer.mychallenge.infrastructure.controller.common;

import com.rviewer.mychallenge.application.dto.common.CmdbElementDto;
import com.rviewer.mychallenge.infrastructure.persistence.dao.common.CmdbElementDao;
import com.rviewer.mychallenge.infrastructure.persistence.repository.common.jpa.JpaElementCrudRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.Serializable;
import java.util.Optional;

public abstract class CmdbCrudControllerIntegrationTest<
        D extends CmdbElementDto<I>,
        E extends CmdbElementDao<I>,
        I extends Serializable> extends CmdbReadOnlyControllerIntegrationTest<D, E, I> {

    private String allElementsEndpoint;

    private String elementEndpoint;

    private JpaElementCrudRepository<E, I> repository;

    public void setup(
            String baseEndpoint,
            JpaElementCrudRepository<E, I> repository
    ) {
        super.setup(baseEndpoint, repository);
        this.repository = repository;
        createEndpoints(baseEndpoint);
    }

    private void createEndpoints(String baseEndpoint) {
        allElementsEndpoint = baseEndpoint;
        elementEndpoint = allElementsEndpoint + "/{id}";
    }

    @MethodSource
    @ParameterizedTest
    public void saveElementIntegrationTest(D elementToSave) {
        // given
        var call = webTestClient.post().uri(uriBuilder -> uriBuilder.path(allElementsEndpoint).build());

        // when
        var response = call.bodyValue(elementToSave)
                .exchange();

        // then
        var responseBody = (D) response.expectStatus().isCreated()
                .expectBody(elementToSave.getClass())
                .returnResult().getResponseBody();
        // and
        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        // and
        Assertions.assertTrue(repository.existsById(responseBody.getId()));
    }

    @MethodSource
    @ParameterizedTest
    public void deleteElementIntegrationTest(I id) {
        // given
        var call = webTestClient.delete().uri(uriBuilder -> uriBuilder.path(elementEndpoint).build(id));

        // when
        var response = call.exchange();

        // then
        response.expectStatus().isNoContent()
                .expectBody().isEmpty();
        // and
        Optional<E> byId = repository.findById(id);
        Assertions.assertTrue(byId.isPresent());
        Assertions.assertFalse(byId.get().getActive());
    }
}
