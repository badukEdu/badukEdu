package org.choongang.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.board.controllers.RequestBoardAdd;
import org.choongang.board.entities.Notice_;
import org.choongang.board.repositiries.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // final 필드 생성자 생성
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 등록 서비스
     */

    public void save(RequestBoardAdd form) {

       Notice_ notice = new Notice_();

       notice.setNoticeCategory(form.getNoticeCategory());
       notice.setFaqCategory(form.getFaqCategory());
       notice.setOnTop(form.isOnTop());
       notice.setNoticeTitle(form.getNoticeTitle());
       notice.setFaqTitle(form.getFaqTitle());
       notice.setPostingType(form.getPostingType());
       notice.setAnswer(form.getAnswer());
       notice.setContent(form.getContent());

       boardRepository.saveAndFlush(notice);
    }


    /**
     * 등록된 게시글 조회
     * @return
     */
    public List<Notice_> getList() {
        List<Notice_> noticeList = boardRepository.findAll();

        return noticeList;
    }
}
