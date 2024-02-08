package org.choongang.gameContent.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class GameContent {
    @Id
    @GeneratedValue
    private Long num; // 게임 식별자, 자동 생성되는 고유한 번호 // pk
    @Column(length = 50, nullable = false)
    private String gameTitle; // 게임콘텐츠명
    @Column(nullable = false)
    private int totalGameLevels; // 총게임레벨
    @Column(nullable = false)
    private int subscriptionMonths; // 구독개월
    @Column(nullable = false)
    private Date subscriptionPeriod; // 구독가능기간
    @Column(nullable = false)
    private int originalPrice; // 정가
    @Column(nullable = false)
    private float discountRate; // 할인율
    @Column(nullable = false)
    private int salePrice; // 판매가
    @Lob
    private String packageContents; // 패키지내용
    @Column(nullable = false)
    private String thumbnail; // 썸네일
}
