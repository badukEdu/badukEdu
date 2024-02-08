package org.choongang.homework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Homework {
    @Id @GeneratedValue
    private Long seq;

    private String name;

    private String content;

    private String groupNum;

    private String studyLevel;

    private LocalDateTime deadLine;
}