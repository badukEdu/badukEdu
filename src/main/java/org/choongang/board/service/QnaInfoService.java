package org.choongang.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.board.entities.Qna;
import org.choongang.board.repositiries.QnaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaInfoService {

    private final QnaRepository qnaRepository;

    /**
     * 등록된 QnA 조회(등록 순)
     * @return
     */

    public List<Qna> getList() {
        List<Qna> qnaList = qnaRepository.findAll();

        return qnaList;
    }

    /**
     * 게시글 번호로 상세 페이지 조회
     * @param num
     * @return
     */

    public Optional<Qna> qnaFindByNum(Long num) { return qnaRepository.findById(num); }
}
