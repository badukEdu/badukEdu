package org.choongang.edu.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.choongang.member.entities.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class EduData {
    @Id
    @GeneratedValue
    private Long num; // 자료 식별자, 자동 생성되는 고유한 번호 //pk
    @Column
    private String name; // 학습자료명
    @Column
    private boolean dataType; // 자료구분 (튜토리얼 0 / 교육영상 1)

    @Column
    private String contentType; // 자료유형 (동영상 / 교재 / 웹사이트)
    @Column
    private boolean serviceType; // 서비스구분 (무료 0 / 유료 1)
    @Column
    private String content; // 자료내용
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime cDate; // 등록일 (자동생성)
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime mDate; // 수정일 (자동생성)
    @Column
    private String fileName; // 자료file명
    @Column
    private String fileAddress; // 자료file주소
    @Column
    private String thumbnail; // 썸네일 (파일명)
    @Column
    private String thumbnailAddress; // 썸네일 (파일명 경로)

    //////////////////////


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member member; //작성자 회원번호



}
