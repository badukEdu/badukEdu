package org.choongang.admin.edu.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.edu.entities.EduData;
import org.choongang.admin.edu.service.EduDataInfoService;
import org.choongang.admin.edu.service.EduDataSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/edu")
@RequiredArgsConstructor
public class EduController {

    private final EduDataSaveService eduDataSaveService;
    private final EduDataInfoService eduDataInfoService;

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

}
