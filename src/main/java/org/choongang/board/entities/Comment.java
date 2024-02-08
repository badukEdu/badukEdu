package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "NOTICE_COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq; // 댓글 번호

    @ManyToOne
    @JoinColumn(name = "notice_seq") // 공지사항 번호 (FK)
    private Notice noticeNum; // 댓글이 속한 공지사항

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 댓글 내용

    /*
    @ManyToOne
    @JoinColumn(name = "seq") // 작성자 (FK)
    private Member member;
    */

}