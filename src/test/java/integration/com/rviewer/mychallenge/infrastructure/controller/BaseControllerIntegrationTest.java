package integration.com.rviewer.mychallenge.infrastructure.controller;

import integration.com.rviewer.mychallenge.BaseIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
public abstract class BaseControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    protected WebTestClient webTestClient;
}
