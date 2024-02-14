package org.choongang.admin.gamecontent.controllers;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class RequestGameContentData {
    private String gameTitle; // 게임콘텐츠명
    private Long totalGameLevels; // 총게임레벨
    private int subscriptionMonths; // 구독개월

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate; // //구독 시작일 (구독가능기간)

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate; //구독 종료일 (구독가능기간)

    private Long maxSubscriber; //최대인원(구독가능인원)
    private Long originalPrice; // 정가
    private float discountRate; // 할인율
    private int salePrice; // 판매가
    private String packageContents; // 패키지 내용
//    private String thumbnail;
}
