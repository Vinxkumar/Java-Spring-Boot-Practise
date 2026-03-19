package com.example.springbootpractise.controller;


import com.example.springbootpractise.entity.JournalEntryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    JournalService journalService;

    @GetMapping
    public List<JournalEntryEntity> ListAllJournals() {
        return journalService.ListJournals();
    }

    @GetMapping("/{id}")
    public JournalEntryEntity ListJournal(@PathVariable String id) {
        return journalService.ListJournalById(id);
    }

    @PostMapping
    public String CreateJournal(@RequestBody JournalEntryEntity journalEntryEntity) {
        if(journalService.ListJournalById(journalEntryEntity.getId()) == null) {
            journalService.CreateJournal(journalEntryEntity);
            return "User Created with id:" + journalEntryEntity.getId();
        }
        return "Journal with this id already exists";
    }

    @PutMapping
    public String UpdateJournal(@RequestBody JournalEntryEntity journalEntryEntity) {
        return journalService.UpdateJournalById(journalEntryEntity);
    }

    @DeleteMapping("/{id}")
    public String DeleteJournalById(@PathVariable String id) {
        return journalService.DeleteJournalById(id);
    }

    @DeleteMapping("/all")
    public String DeleteAllJournals() {
        return journalService.DeleteAllJournal();
    }
}
