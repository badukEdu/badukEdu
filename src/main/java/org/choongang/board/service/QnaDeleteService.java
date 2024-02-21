package org.choongang.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.board.repositiries.QnaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaDeleteService {

    private final QnaRepository qnaRepository;

    public void deleteById(Long num) {
        qnaRepository.deleteById(num);
    }

}
