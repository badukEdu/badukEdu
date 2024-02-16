package org.choongang.admin.gamecontent.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.gamecontent.entities.GameContent;
import org.choongang.admin.gamecontent.service.GameContentDeleteService;
import org.choongang.admin.gamecontent.service.GameContentInfoService;
import org.choongang.admin.gamecontent.service.GameContentSaveService;
import org.choongang.commons.ListData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("AdminGameContentController")
@RequestMapping("/admin/gamecontent")
@RequiredArgsConstructor
public class GameContentController {

    private final GameContentSaveService gameContentSaveService;
    private final GameContentInfoService gameContentInfoService;
    private final GameContentDeleteService gameContentDeleteService;

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
     * @param search
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@ModelAttribute GameContentSearch search, Model model) {

        ListData<GameContent> data = gameContentInfoService.getList(search);
        model.addAttribute("items", data.getItems());
        model.addAttribute("pagination", data.getPagination());

        return "admin/gamecontent/list";
    }

    /**
     * 단일 삭제
     * @param num
     * @param model
     * @return
     */
    @GetMapping("/delete/{num}")
    public String delete(@PathVariable("num") Long num, Model model) {
        gameContentDeleteService.delete(num);

        return "redirect:/admin/gamecontent/list";
    }

    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") Long num, Model model) {

        RequestGameContentData requestGameContentData = gameContentInfoService.getForm(num);
        model.addAttribute("requestGameContentData", requestGameContentData);

        return "admin/gamecontent/edit";
    }

    @PostMapping("/edit/{num}")
    public String editPs(@Valid RequestGameContentData form,
                         @PathVariable("num") Long num,
                         Model model) {

        form.setMode("edit");
        form.setNum(num);
        gameContentSaveService.save(form);

        return "redirect:/admin/gamecontent/list";
    }


}
