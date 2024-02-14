package org.choongang.gameContent.controllers;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RequestGameContentData {
    private String gameTitle;
    private Long totalGameLevels;
    private int subscriptionMonths;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    private Long maxSubscriber;
    private float discountRate;
    private int salePrice;
    private String packageContents;
//    private String thumbnail;
}
