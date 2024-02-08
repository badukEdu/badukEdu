package org.choongang.stGrooup.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.stGrooup.services.stGroup.SGSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/studyGroup")
@RequiredArgsConstructor
public class StGroupController {


    private final SGSaveService sgSaveService;


    @GetMapping("/form")
    public String form(){

        return "front/educator/studyGroup/add";
    }

    @PostMapping("/save")
    public String save(RequestStGroup form){

        sgSaveService.save(form);
        System.out.println(form+";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
        return "front/educator/studyGroup/list";
    }
}
