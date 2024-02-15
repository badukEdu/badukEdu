package org.choongang.admin.gamecontent.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.gamecontent.entities.GameContent;
import org.choongang.admin.gamecontent.service.GameContentInfoService;
import org.choongang.admin.gamecontent.service.GameContentSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("AdminGameContentController")
@RequestMapping("/admin/gamecontent")
@RequiredArgsConstructor
public class GameContentController {

    private final GameContentSaveService gameContentSaveService;
    private final GameContentInfoService gameContentInfoService;

    /**
     * 게임 콘텐츠 등록
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add(@ModelAttribute GameContent form, Model model) {

        return "admin/gamecontent/add";
    }

    @PostMapping("/add")
    public String addPs(@Valid RequestGameContentData form, Model model) {

        gameContentSaveService.save(form);

        return "redirect:/admin/gamecontent/list";
    }

    /**
     * 게임 콘텐츠 조회
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@ModelAttribute GameContent form, Model model) {

        List<GameContent> dataList = gameContentInfoService.getList();
        model.addAttribute("dataList", dataList);

        return "admin/gamecontent/list";

    }


}
