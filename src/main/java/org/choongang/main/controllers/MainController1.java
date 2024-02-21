package org.choongang.main.controllers;

import org.choongang.admin.menus.Menu;
import org.choongang.admin.menus.MenuDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController1 {

    @ModelAttribute("menus")
    public Map<String, List<MenuDetail>> getMenus() {
        return Menu.menus;
    }

    @ModelAttribute("addCss")
    public String[] addCss(){ return new String[] {"main/style"}; }

    @ModelAttribute("addScript")
    public String[] addScript() { return new String[] { "main/common" };}

    @GetMapping
    public String index() {

        return "front/main/index";


    }
}