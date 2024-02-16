package org.choongang.stGrooup.entities;

import jakarta.persistence.*;
import org.choongang.member.entities.Member;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class JoinStudyGroup {

    @Id
    @GeneratedValue
    private Long num;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime SDate; // 신청일 (자동생성)
    @Column
    private boolean accept = false; // 가입승인여부
    @Column(updatable = false)
    private LocalDateTime JoinDate; // 가입 승인일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studyGroupNum")
    private StudyGroup studyGroup; // 스터디그룹

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member member; //구독회원 회원번호

}
