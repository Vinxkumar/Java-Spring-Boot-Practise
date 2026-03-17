package com.example.springbootpractise.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name="journalEntries")
public class JournalEntryEntity {

    @Id
    private String id;

    @Column
    private String context;

    @Column
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }



}
