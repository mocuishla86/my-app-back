package org.llanesagudelo.myapp.weeklyEntry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/entries")
public class WeeklyEntryController {
    private WeeklyEntryService weeklyEntryService;

    public WeeklyEntryController(WeeklyEntryService weeklyEntryService){
        this.weeklyEntryService = weeklyEntryService;
    }
    @GetMapping()
    public ResponseEntity<List<WeeklyEntry>> getWeeklyEntries(){
        return ResponseEntity.ok(weeklyEntryService.getWeeklyEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<WeeklyEntry>> getWeeklyEntryById(@PathVariable UUID id){
        Optional<WeeklyEntry> weeklyEntry = weeklyEntryService.getWeeklyEntryById(id);

        if(weeklyEntry.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(weeklyEntry);
    }

    @PostMapping
    public ResponseEntity<Void> createWeeklyEntry(@RequestBody WeeklyEntry weeklyEntry){
        weeklyEntryService.save(weeklyEntry);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
