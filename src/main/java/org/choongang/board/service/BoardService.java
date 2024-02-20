package org.choongang.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.board.controllers.RequestBoardAdd;
import org.choongang.board.entities.Notice_;
import org.choongang.board.repositiries.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 필드 생성자 생성
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글(Notice, FaQ) 등록 서비스
     */

    public void save(RequestBoardAdd form) {

       Notice_ notice = new Notice_();

       notice.setType(form.getType());
       notice.setOnTop(form.isOnTop());
       notice.setTitle(form.getTitle());
       notice.setPostingType(form.getPostingType());
       notice.setQuestion(form.getQuestion());
       notice.setAnswer(form.getAnswer());
       notice.setContent(form.getContent());

       boardRepository.saveAndFlush(notice);
    }


    /**
     * 등록된 게시글 조회(정렬 기준 X, 등록 순)
     * @return
     */
    public List<Notice_> getList() {
        List<Notice_> noticeList = boardRepository.findAll();

        return noticeList;
    }

    /**
     * 노출 여부를 기준으로 게시물을 조회
     */
    public List<Notice_> getListOrderByOnTop() {
        return boardRepository.findByOrderByOnTopDesc();
    }

    /**
     * 게시글 번호로 상세 페이지 조회
     */

    public Optional<Notice_> findByNum(Long num) {
        return boardRepository.findById(num);
    }

}
