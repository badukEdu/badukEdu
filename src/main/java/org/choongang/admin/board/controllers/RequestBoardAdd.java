package org.choongang.admin.board.controllers;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestBoardAdd {

    private Long num;

    private String type; // 공지 분류(notice 또는 faq)
    
    private String mode = "edit"; // 등록 및 수정 분리

    private boolean onTop;

    private String title;

    private String postingType; // 게시 타입

//    @Column(insertable = false)
//    private LocalDateTime reservationDateTime; // 예약 게시 일시

    private String question;

    private String answer;

    @Lob
    private String content;

    public boolean isOnTop() {
        return onTop;
    }
}
