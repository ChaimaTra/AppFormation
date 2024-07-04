
package com.example.gdechetslabibup.controller;

import com.example.gdechetslabibup.dto.CollectionScheduleDto;
import com.example.gdechetslabibup.model.CollectionSchedule;
import com.example.gdechetslabibup.service.CollectionScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/collectionschedules")
@PreAuthorize("hasAnyRole('ADMIN','USER')")

public class CollectionScheduleController {

    private final CollectionScheduleService scheduleService;

    @Autowired
    public CollectionScheduleController(CollectionScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('ADMIN')")

    public ResponseEntity<CollectionSchedule> createCollectionSchedule(@RequestBody CollectionScheduleDto scheduleDto) {
        CollectionSchedule newSchedule = scheduleService.createCollectionSchedule(scheduleDto);
        return ResponseEntity.ok(newSchedule);
    }

    @GetMapping("/quartier/{quartier}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<List<CollectionSchedule>> getCollectionSchedulesByQuartier(@PathVariable String quartier) {
        List<CollectionSchedule> schedules = scheduleService.findByQuartier(quartier);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/date/{date}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<List<CollectionSchedule>> getCollectionSchedulesByDate(@PathVariable String date) {
        List<CollectionSchedule> schedules = scheduleService.findByDateDeCollect(LocalDate.parse(date));
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/quartier/{quartier}/date/{date}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<Optional<CollectionSchedule>> getCollectionSchedulesByQuartierAndDate(@PathVariable String quartier, @PathVariable String date) {
        Optional<CollectionSchedule> schedules = scheduleService.findByQuartierAndDateDeCollect(quartier, LocalDate.parse(date));
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<List<CollectionSchedule>> getAllCollectionSchedules() {
        List<CollectionSchedule> schedules = scheduleService.getAll();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and hasAuthority('READ_PRIVILEGE')")

    public ResponseEntity<CollectionSchedule> getCollectionScheduleById(@PathVariable Long id) {
        Optional<CollectionSchedule> schedule = scheduleService.getById(id);
        return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE') and hasRole('ADMIN')")

    public ResponseEntity<CollectionSchedule> updateCollectionSchedule(@PathVariable Long id, @RequestBody CollectionScheduleDto scheduleDto) {
        CollectionSchedule updatedSchedule = scheduleService.updateCollectionSchedule(id, scheduleDto);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE') and hasRole('ADMIN')")

    public ResponseEntity<Void> deleteCollectionSchedule(@PathVariable Long id) {
        scheduleService.deleteCollectionSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
