package org.choongang.gamecontent;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.gamecontent.entities.GameContent;
import org.choongang.admin.gamecontent.service.GameContentInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gamecontent")
@RequiredArgsConstructor
public class GameContentController {

    private final GameContentInfoService gameContentInfoService;

    @GetMapping("/subscribe")
    public String subscribe(@ModelAttribute GameContent form, Model model) {

        List<GameContent> dataList = gameContentInfoService.getList();
        model.addAttribute("dataList", dataList);
        System.out.println("///////");
        return "front/teacher/gamecontent/subscribe";
    }
}
