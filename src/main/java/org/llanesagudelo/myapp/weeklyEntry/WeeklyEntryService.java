package org.llanesagudelo.myapp.weeklyEntry;

import org.llanesagudelo.myapp.weeklyEntry.WeeklyEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeeklyEntryService {

    public List<WeeklyEntry> getWeeklyEntries(){
        return List.of(new WeeklyEntry("My First Month", "...bla,bla"));
    }

    public void save(WeeklyEntry weeklyEntry) {
    }
}
