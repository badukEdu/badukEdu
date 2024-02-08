package org.choongang.gameContent.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

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
    private int totalGameLevels; // 총게임레벨
    @Column
    private int subscriptionMonths; // 구독개월
    @Column
    private Date subscriptionPeriod; // 구독가능기간
    @Column
    private int originalPrice; // 정가
    @Column
    private float discountRate; // 할인율
    @Column
    private int salePrice; // 판매가
    @Column
    private String packageContents; // 패키지내용
    @Column
    private String thumbnail; // 썸네일
}
