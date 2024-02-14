package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.choongang.commons.entities.Base;
import org.choongang.member.entities.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data // getter, setter, NoArgsConstructor 등 필요한 것만 구성 가능
public class Notice_ extends Base {

    @Id
    @GeneratedValue
    private Long num; // 공지사항(게시물) 번호
    @Column(nullable = false)
    private String type; // 공지사항 분류(공지사항, FAQ , QnA-> ENUM 클래스 사용?)
    @Column(nullable = false)
    private String cate; // 카테고리 분류( 공통, 상품안내, 이용안내, 상품결제, 숙제관리, 학습그룹, 기타 )
    @Column
    private boolean onTop; // 중요글 상단 노출(공지사항일때만 적용)
    @Column(nullable = false)
    private String title; // 게시글 제목
    @Lob // 가변 길이의 문자열을 저장, 길이 제한 X
    private String content; // 내용

    @Column
    private Long visit; //조회수
    @Column
    private String fileName; // 파일명 (파일명)
    @Column
    private String fileAddress; // 파일경로 (파일 경로)

    @Column(nullable = false)
    private boolean useReservation; // 예약게시 여부

    @Column(insertable = false)
    private LocalDateTime reservationDate; // 예약게시일 (useReservation true일 경우)


    @Column
    private String answer; // 답변(FaQ일 경우에만 사용)

////////////////////////////

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member member; //작성자 회원번호

    @OneToMany(mappedBy = "notice", fetch = FetchType.LAZY)
    private List<NoticeComent> noticeComments;




}