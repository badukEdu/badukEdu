package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.choongang.member.entities.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class NoticeComent {

    @Id
    @GeneratedValue
    private Long num; // 댓글 번호

    @ManyToOne
    @JoinColumn(name = "notice_Num") // 공지사항 번호 (FK)
    private Notice_ notice; // 댓글이 속한 공지사항

    @Lob
    private String content; // 댓글 내용

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime SDate; // 작성일 (자동생성)
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime mDate; // 수정일 (자동생성)

    ///////////////////////////


    @ManyToOne
    @JoinColumn(name = "memberNum") // 작성자 (FK)
    private Member member;



}