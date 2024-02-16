package org.choongang.stGrooup.controllers;


import lombok.RequiredArgsConstructor;
import org.choongang.commons.ListData;
import org.choongang.stGrooup.entities.JoinStudyGroup;
import org.choongang.stGrooup.entities.StudyGroup;
import org.choongang.stGrooup.services.stGroup.SGInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/JoinStudyGroup")
@RequiredArgsConstructor
public class JoinStGroupController {

    private final SGInfoService sgInfoService;

    /**
     * 스터디그룹 목록
     * @param model
     * @param search
     * @return
     */
    @GetMapping
    public String list(Model model , @ModelAttribute StGroupSearch search){

        ListData<StudyGroup> data = sgInfoService.getList(search);
        model.addAttribute("list" , data.getItems());
        model.addAttribute("pagination", data.getPagination());
        return "front/user/studyGroup/join";
    }

    /**
     * 가임신청
     * @param model
     * @param search
     * @return
     */
    @PostMapping("join")
    public String join(Model model , @ModelAttribute StGroupSearch search ,
                       @RequestParam(name = "chk" ) List<Long> chks ,
                       @RequestParam(name = "userNum" ) Long userNum){



        ListData<StudyGroup> data = sgInfoService.getList(search);
        model.addAttribute("list" , data.getItems());
        model.addAttribute("pagination", data.getPagination());
        return "front/user/studyGroup/join";

    }



}
