package org.choongang.edu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher/edu")
public class EduController {

    /* 학습 자료 등록 */
    @GetMapping("/add")
    public String add() {

        return "front/teacher/edu/add";
    }

}
