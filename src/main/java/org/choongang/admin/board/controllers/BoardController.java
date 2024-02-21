package org.choongang.admin.board.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.board.entities.Notice_;
import org.choongang.admin.board.service.BoardDeleteService;
import org.choongang.admin.board.service.BoardInfoService;
import org.choongang.admin.board.service.BoardSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/board")
@RequiredArgsConstructor // final 필드 생성자 생성
public class BoardController {

    private final BoardSaveService boardSaveService;
    private final BoardInfoService boardInfoService;
    private final BoardDeleteService boardDeleteService;

    /* 게시글 등록(공지사항, FAQ등 관리자 권한) S */
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("RequestBoardAdd", new RequestBoardAdd());

        return "admin/board/add";
    }

    @PostMapping("/add")
    public String BoardAdd(RequestBoardAdd form, Model model) {

        boardSaveService.save(form);

        return "redirect:/board/list";
    }
    /* 게시글 등록(공지사항, FAQ등 관리자 권한) S */


    /* 게시글 목록 조회 S */
    @GetMapping("/list")
    public String list(@ModelAttribute Notice_ form, Model model) {

        List<Notice_> noticeList = boardInfoService.getListOrderByOnTop();
        model.addAttribute("noticeList", noticeList);

        return "front/board/list";
    }

    @PostMapping("/list")
    public String boardList() {

        return "front/board/list";
    }
    /* 게시글 목록 조회 E */

    /* 공지사항 및 FAQ 게시글 상세 조회 S */

    @GetMapping("/detail/{num}")
    public String detail(@PathVariable Long num, Model model) {

        // 경로 변수 num이 null이거나 음수인 경우에는 front/board/list로 리다이렉션
        if (num <= 0) {
            return "redirect:/front/board/list";
        }

        // 게시글 번호를 사용하여 해당 게시글 정보를 가져온다.
        Optional<Notice_> noticeDetail = boardInfoService.findByNum(num);

        // 게시글이 존재하는 경우에는 모델에 추가하고 admin/board/noitceDetail 페이지를 반환
        if (noticeDetail.isPresent()) {
            model.addAttribute("noticeDetail", noticeDetail.get());
            model.addAttribute("requestBoardAdd", new RequestBoardAdd());
            return "admin/board/noticeDetail";
        }

        // 해당 게시글을 찾을 수 없는 경우에는 front/board/list로 리다이렉션
        return "redirect:/front/board/list";
        }

    /* 공지사항 및 FAQ 게시글 상세 조회 E */

    /* 공지사항 및 FAQ 게시글 수정 S */

    @GetMapping("/edit/{num}")
    public String editForm(@PathVariable Long num, Model model) {

        // 게시글 번호를 사용하여 해당 게시글 정보를 가져온다.
        Optional<Notice_> noticeDetail = boardInfoService.findByNum(num);

        // 게시글이 존재하는 경우에는 모델에 추가하고 admin/board/noticeEdit 페이지를 반환
        if (noticeDetail.isPresent()) {
            model.addAttribute("requestBoardAdd", noticeDetail.get());
            return "admin/board/noticeEdit";
        }

        // 해당 게시글을 찾을 수 없는 경우에는 front/board/list로 리다이렉션
        return "redirect:/front/board/list";
    }

    @PostMapping("/edit")
    public String editBoard(RequestBoardAdd form, Model model) {
        // 기존 게시물 정보 가져오기
        Optional<Notice_> existingNotice = boardInfoService.findByNum(form.getNum());

        // 기존 게시물이 존재하는 경우에만 수정
        if (existingNotice.isPresent()) {
            Notice_ notice = existingNotice.get();
            // 기존 게시물 내용 수정
            notice.setTitle(form.getTitle());
            notice.setPostingType(form.getPostingType());
            notice.setQuestion(form.getQuestion());
            notice.setAnswer(form.getAnswer());
            notice.setContent(form.getContent());
            // 수정된 게시물 저장
            boardSaveService.save(form);
        }

        return "redirect:/board/list";
    }

    /* 공지사항 및 FAQ 게시글 수정 E */

    /* 공지사항 및 FAQ 게시글 삭제 S */

    @GetMapping("/delete/{num}")
    public String deleteBoard(@PathVariable Long num) {
        boardDeleteService.deleteById(num);
        return "front/board/list";
    }
    /* 공지사항 및 FAQ 게시글 삭제 E */
}

