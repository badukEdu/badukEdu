package org.choongang.board.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // final 필드 생성자 생성
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/add")
    public String add() {

        return "front/board/add";
    }

    @PostMapping("/add")
    public String BoardAdd() {

        return "front/board/list";
    }

    @GetMapping("/list")
    public String list() {

        return "front/board/list";
    }

    @PostMapping("/list")
    public String boardList() {

        return "front/board/list";
    }

}
