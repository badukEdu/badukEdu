package org.choongang.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.board.entities.Qna;
import org.choongang.board.repositiries.QnaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaInfoService {

    private final QnaRepository qnaRepository;

    /**
     * 등록된 QnA 조회(등록 순)
     */

    public List<Qna> getList() {
        List<Qna> qnaList = qnaRepository.findAll();

        return qnaList;
    }
}
