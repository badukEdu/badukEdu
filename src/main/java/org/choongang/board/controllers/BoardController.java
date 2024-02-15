package org.choongang.board.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.board.entities.Notice_;
import org.choongang.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // final 필드 생성자 생성
public class BoardController {

    private final BoardService boardService;

    /* 게시글 등록(공지사항, FAQ등 관리자 권한) S */
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("RequestBoardAdd", new RequestBoardAdd());

        return "admin/board/add";
    }

    @PostMapping("/add")
    public String BoardAdd(RequestBoardAdd form, Model model) {

        boardService.save(form);

        return "front/board/list";
    }
    /* 게시글 등록(공지사항, FAQ등 관리자 권한) S */


    /* 게시글 목록 S */
    @GetMapping("/list")
    public String list(@ModelAttribute Notice_ form, Model model) {

        List<Notice_> noticeList = boardService.getList();
        model.addAttribute("noticeList", noticeList);

        return "front/board/list";
    }

    @PostMapping("/list")
    public String boardList() {

        return "front/board/list";
    }
    /* 게시글 목록 E */


    /* QnA 게시글 등록 S */
    @GetMapping("/qna")
    public String qnaAdd() {

        return "admin/board/qnaAdd";
    }

    @PostMapping("/qna")
    public String qnaList() {

        return "admin/board/qnaList";
    }
    /* QnA 게시글 등록 E */
}
