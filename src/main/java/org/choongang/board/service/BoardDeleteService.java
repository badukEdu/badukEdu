package org.choongang.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.board.repositiries.BoardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDeleteService {

    private final BoardRepository boardRepository;

    public void deleteById(Long num) {
        boardRepository.deleteById(num);
    }

}
