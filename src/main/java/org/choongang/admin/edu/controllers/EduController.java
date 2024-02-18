package org.choongang.admin.edu.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.edu.entities.EduData;
import org.choongang.admin.edu.service.EduContentDeleteService;
import org.choongang.admin.edu.service.EduDataInfoService;
import org.choongang.admin.edu.service.EduDataSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/edu")
@RequiredArgsConstructor
public class EduController {

    private final EduDataSaveService eduDataSaveService;
    private final EduDataInfoService eduDataInfoService;
    private final EduContentDeleteService eduContentDeleteService;

    /**
     * 학습 자료 등록
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add(@ModelAttribute RequestEduData form, Model model) {

        return "admin/edu/add";
    }

    @PostMapping("/add")
    public String addPs(@Valid RequestEduData form, Model model) {

        eduDataSaveService.save(form);

        return "redirect:/admin/edu/list";
    }

    /**
     * 학습 자료 수정
     * @param num
     * @param model
     * @return
     */
    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") Long num, Model model) {

        RequestEduData form = eduDataInfoService.getForm(num);
        model.addAttribute("requestEduData", form);

        return "admin/edu/edit";
    }

    @PostMapping("/edit/{num}")
    public String editPs(@PathVariable("num") Long num, Model model) {
        commonProcess("edit", model);

        return "admin/edu/edit";
    }

    @PostMapping("save")
    public String save(@Valid RequestEduData form, Errors errors, Model model) {
        String mode = form.getMode();
        commonProcess(mode, model);

        if(errors.hasErrors()) {
            return "admin/edu/" + mode;
        }

        eduDataSaveService.save(form);

        return "redirect:/admin/edu/list";
    }

    /**
     * 단일 삭제
     * @param num
     * @param model
     * @return
     */
    @GetMapping("/delete/{num}")
    public String delete(@PathVariable("num") Long num, Model model) {
        eduContentDeleteService.delete(num);

        return "redirect:/admin/edu/list";
    }

    /**
     * 학습 자료 조회
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@ModelAttribute EduData form, Model model) {

        List<EduData> dataList = eduDataInfoService.getList();
        model.addAttribute("dataList", dataList);

        return "admin/edu/list";
    }

    private void commonProcess(String mode, Model model) {
        mode = StringUtils.hasText(mode) ? mode : "add";

        List<String> addCommonScript = new ArrayList<>();
        List<String> addScript = new ArrayList<>();
        if (mode.equals("add") || mode.equals("edit")) {
            addCommonScript.add("fileManager");
            addScript.add("edu/form");
        }

        model.addAttribute("addCommonScript", addCommonScript);
        model.addAttribute("addScript", addScript);
    }

}
