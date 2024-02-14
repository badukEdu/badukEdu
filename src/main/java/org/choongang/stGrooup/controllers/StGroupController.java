package org.choongang.stGrooup.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.gameContent.service.GameContentSaveService;
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
    private final GameContentSaveService gameContentSaveService; //게임 인포서비스 만들어지면 이거 지워야함

    @GetMapping
    public String list(Model model , @ModelAttribute StGroupSearch search){

        List<StudyGroup> list = sgInfoService.getList(search);
        model.addAttribute("list" , list);

        System.out.println(list);


        return "front/teacher/studyGroup/list";
    }

    @GetMapping("/detail/{num}")
    public String detail(@PathVariable("num") Long num, Model model, @ModelAttribute StGroupSearch search){

        List<StudyGroup> list = sgInfoService.getList(search);
        model.addAttribute("list" , list);
        model.addAttribute("item" , sgInfoService.getForm(num));

        return "front/teacher/studyGroup/list";
    }


    @GetMapping("/add")
    public String add(Model model , @ModelAttribute RequestStGroup form){
        model.addAttribute("mode" , "add");
        model.addAttribute("gameList" , gameContentSaveService.getList());
        return "front/teacher/studyGroup/add";
    }

    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") Long num, Model model){
        model.addAttribute("mode" , "edit");
        model.addAttribute("requestStGroup" , sgInfoService.getForm(num));

        return "front/teacher/studyGroup/edit";
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
