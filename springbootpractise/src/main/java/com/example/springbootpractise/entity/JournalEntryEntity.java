package com.example.springbootpractise.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Table(name="journalEntries")
@Data
public class JournalEntryEntity {

    @Id
    private String id;

    @Column
    private String context;

    @Column
    private String text;

}
