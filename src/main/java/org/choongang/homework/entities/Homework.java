package org.choongang.homework.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Homework {
    @Id @GeneratedValue
    private Long num;

    @Column
    private String name;

    @Column
    private String content;

    @Column
    private String groupNum;

    @Column
    private String studyLevel;

    @Column
    private LocalDateTime deadLine;
}