package org.choongang.homework.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.homework.entities.Homework;
import org.choongang.homework.repositories.HomeworkRepository;
import org.choongang.homework.service.HomeworkInfoService;
import org.choongang.homework.service.HomeworkSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/homework")
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkSaveService homeworkSaveService;
    private final HomeworkInfoService homeworkInfoService;
    @GetMapping
    public String homework(@ModelAttribute Homework form, Model model) {
        // List 뽑아서 model에 담아서 이동하는 코드...
        // getList 설정추가필요
        List<Homework> items = homeworkInfoService.getList();
        model.addAttribute("items", items);


        return "front/teacher/homework/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute RequestHomework form, Model model) {
        // 등록 페이지


        return "front/teacher/homework/add";
    }
    @PostMapping("/add")
    public String addPs(@Valid RequestHomework form, Model model) {
        // 등록 버튼 후

        homeworkSaveService.save(form);


        return "redirect:/homework";
    }

    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") Long num, Model model) {

        RequestHomework form = homeworkInfoService.getForm(num);
        model.addAttribute("requestForm", form);

        return "front/teacher/homework/edit";
    }

    @PostMapping("/edit/{num}")
    public String editPs(@Valid RequestHomework form, @PathVariable("num") Long num, Model model) {
        form.setMode("edit");
        form.setNum(num);
        homeworkSaveService.save(form);

        return "redirect:/homework";
    }
}
