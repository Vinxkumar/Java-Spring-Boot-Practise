package com.example.springbootpractise.services.service;

import com.example.springbootpractise.dto.JournalDto;

import java.util.List;

public interface JournalService  {
    JournalDto createJournalEntry(JournalDto journalDto);
    JournalDto getJournalById(String id);
    List<JournalDto> listAllJournal();
    JournalDto updateJournal(String id, JournalDto journalDto);
    void deleteAllJournal();
    void deleteJournalById(String id);
}
