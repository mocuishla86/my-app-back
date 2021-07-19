package org.llanesagudelo.myapp.weeklyEntry;

import org.llanesagudelo.myapp.weeklyEntry.WeeklyEntry;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public WeeklyEntry getWeeklyEntryById(UUID id){
        return weeklyEntryRepository.findById(id).get();
    }

    public void save(WeeklyEntry weeklyEntry) {
    }
}
