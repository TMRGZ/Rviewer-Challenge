package integration.com.rviewer.mychallenge;

import com.rviewer.mychallenge.MyChallengeApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.util.Optional;

@SpringBootTest(classes = MyChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {

    private static RedisServer redisServer;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @BeforeAll
    static void setupEnvironment(@Value("${spring.data.redis.port}") Integer redisPort) throws IOException {
        redisServer = RedisServer.newRedisServer()
                .port(redisPort)
                .build();
        redisServer.start();
    }

    @AfterAll
    static void cleanupEnvironment() throws IOException {
        redisServer.stop();
    }

    @BeforeEach
    void setupRun() {
        Optional.ofNullable(redisTemplate.keys("*")).ifPresent(redisTemplate::delete);
    }

    @AfterEach
    void cleanupRun() {
        Optional.ofNullable(redisTemplate.keys("*")).ifPresent(redisTemplate::delete);
    }
}
