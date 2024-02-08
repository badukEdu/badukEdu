package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data // getter, setter, NoArgsConstructor 등 필요한 것만 구성 가능
@Table(name = "notice_table")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_seq") // 공지사항 시퀀스 생성
    @SequenceGenerator(name = "notice_seq", sequenceName = "notice_seq", allocationSize = 1) // 1부터 순차적으로 생성
    private Long seq; // 공지사항(게시물) 번호

    @Column(nullable = false)
    private String type; // 공지사항 분류(공지사항, FAQ -> ENUM 클래스 사용?)

    @Column
    private boolean onTop; // 중요글 상단 노출(공지사항일때만 적용)

    @Column(nullable = false)
    private String title; // 게시글 제목

    @Column(nullable = false)
    private String cate; // 카테고리 분류( 공통, 상품안내, 이용안내, 상품결제, 숙제관리, 학습그룹, 기타 )

    @Column(nullable = false)
    private LocalDateTime reservationDate; // 게시일자(등록 즉시 or 개시 예정일 지정)

    @Column(nullable = false)
    private boolean useReservation; // 예약게시 여부

    @Column(nullable = false, columnDefinition = "TEXT") // 가변 길이의 문자열을 저장, 길이 제한 X
    private String content; // 내용

    @Column(nullable = false)
    private String question; // 질의

    @Column
    private String answer; // 답변

    /*
    @Column
    private String attachment; // 첨부 파일
    */

    /*
    @ManyToOne
    @JoinColumn(name = "gid")
    private FileGroup fileGroup; // 파일 그룹 아이디 (FK)
    */

}