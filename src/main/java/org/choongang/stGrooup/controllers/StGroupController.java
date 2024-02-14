package org.choongang.stGrooup.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.stGrooup.entities.StudyGroup;
import org.choongang.stGrooup.services.stGroup.SGDeleteService;
import org.choongang.stGrooup.services.stGroup.SGInfoService;
import org.choongang.stGrooup.services.stGroup.SGSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/studyGroup")
@RequiredArgsConstructor
public class StGroupController {


    private final SGSaveService sgSaveService;
    private final SGInfoService sgInfoService;
    private final SGDeleteService sgDeleteService;

    @GetMapping
    public String list(Model model , @ModelAttribute StGroupSearch search){

        List<StudyGroup> list = sgInfoService.getList(search);
        model.addAttribute("list" , list);

        System.out.println(list);


        return "front/educator/studyGroup/list";
    }

    @GetMapping("/detail/{num}")
    public String detail(@PathVariable("num") Long num, Model model){

        model.addAttribute("item" , sgInfoService.getById(num));

        return "front/educator/studyGroup/detail";
    }


    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("mode" , "add");
        model.addAttribute("item" , new RequestStGroup());
        return "front/educator/studyGroup/add";
    }

    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") Long num, Model model){
        model.addAttribute("mode" , "edit");
        model.addAttribute("item" , sgInfoService.getById(num));


        return "front/educator/studyGroup/edit";
    }

    @PostMapping("/save")
    public String save(RequestStGroup form){

        sgSaveService.save(form);
        return "redirect:/studyGroup";
    }

    @GetMapping("/delete/{num}")
    public String delete(@PathVariable("num") Long num , Model model){
            sgDeleteService.delete(num);
        return "redirect:/studyGroup";
    }

    @DeleteMapping
    public String deletes(@RequestParam(name = "chk" ) List<Long> chks ,Model model){
            for(Long n : chks){
                sgDeleteService.delete(n);
            }
        return "redirect:/studyGroup";
    }

}