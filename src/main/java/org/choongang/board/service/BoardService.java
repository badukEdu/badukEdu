package org.choongang.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.board.repositiries.BoardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final 필드 생성자 생성
public class BoardService {

    private final BoardRepository boardRepository;
}
