package com.example.springbootpractise.service;


import com.example.springbootpractise.entity.JournalEntryEntity;
import com.example.springbootpractise.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {
    @Autowired
    JournalRepository journalRepository;

    // Create
    public JournalEntryEntity CreateJournal(JournalEntryEntity journalEntryEntity) {
        JournalEntryEntity newentry = journalRepository.save(journalEntryEntity);
        return newentry;
    }
    // Retrive
    public JournalEntryEntity ListJournalById(String id) {
        JournalEntryEntity journal = journalRepository.findById(id).orElse(null);
        return journal;
    }

    public List<JournalEntryEntity> ListJournals() {
        return journalRepository.findAll();
    }
    // Update
    public String UpdateJournalById(JournalEntryEntity updateJournal) {
//        JournalEntryEntity updatedJournal = journalRepository.save(updateJournal)
        if(journalRepository.findById(updateJournal.getId()).isPresent()) {
            JournalEntryEntity oldJournal = journalRepository.findById(updateJournal.getId()).orElse(null);
            if(oldJournal != null) {
                if ((updateJournal.getContext().isEmpty()) || (updateJournal.getContext() == null)) {
                    updateJournal.setContext(oldJournal.getContext());
                } else {
                    updateJournal.setContext(updateJournal.getContext());
                }

                if((updateJournal.getText().isEmpty()) || (updateJournal.getText() == null)) {
                    updateJournal.setText(oldJournal.getText());
                } else {
                    updateJournal.setText(updateJournal.getText());
                }
            }
            journalRepository.save(updateJournal);
            return "Journal Updated Successfully";
        }
        return "Journal Failed to Update";
    }
    // Delete
    public String DeleteJournalById(String id) {
        if(journalRepository.findById(id).isPresent()) {
            journalRepository.deleteById(id);
            return "Journal Deleted fo id:" + id + " Successfully";
        }
        return "Journal Failed to Delete";
    }

    public String DeleteAllJournal() {
        journalRepository.deleteAll();
        return "Deleted all the Journals";
    }
}
