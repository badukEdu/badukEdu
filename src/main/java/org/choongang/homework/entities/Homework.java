package org.choongang.homework.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.choongang.member.entities.Member;
import org.choongang.stGrooup.StudyGroup;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime SDate; // 등록일 (자동생성)
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime mDate; // 수정일 (자동생성)
    ////////////////

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studyGroupNum")
    private StudyGroup studyGroup; //작성자 회원번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member member; //교육자 회원번호




}