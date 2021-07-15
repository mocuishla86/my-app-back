package org.llanesagudelo.myapp.weeklyEntry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WeeklyEntryRepositoryIT {

    @Autowired
    private WeeklyEntryRepository weeklyEntryRepository;

    @Test
    public void shouldGetAllWeeklyEntries() {
        List<WeeklyEntry> entries = weeklyEntryRepository.findAll();

        assertThat(entries).hasSize(1);
    }

}