package org.choongang.gameContent.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.choongang.commons.entities.Base;
import org.choongang.member.entities.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class GameContent extends Base {
    @Id
    @GeneratedValue
    private Long num; // 게임 식별자, 자동 생성되는 고유한 번호 // pk
    @Column
    private String gameTitle; // 게임콘텐츠명
    @Column
    private Long totalGameLevels; // 총게임레벨
    @Column
    private LocalDate startDate; //구독 시작일 (구독가능기간)
    @Column
    private LocalDate endDate; //구독 종료일 (구독가능기간)
    @Column
    private int subscriptionMonths; // 구독개월
    @Column
    private Long maxSubscriber; //최대인원(구독가능인원)
    @Column
    private Long originalPrice; // 정가
    @Column
    private int salePrice; // 판매가
    @Column
    private String packageContents; // 패키지내용
    @Column
    private String thumbnail; // 썸네일 (파일명)
    @Column
    private String fileAddress; // 썸네일 (파일명 경로)
    @Column
    private boolean use;  // 삭제여부 (사용중 1 / 삭제 0)

    @Column
    private float discountRate; // 할인율

///////////////////////


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "memberNum")
private Member member; //작성자 회원번호





}
