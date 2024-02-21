package org.choongang.admin.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.board.entities.Notice_;
import org.choongang.admin.board.repositiries.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardInfoService {

    private final BoardRepository boardRepository;

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
     * @return
     */

    public List<Notice_> getListOrderByOnTop() {
        return boardRepository.findByOrderByOnTopDesc();
    }

    /**
     * 게시글 번호로 상세 페이지 조회
     * @param num
     * @return
     */

    public Optional<Notice_> findByNum(Long num) {
        return boardRepository.findById(num);
    }

}
