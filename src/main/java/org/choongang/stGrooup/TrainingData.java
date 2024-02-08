package org.choongang.stGrooup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TrainingData {
  @Id
  @GeneratedValue
  private Long num; // 자료 식별자, 자동 생성되는 고유한 번호 //pk
  @Column
  private String trainingDataName; // 학습자료명
  @Column
  private String dataType; // 자료구분
  @Column
  private String contentType; // 자료유형
  @Column
  private String fileAddress; // 자료file주소
  @Column
  private String serviceType; // 서비스구분
  @Column
  private String content; // 자료내용
  @Column
  private String thumbnail; // 썸네일
}