package org.choongang.stGrooup.controllers;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RequestStGroup {

    private Long num;    //기본키

    @NotBlank
    private String name;     //스터디그룹명

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate; //시작일

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; //종료일

    @NotBlank
    private Long maxSubscriber;    //최대인원

    @NotBlank
    private Long maxLevel;   //달성 레벨
    private String text;     //비고
    private String mode = "add";    //수정 , 생성
    private long gameContentNum;    //게임 컨텐츠 번호

    @NotBlank
    private long month;


    private String gameTitle;


}
