package org.llanesagudelo.myapp.weeklyEntry;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeeklyEntryControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WeeklyEntryRepository repository;

    @BeforeEach
    public void cleanUp() {
        repository.deleteAllInBatch();
    }

    @Test
    public void atTheBeginningNoWeeklyEntriesShouldBePresent() {
        ResponseEntity<List<WeeklyEntry>> weeklyEntries = restTemplate.exchange(
                format("http://localhost:%d/entries", port),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<WeeklyEntry>>() {
                });

        assertThat(weeklyEntries.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(weeklyEntries.getBody()).hasSize(0);
    }

}