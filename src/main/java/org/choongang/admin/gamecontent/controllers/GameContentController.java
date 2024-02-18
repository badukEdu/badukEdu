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
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public String add(@ModelAttribute RequestGameContentData form, Model model) {
        commonProcess("add", model);

        return "admin/gamecontent/add";
    }

    /**
     * 게임 컨텐츠 수정
     * @param num
     * @param model
     * @return
     */
    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") Long num, Model model) {

        RequestGameContentData form = gameContentInfoService.getForm(num);
        model.addAttribute("requestGameContentData", form);

        return "admin/gamecontent/edit";
    }

    @PostMapping("/edit/{num}")
    public String editPs(@PathVariable("num") Long num, Model model) {
        commonProcess("edit", model);

        return "admin/gamecontent/edit";
    }

    @PostMapping("/save")
    public String save(@Valid RequestGameContentData form, Errors errors, Model model) {
        String mode = form.getMode();
        commonProcess(mode, model);

        if(errors.hasErrors()) {
            return "admin/gamecontent/" + mode;
        }

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

    private void commonProcess(String mode, Model model) {
        mode = StringUtils.hasText(mode) ? mode : "add";

        List<String> addCommonScript = new ArrayList<>();
        List<String> addScript = new ArrayList<>();
        if (mode.equals("add") || mode.equals("edit")) {
            addCommonScript.add("fileManager");
            addScript.add("gamecontent/form");
        }

        model.addAttribute("addCommonScript", addCommonScript);
        model.addAttribute("addScript", addScript);
    }

    /*
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

    */
}
