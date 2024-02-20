package org.choongang.board.controllers;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RequestQnaAdd {

    private Long num;

    private String type;

    private String mode = "add"; // 등록 및 수정 분리

    private String title;

    private String answerAlert;

    private String content;

    @Column
    private String fileName; // 파일명 (파일명)

    @Column
    private String fileAddress; // 파일경로 (파일 경로)
}
