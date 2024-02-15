package org.choongang.board.controllers;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class RequestBoardAdd {

    private String noticeCategory;

    private String faqCategory;

    private boolean onTop;

    private String noticeTitle;

    private String faqTitle;

    private String postingType;

    private String question;

    private String answer;

    @Lob
    private String content;
}
