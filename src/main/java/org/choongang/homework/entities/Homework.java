package org.choongang.homework.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.choongang.member.entities.Member;
import org.choongang.stGrooup.StudyGroup;
import org.choongang.stGrooup.TrainingData;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Homework {
    @Id @GeneratedValue
    private Long num; // 숙제번호

    @Column
    private String name; // 숙제명

    @Column
    private String content; // 숙제내용

    @Column
    private int studyLevel; // 숙제레벨

    @Column
    private LocalDate deadLine; // 제출기한

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

    @OneToMany(mappedBy = "homework", fetch = FetchType.LAZY)
    private List<TrainingData> trainingDatas;



}