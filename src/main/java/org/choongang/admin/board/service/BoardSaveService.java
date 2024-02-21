package org.choongang.admin.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.board.repositiries.BoardRepository;
import org.choongang.admin.board.controllers.RequestBoardAdd;
import org.choongang.admin.board.entities.Notice_;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor // final 필드 생성자 생성
public class BoardSaveService {

    private final BoardRepository boardRepository;

    /**
     * 게시글(Notice, FaQ) 등록, 수정 서비스
     */

    public void save(RequestBoardAdd form) {

       Long num = form.getNum();
       String mode = form.getMode();
       mode = StringUtils.hasText(mode) ? mode : "add";

       Notice_ notice;

       if (mode.equals("edit") && num != null) {
           // 수정 모드이고 게시물의 번호가 있을 때 이미 존재하는 게시글을 불러옴
           notice = boardRepository.findById(num).orElseThrow(BoardNotFoundException::new);
       } else {
           // 등록 모드일 때 새로운 게시글 생성
           notice = new Notice_();
       }

       notice.setType(form.getType());
       notice.setOnTop(form.isOnTop());
       notice.setTitle(form.getTitle());
       notice.setPostingType(form.getPostingType());
       notice.setQuestion(form.getQuestion());
       notice.setAnswer(form.getAnswer());
       notice.setContent(form.getContent());

       boardRepository.saveAndFlush(notice);
    }
}
