package org.llanesagudelo.myapp;

import org.springframework.stereotype.Component;
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
