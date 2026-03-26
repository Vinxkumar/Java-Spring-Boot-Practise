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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class JournalSeriveImp implements JournalService {

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    JournalRepository journalRepository;

    @Transactional
    @Override
    public JournalDto createJournalEntry(JournalDto journalDto) {
            if(journalRepository.findById(journalDto.getId()).isPresent()) {
                throw new RuntimeException("Journal with ID: "+ journalDto.getId()+" already exists..!");
            }
            JournalEntryEntity newJournal = maptoEntity(journalDto);
            JournalEntryEntity journalEntry = journalRepository.save(newJournal);
            log.info("Journal Created with ID: "+ journalEntry.getId()+" successful");
            return maptoDto(journalEntry);
    }

    @Override
    public JournalDto getJournalById(String id) {
        JournalEntryEntity journalEntryEntity = journalRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not Found"));
        log.info("Retrieving Journal with ID: "+ id);
        return maptoDto(journalEntryEntity);
    }

    @Override
    public List<JournalDto> listAllJournal() {
        try {
            List<JournalEntryEntity> journalList = journalRepository.findAll();
            log.info("Fetching all Journal Records");
            return maptoDtoList(journalList);
        } catch (Exception e) {
            throw new RuntimeException("Failed To Fetch Data's");
        }
    }

    @Transactional
    @Override
    public JournalDto updateJournal(String id, JournalDto newJournalDto) {
        JournalEntryEntity oldJournal = journalRepository.findById(id).orElseThrow(() -> new RuntimeException("Failed to Find Journal with ID: "+ id));
        if(
                newJournalDto.getText() != null &&
                !(newJournalDto.getText().isEmpty()) &&
                !(newJournalDto.getText().equals(oldJournal.getText()))
        ) {
            oldJournal.setText(newJournalDto.getText());
        }
        if(
                newJournalDto.getContext() != null &&
                !(newJournalDto.getContext().isEmpty()) &&
                !(newJournalDto.getContext().equals(oldJournal.getContext()))
        ) {
            oldJournal.setContext(newJournalDto.getContext());
        }
        JournalEntryEntity updatedJournal = journalRepository.save(oldJournal);
        log.info("Updated Journal Successfully");
        return maptoDto(updatedJournal);
    }

    @Transactional
    @Override
    public void deleteAllJournal() {
        log.info("Deleted all records successfully");
        journalRepository.deleteAll();
    }

    @Transactional
    @Override
    public void deleteJournalById(String id) {
        if(journalRepository.findById(id).isPresent()) {
            journalRepository.deleteById(id);
            log.info("Deleted Journal With ID: "+ id);
        } else {
            throw new RuntimeException("Failed to Find Journal with ID: "+ id);
        }
    }


    //Mappers;

    private JournalDto maptoDto(JournalEntryEntity journalEntryEntity) {
        return mapper.map(journalEntryEntity, JournalDto.class);
    }

    private JournalEntryEntity maptoEntity(JournalDto journalDto) {
        return mapper.map(journalDto, JournalEntryEntity.class);
    }

    private List<JournalDto> maptoDtoList(List<JournalEntryEntity> journalEntryEntityList) {
        List<JournalDto> journalDtoList = new ArrayList<>();
        for(JournalEntryEntity journalEntryEntity: journalEntryEntityList) {
            journalDtoList.add(maptoDto(journalEntryEntity));
        }
        return journalDtoList;
    }

    private List<JournalEntryEntity> maptoEntityList(List<JournalDto> journalDtoList) {
        List<JournalEntryEntity> journalEntryEntityList = new ArrayList<>();
        for(JournalDto journalDto: journalDtoList) {
            journalEntryEntityList.add(maptoEntity(journalDto));
        }
        return journalEntryEntityList;
    }
}