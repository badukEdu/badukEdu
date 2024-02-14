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
    private boolean accept;
    @Column(updatable = false)
    private LocalDateTime JoinDate; // 가입 승인일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studyGroupNum")
    private StudyGroup studyGroup; //작성자 회원번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member member; //작성자 회원번호

}
