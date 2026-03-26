package com.example.springbootpractise.controller;

import com.example.springbootpractise.dto.JournalDto;

import com.example.springbootpractise.services.JournalSeriveImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    JournalSeriveImp journalSeriveImp;

    @GetMapping
    public ResponseEntity<List<JournalDto>> listAllJournals() {
        return new ResponseEntity<>(journalSeriveImp.listAllJournal(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<JournalDto> listJournal(@PathVariable String id) {
        return new ResponseEntity<>(journalSeriveImp.getJournalById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JournalDto> CreateJournal(@RequestBody JournalDto journalDto) {
        return new ResponseEntity<>(journalSeriveImp.createJournalEntry(journalDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<JournalDto> UpdateJournal(@RequestBody JournalDto journalDto) {
        return new ResponseEntity<>( journalSeriveImp.updateJournal(journalDto.getId(), journalDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus DeleteJournalById(@PathVariable String id) {
        journalSeriveImp.deleteJournalById(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/all")
    public HttpStatus DeleteAllJournals() {
        journalSeriveImp.deleteAllJournal();
        return HttpStatus.OK;
    }
}
