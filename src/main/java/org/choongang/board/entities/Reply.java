package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reply_table")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq")
    @SequenceGenerator(name = "reply_seq", sequenceName = "reply_seq", allocationSize = 1)
    private Long num; // 답변 번호

    /*
    @ManyToOne
    @JoinColumn(name = "seq", nullable = false)
    private Member member; // 답변자 (사용자 엔티티와 관계)
    */

    @ManyToOne
    @JoinColumn(name = "noticeSeq", nullable = false) // notice 엔티티의 seq를 사용하여 조인
    private Notice notice; // 문의 번호 (공지사항 엔티티와 관계)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 답변 내용

    // 추가적인 필드 및 매핑 설정은 필요에 따라 추가할 수 있습니다.
}