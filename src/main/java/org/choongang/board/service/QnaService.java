package org.choongang.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.board.controllers.RequestQnaAdd;
import org.choongang.board.entities.Qna;
import org.choongang.board.repositiries.QnaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    /**
     * QnA 등록 서비스
     */

    public void save(RequestQnaAdd form) {

        Qna qna = new Qna();

        qna.setType(form.getType());
        qna.setTitle(form.getTitle());
        qna.setAnswerAlert(form.getAnswerAlert());
        qna.setContent(form.getContent());
        qna.setFileName(form.getFileName());
        qna.setFileAddress(form.getFileAddress());

        qnaRepository.saveAndFlush(qna);
    }

    /**
     * 등록된 QnA 조회(등록 순)
     */

    public List<Qna> getList() {
        List<Qna> qnaList = qnaRepository.findAll();

        return qnaList;
    }
}
