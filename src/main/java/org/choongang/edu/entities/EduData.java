package org.choongang.edu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class EduData {
    @Id
    private Long dataId; // 자료 식별자, 자동 생성되는 고유한 번호 //pk

    @Column(length = 50, nullable = false)
    private String trainingDataName; // 학습자료명

    private String dataType; // 자료구분

    private String contentType; // 자료유형

    private String fileAddress; // 자료file주소

    private String serviceType; // 서비스구분

    @Lob
    private String content; // 자료내용

    private String thumbnail; // 썸네일
}
