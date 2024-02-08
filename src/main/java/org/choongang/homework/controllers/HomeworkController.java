package org.choongang.homework.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.homework.repositories.HomeworkRepository;
import org.choongang.homework.service.HomeworkSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/homework")
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkSaveService homeworkSaveService;
    @GetMapping
    public String homework() {
        // List 뽑아서 model에 담아서 이동하는 코드...

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

        return "front/teacher/homework/list";
    }
}
