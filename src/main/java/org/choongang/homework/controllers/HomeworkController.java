package org.choongang.homework.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.homework.entities.Homework;
import org.choongang.homework.entities.TrainingData;
import org.choongang.homework.service.HomeworkInfoService;
import org.choongang.homework.service.HomeworkSaveService;
import org.choongang.member.MemberUtil;
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
    private final MemberUtil memberUtil;

    @GetMapping
    public String homework(Model model) {

        // 내가(한 교육자가) 담당하는 그룹만 조회할 수 있도록
        /*
        Member member = memberUtil.getMember();

        List<Homework> items = homeworkInfoService.getList(member.getNum());
         */
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

    @GetMapping("/submitList/{num}")
    public String submitList() {
        // 그룹 학습자들이 제출한 내용을 가져갈 수 있도록.

        return "front/teacher/homework/submitList";
    }

    @GetMapping("/list")
    public String homeworkList() {
        return "/front/user/homework/list";
    }

    @GetMapping("/submit/{num}")
    public String submit(@PathVariable("num") Long num, @ModelAttribute TrainingData trainingData, Model model) {
        model.addAttribute("trainingData", trainingData);
        return "front/user/homework/homework";
    }

    @PostMapping("/submit")
    public String submitPs() {



        return "redirect:/homework/list";
    }
}
