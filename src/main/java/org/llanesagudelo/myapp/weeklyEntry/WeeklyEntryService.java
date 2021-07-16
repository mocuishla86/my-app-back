package org.llanesagudelo.myapp.weeklyEntry;

import org.llanesagudelo.myapp.weeklyEntry.WeeklyEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeeklyEntryService {

    WeeklyEntryRepository weeklyEntryRepository;

    public WeeklyEntryService(WeeklyEntryRepository weeklyEntryRepository) {
        this.weeklyEntryRepository = weeklyEntryRepository;
    }

    public List<WeeklyEntry> getWeeklyEntries(){

        return weeklyEntryRepository.findAll();
    }

    public void save(WeeklyEntry weeklyEntry) {
    }
}
