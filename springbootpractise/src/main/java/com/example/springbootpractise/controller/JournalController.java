package com.example.springbootpractise.controller;

import com.example.springbootpractise.dto.JournalDto;
import com.example.springbootpractise.entity.JournalEntryEntity;
import com.example.springbootpractise.services.JournalSeriveImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    JournalSeriveImp journalSeriveImp;

    @GetMapping
    public List<JournalEntryEntity> listAllJournals() {
        return journalSeriveImp.listAllJournal();
    }

    @GetMapping("/{id}")
    public JournalDto listJournal(@PathVariable String id) {
        return journalSeriveImp.getJournalById(id);
    }

    @PostMapping
    public JournalDto CreateJournal(@RequestBody JournalDto journalDto) {
        return journalSeriveImp.createJournalEntry(journalDto);
    }

    @PutMapping
    public JournalDto UpdateJournal(@RequestBody JournalDto journalDto) {
        return journalSeriveImp.updateJournal(journalDto.getId(), journalDto);
    }

    @DeleteMapping("/{id}")
    public void DeleteJournalById(@PathVariable String id) {
        journalSeriveImp.deleteJournalById(id);
    }

    @DeleteMapping("/all")
    public void DeleteAllJournals() {
        journalSeriveImp.deleteAllJournal();
    }
}
