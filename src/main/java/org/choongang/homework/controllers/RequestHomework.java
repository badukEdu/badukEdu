package org.choongang.homework.controllers;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestHomework {
    private String name;
    private String content;
    private Long groupNum;
    private int studyLevel;
    private LocalDate deadLine;
}
