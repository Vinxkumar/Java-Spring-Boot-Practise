package com.example.springbootpractise.services.service;

import com.example.springbootpractise.dto.JournalDto;
import com.example.springbootpractise.entity.JournalEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalService  {
    JournalDto createJournalEntry(JournalDto journalDto);
    JournalDto getJournalById(String id);
    List<JournalEntryEntity> listAllJournal();
    JournalDto updateJournal(String id, JournalDto journalDto);
    void deleteAllJournal();
    void deleteJournalById(String id);
}
