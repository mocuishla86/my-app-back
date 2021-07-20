package org.llanesagudelo.myapp.weeklyEntry;

import org.llanesagudelo.myapp.weeklyEntry.WeeklyEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WeeklyEntryService {

    WeeklyEntryRepository weeklyEntryRepository;

    public WeeklyEntryService(WeeklyEntryRepository weeklyEntryRepository) {
        this.weeklyEntryRepository = weeklyEntryRepository;
    }

    public List<WeeklyEntry> getWeeklyEntries(){

        return weeklyEntryRepository.findAll();
    }

    public Optional<WeeklyEntry> getWeeklyEntryById(UUID id){
        return weeklyEntryRepository.findById(id);
    }

    public void save(WeeklyEntry weeklyEntry) {
        weeklyEntry.setId(UUID.randomUUID());
        weeklyEntryRepository.save(weeklyEntry);
    }
}
