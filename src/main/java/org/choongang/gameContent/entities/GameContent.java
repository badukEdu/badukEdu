package org.choongang.gameContent.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class GameContent {
    @Id
    @GeneratedValue
    private Long num; // 게임 식별자, 자동 생성되는 고유한 번호 // pk
    @Column
    private String gameTitle; // 게임콘텐츠명
    @Column
    private Long totalGameLevels; // 총게임레벨
    @Column
    private LocalDateTime startDate; //구독 시작일 (구독가능기간)
    @Column
    private LocalDateTime endDate; //구독 종료일 (구독가능기간)
    @Column
    private int subscriptionMonths; // 구독개월
    @Column
    private Long maxSubscriber;    //최대인원
    @Column
    private Long originalPrice; // 정가
    @Column
    private int salePrice; // 판매가
    @Column
    private String packageContents; // 패키지내용
    @Column
    private String thumbnail; // 썸네일 (파일명)
    @Column
    private String path; // 썸네일 (파일명 경로)
    @Column
    private boolean use;  // 삭제여부 (사용중 1 / 삭제 0)
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime cDate; // 등록일 (자동생성)
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime mDate; // 수정일 (자동생성)
    @Column
    private float discountRate; // 할인율







}
