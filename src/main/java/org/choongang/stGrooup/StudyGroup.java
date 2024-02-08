package org.choongang.stGrooup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class StudyGroup extends Base {


    @Id
    @GeneratedValue
    private Long num;    //기본키

    @Column(length = 80 , unique = true , nullable = false)
    private String name;     //스터디그룹명

    @Column(length = 80 , nullable = false)
    private String teacherName;      //선생님 이름

    @Column(length = 80 , nullable = false)
    private String teacherId;    //교육자 id

    @Column
    private LocalDateTime startDate; //시작일

    @Column
    private LocalDateTime endDate; //종료일

    @Column(length = 80 , nullable = false)
    private Long maxSubscriber;    //최대인원

    @Column(length = 80 , nullable = false)
    private Long subscriber;     //참여중인 인원

    @Column
    private Long maxLevel;   //달성 레벨

    @Column(length = 80 , nullable = false)
    private String text;     //비고



}
