package org.choongang.admin.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.board.repositiries.QnaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaDeleteService {

    private final QnaRepository qnaRepository;

    public void deleteById(Long num) {
        qnaRepository.deleteById(num);
    }

}
