package org.choongang.admin.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.board.controllers.RequestQnaAdd;
import org.choongang.admin.board.entities.Qna;
import org.choongang.admin.board.repositiries.QnaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaSaveService {

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


}
