package org.choongang.gamecontent;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.gamecontent.controllers.GameContentSearch;
import org.choongang.admin.gamecontent.entities.GameContent;
import org.choongang.admin.gamecontent.service.GameContentInfoService;
import org.choongang.commons.ListData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gamecontent")
@RequiredArgsConstructor
public class GameContentController {

    private final GameContentInfoService gameContentInfoService;

    @GetMapping("/subscribe")
    public String subscribe(@ModelAttribute GameContent form,
                            GameContentSearch search,
                            Model model) {

        ListData<GameContent> data = gameContentInfoService.getList(search);
        model.addAttribute("items", data.getItems());
        model.addAttribute("pagination", data.getPagination());

        return "front/teacher/gamecontent/subscribe";
    }

    @GetMapping("/payment")
    public String payment(@ModelAttribute GameContentSearch search , Model model) {

        ListData<GameContent> data = gameContentInfoService.getList(search);
        model.addAttribute("items", data.getItems());
        model.addAttribute("pagination", data.getPagination());

        return "front/teacher/gamecontent/payment";
    }

    @GetMapping("/list")
    public String list(@ModelAttribute GameContentSearch search, Model model) {

        ListData<GameContent> data = gameContentInfoService.getList(search);
        model.addAttribute("items", data.getItems());
        model.addAttribute("pagination", data.getPagination());;

        return "front/teacher/gamecontent/list";
    }

}
