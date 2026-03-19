package com.example.springbootpractise.services;

import com.example.springbootpractise.dto.JournalDto;
import com.example.springbootpractise.entity.JournalEntryEntity;
import com.example.springbootpractise.repository.JournalRepository;
import com.example.springbootpractise.services.service.JournalService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class journalSeriveImp implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    @Transactional
    public JournalDto createJournalEntry(JournalDto journalDto) {
        log.info("Creating New Journal Entry with ID {}", journalDto.getId());
        if(journalRepository.findById(journalDto.getId()).isPresent()){
            throw new RuntimeException("Journal with ID: " + journalDto.getId()+", Already Exists....! ");
        }
        JournalEntryEntity journalEntry = mapToEntity(journalDto);
        JournalEntryEntity savedJournalEntry = journalRepository.save(journalEntry);

        log.info("Created Journal Entry with ID: {}", savedJournalEntry.getId());
        return mapToDto(savedJournalEntry);
    }

    @Override
    public JournalDto getJournalById(String id) {
        log.info("Fetching Journal with ID: {}", id);
        JournalEntryEntity journalEntryEntity = journalRepository.findById(id).orElseThrow(() -> new RuntimeException("Journal with ID: "+id+" Not Found....!") );
        return mapToDto(journalEntryEntity);
    }

    @Override
    public List<JournalDto> listAllJournal() {
        return List.of();
    }

    @Override
    @Transactional
    public JournalDto updateJournal(String id, JournalDto journalDto) {
        log.info("Trying to Update Journal with ID: {}", id);
        JournalEntryEntity existingJournalEntry = journalRepository.findById(id).orElseThrow(() -> new RuntimeException("Journal with ID: "+id+" Not Found....!"));
        if(!(existingJournalEntry.getText() == journalDto.getText())) {
            existingJournalEntry.setText(journalDto.getText());
        }
        if(!(existingJournalEntry.getContext() == journalDto.getContext())) {
            existingJournalEntry.setContext(journalDto.getContext());
        }

        JournalEntryEntity updatedJournal = journalRepository.save(existingJournalEntry);
        log.info("Updated Journal with ID: {}, Successfully", id);
        return mapToDto(updatedJournal);
    }

    @Override
    public void deleteAllJournal() {
        log.info("Attempted to Delete all Records.!");
        journalRepository.deleteAll();
        log.info("Deleted All Journals Successfully");
    }

    @Override
    public void deleteJournalById(String id) {
        log.info("Attempt to Delete Journal with ID: {}", id);
        journalRepository.deleteById(id);
        log.info("Deleted Journal with ID: {}, Successfully...!", id);
    }


    //mappers
    private JournalEntryEntity mapToEntity(JournalDto journalDto) {
        JournalEntryEntity journalEntryEntity = new ModelMapper().map(journalDto, JournalEntryEntity.class);
        return journalEntryEntity;
    }
    private JournalDto mapToDto(JournalEntryEntity journalEntryEntity) {
        JournalDto journalDto = new ModelMapper().map(journalEntryEntity, JournalDto.class);
        return journalDto;
    }


}




