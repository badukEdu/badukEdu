package org.choongang.admin.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.board.controllers.RequestQnaAdd;
import org.choongang.admin.board.entities.Qna;
import org.choongang.admin.board.repositiries.QnaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class QnaSaveService {

    private final QnaRepository qnaRepository;

    /**
     * QnA 등록, 수정 서비스
     */

    public void save(RequestQnaAdd form) {

        Long num = form.getNum();
        String mode = form.getMode();
        mode = StringUtils.hasText(mode) ? mode : "add";

        Qna qna = new Qna();

        if (mode.equals("edit") && num != null) {
            // 수정 모드이고 게시물의 번호가 있을 때 이미 존재하는 게시글을 불러옴
            qna = qnaRepository.findById(num).orElseThrow(QnaNotFoundException::new);
        } else {
            // 등록 모드일 때 새로운 게시글 생성
            qna = new Qna();
        }

        qna.setType(form.getType());
        qna.setTitle(form.getTitle());
        qna.setAnswerAlert(form.getAnswerAlert());
        qna.setContent(form.getContent());
        qna.setFileName(form.getFileName());
        qna.setFileAddress(form.getFileAddress());

        qnaRepository.saveAndFlush(qna);
    }


}
