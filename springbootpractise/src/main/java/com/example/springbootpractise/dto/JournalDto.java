package com.example.springbootpractise.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JournalDto {


    private String id;

    @NotBlank(message = "Context is needed...!")
    private String context;

    @NotBlank(message = "Context is needed...!")
    private String text;

}
