package org.choongang.board.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.board.entities.Qna;
import org.choongang.board.service.QnaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    /* QnA 게시글 등록 S */
    @GetMapping("/qna")
    public String qnaAdd(Model model) {
        model.addAttribute("RequestQnaAdd", new RequestQnaAdd());

        return "admin/board/qnaAdd";
    }

    @PostMapping("/qna")
    public String qnaList(RequestQnaAdd form, Model model) {

        qnaService.save(form);

        return "redirect:/board/qnaList";
    }
    /* QnA 게시글 등록 E */


    /* QnA 게시글 조회 S */
    @GetMapping("/qnaList")
    public String list(@ModelAttribute Qna form, Model model) {

        List<Qna> qnaList = qnaService.getList();
        model.addAttribute("qnaList", qnaList);

        return "admin/board/qnaList";
    }

    @PostMapping("/qnaList")
    public String toQnaList() {

        return "admin/board/qnaList";
    }

    /* QnA 게시글 조회 E */
}
