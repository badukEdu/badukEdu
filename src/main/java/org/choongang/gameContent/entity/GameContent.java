package org.choongang.gameContent.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class GameContent {
    @Id
    private Long gameID; // 게임 식별자, 자동 생성되는 고유한 번호 // pk
    private String gameTitle; // 게임콘텐츠명
    private int totalGameLevels; // 총게임레벨
    private int subscriptionMonths; // 구독개월
    private Date subscriptionPeriod; // 구독가능기간
    private int originalPrice; // 정가
    private float discountRate; // 할인율
    private int salePrice; // 판매가
    private String packageContents; // 패키지내용
    private String thumbnail; // 썸네일
}
