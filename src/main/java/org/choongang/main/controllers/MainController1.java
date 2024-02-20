package org.choongang.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController1 {

    @ModelAttribute("addCss")
    public String[] addCss(){ return new String[] {"main/style"}; }

    @ModelAttribute("addScript")
    public String[] addScript() { return new String[] { "main/common" };}

    @GetMapping("/")
    public String index() {

        return "front/main/index";


    }
}