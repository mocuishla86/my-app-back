package org.llanesagudelo.myapp.Controller;

import org.llanesagudelo.myapp.WeeklyEntry;
import org.llanesagudelo.myapp.WeeklyEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RestController
@RequestMapping("/entries")
public class MainController {
    private WeeklyEntryService weeklyEntryService;

    public MainController(WeeklyEntryService weeklyEntryService){
        this.weeklyEntryService = weeklyEntryService;
    }
    @GetMapping()
    public ResponseEntity<List<WeeklyEntry>> getWeeklyEntries(){
        return ResponseEntity.ok(weeklyEntryService.getWeeklyEntries());
    }
}
