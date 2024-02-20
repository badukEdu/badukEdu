package org.choongang.board.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.board.entities.Notice_;
import org.choongang.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

        return "redirect:/board/list";
    }
    /* 게시글 등록(공지사항, FAQ등 관리자 권한) S */


    /* 게시글 목록 S */
    @GetMapping("/list")
    public String list(@ModelAttribute Notice_ form, Model model) {

        List<Notice_> noticeList = boardService.getListOrderByOnTop();
        model.addAttribute("noticeList", noticeList);

        return "front/board/list";
    }

    @PostMapping("/list")
    public String boardList() {

        return "front/board/list";
    }
    /* 게시글 목록 E */

    /* 게시글 상세 조회 S */

    @GetMapping("/detail/{num}")
    public String detail(@PathVariable Long num, Model model) {

        // 경로 변수 num이 null이거나 음수인 경우에는 front/board/list로 리다이렉션
        if (num <= 0) {
            return "redirect:/front/board/list";
        }

        // 게시글 번호를 사용하여 해당 게시글 정보를 가져온다.
        Optional<Notice_> noticeDetail = boardService.findByNum(num);

        // 게시글이 존재하는 경우에는 모델에 추가하고 admin/board/detail 페이지를 반환
        if (noticeDetail.isPresent()) {
            model.addAttribute("noticeDetail", noticeDetail.get());
            return "admin/board/detail";
        }

        // 해당 게시글을 찾을 수 없는 경우에는 front/board/list로 리다이렉션
        return "redirect:/front/board/list";
        }
    /* 게시글 상세 조회 E */

}

