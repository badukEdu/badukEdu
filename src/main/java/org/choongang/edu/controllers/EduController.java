package org.choongang.edu.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.edu.entities.EduData;
import org.choongang.edu.service.EduDataInfoService;
import org.choongang.edu.service.EduDataSaveService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher/edu")
@RequiredArgsConstructor
public class EduController {

    private final EduDataSaveService eduDataSaveService;
    private final EduDataInfoService eduDataInfoService;

    /* 학습 자료 등록 */
    @GetMapping("/add")
    public String add(@ModelAttribute RequestEduData form, Model model) {

        return "front/teacher/edu/add";
    }

    @PostMapping("/add")
    public String addPs(@Valid RequestEduData form, Model model) {

        eduDataSaveService.save(form);

        return "redirect:/teacher/edu/list";
    }

    /* 학습 자료 조회 */
    @GetMapping("/list")
    public String list(@ModelAttribute EduData form, Model model) {
        System.out.println("///////111");
        List<EduData> dataList = eduDataInfoService.getList();
        System.out.println("//////" + dataList);
        model.addAttribute("dataList", dataList);

        return "front/teacher/edu/list";
    }

}
