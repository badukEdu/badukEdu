package org.choongang.board.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/board")
@RequiredArgsConstructor // final 필드 생성자 생성
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/add")
    public String boardAdd() {

        return "board/add";
    }

    @PostMapping("/add")
    public String addToBoard() {

        return "board/add";
    }

    @GetMapping
    public String boardList() {

        return "board/list";
    }

}
