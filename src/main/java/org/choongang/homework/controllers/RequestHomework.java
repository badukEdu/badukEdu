package org.choongang.homework.controllers;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class RequestHomework {
    private String mode = "add";
    private Long num;

    @NotBlank
    private String name;
    @NotBlank @Lob
    private String content;

    private Long groupNum;

    @NotBlank
    private int studyLevel;
    @NotBlank
    private LocalDate deadLine;
}
