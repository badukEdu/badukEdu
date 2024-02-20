package org.choongang.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("AdminMainController")
@RequestMapping("/admin")
public class MainController {

    @ModelAttribute("addCss")
    public String[] addCss(){ return new String[] {"main/style"}; }

    @ModelAttribute("addScript")
    public String[] addScript() { return new String[] { "main/view" };}

    @GetMapping
    public String index() {

        return "admin/main/index";

    }
}
