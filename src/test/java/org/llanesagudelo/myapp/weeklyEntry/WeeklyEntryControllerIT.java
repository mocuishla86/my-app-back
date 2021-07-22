package org.llanesagudelo.myapp.weeklyEntry;

import com.fasterxml.jackson.core.type.TypeReference;
import liquibase.pro.packaged.W;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        ResponseEntity<List<WeeklyEntry>> weeklyEntries = getAllWeeklyEntries();

        assertThat(weeklyEntries.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(weeklyEntries.getBody()).hasSize(0);
    }

    @Test
    public void aUserShouldBeAbleToAddAnEntry(){
        WeeklyEntry weeklyEntry = new WeeklyEntry();
        weeklyEntry.setTitle("Title 1");
        weeklyEntry.setContent("Content 1");

        ResponseEntity<WeeklyEntry> postResponse = createWeeklyEntry(weeklyEntry);

        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(postResponse.getBody().getTitle()).isEqualTo("Title 1");
        assertThat(postResponse.getBody().getContent()).isEqualTo("Content 1");
        UUID weeklyEntryId = postResponse.getBody().getId();
        assertThat(weeklyEntryId).isNotNull();

        ResponseEntity<List<WeeklyEntry>> weeklyEntries = getAllWeeklyEntries();

        assertThat(weeklyEntries.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(weeklyEntries.getBody()).hasSize(1);

        ResponseEntity<WeeklyEntry> weeklyEntryById = getWeeklyEntryById(weeklyEntryId);

        assertThat(weeklyEntryById.getBody().getTitle()).isEqualTo("Title 1");
        assertThat(weeklyEntryById.getBody().getContent()).isEqualTo("Content 1");
        assertThat(weeklyEntryById.getBody().getId()).isEqualTo(weeklyEntryId);

    }

    @Test
    public void aUserShouldBeAbleToUpdateAnEntry(){
        WeeklyEntry weeklyEntry = new WeeklyEntry();
        weeklyEntry.setTitle("Title 1");
        weeklyEntry.setContent("Content 1");

        ResponseEntity<WeeklyEntry> postResponse = createWeeklyEntry(weeklyEntry);

        UUID weeklyEntryId = postResponse.getBody().getId();

        weeklyEntry.setTitle("Title 1 updated");
        weeklyEntry.setContent("Content 1 Updated");

        ResponseEntity<WeeklyEntry> updatedWeeklyEntry = updateWeeklyEntry(weeklyEntryId, weeklyEntry);
        assertThat(updatedWeeklyEntry.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<WeeklyEntry> weeklyEntryById = getWeeklyEntryById(weeklyEntryId);

        assertThat(weeklyEntryById.getBody().getTitle()).isEqualTo("Title 1 updated");
        assertThat(weeklyEntryById.getBody().getContent()).isEqualTo("Content 1 Updated");
    }

    private ResponseEntity<WeeklyEntry> getWeeklyEntryById(UUID weeklyEntryId) {
        return restTemplate.exchange(
                    format("http://localhost:%d/entries/%s", port, weeklyEntryId),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<WeeklyEntry>() {
                    });
    }

    private ResponseEntity<WeeklyEntry> createWeeklyEntry(WeeklyEntry weeklyEntry) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WeeklyEntry> requestEntity = new HttpEntity<>(weeklyEntry, requestHeaders);
        ResponseEntity<WeeklyEntry> postResponse = restTemplate.exchange(
                format("http://localhost:%d/entries", port),
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<WeeklyEntry>() {
                }
        );
        return postResponse;
    }

    private ResponseEntity<WeeklyEntry> updateWeeklyEntry(UUID weeklyEntryId, WeeklyEntry weeklyEntry){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WeeklyEntry> requestEntity = new HttpEntity<>(weeklyEntry, requestHeaders);
        ResponseEntity<WeeklyEntry> updateResponse = restTemplate.exchange(
                format("http://localhost:%d/entries/%s", port, weeklyEntryId),
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<WeeklyEntry>() {
                }
        );
        return updateResponse;

    }



    private ResponseEntity<List<WeeklyEntry>> getAllWeeklyEntries() {
        return restTemplate.exchange(
                format("http://localhost:%d/entries", port),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<WeeklyEntry>>() {
                });
    }

}