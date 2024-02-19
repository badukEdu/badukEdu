package org.choongang.gamecontent;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.gamecontent.controllers.GameContentSearch;
import org.choongang.admin.gamecontent.entities.GameContent;
import org.choongang.admin.gamecontent.service.GameContentInfoService;
import org.choongang.commons.ListData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gamecontent")
@RequiredArgsConstructor
public class GameContentController {

    private final GameContentInfoService gameContentInfoService;

    /**
     * 구독 할 게임콘텐츠 리스트
     * @param form
     * @param search
     * @param model
     * @return
     */
    @GetMapping("/subscribe")
    public String subscribe(@ModelAttribute GameContent form,
                            GameContentSearch search,
                            Model model) {

        ListData<GameContent> data = gameContentInfoService.getList(search);
        model.addAttribute("items", data.getItems());
        model.addAttribute("pagination", data.getPagination());

        return "front/teacher/gamecontent/subscribe";
    }

    /**
     * 결제 할 게임콘텐츠 리스트
     * @param search
     * @param model
     * @return
     */
    @PostMapping("/payment")
    public String payment(@ModelAttribute GameContentSearch search , Model model , @RequestParam(name = "chk" ) List<Long> chks) {

        List<GameContent> items = new ArrayList<>();

        for(Long num : chks){
            items.add(gameContentInfoService.getById(num));
        }

        //ListData<GameContent> data = gameContentInfoService.getList(search);
        //model.addAttribute("items", data.getItems());
        //model.addAttribute("pagination", data.getPagination());
        model.addAttribute("items", items);
        return "front/teacher/gamecontent/payment";
    }

    /**
     * 결제 완료한 게임 콘텐츠 리스트
     * @param search
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@ModelAttribute GameContentSearch search, Model model) {

        ListData<GameContent> data = gameContentInfoService.getList(search);
        model.addAttribute("items", data.getItems());
        model.addAttribute("pagination", data.getPagination());;

        return "front/teacher/gamecontent/list";
    }

}
