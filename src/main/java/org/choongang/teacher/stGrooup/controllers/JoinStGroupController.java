package org.choongang.teacher.stGrooup.controllers;


import lombok.RequiredArgsConstructor;
import org.choongang.commons.ListData;
import org.choongang.teacher.stGrooup.entities.StudyGroup;
import org.choongang.teacher.stGrooup.services.joinStG.JoinSTGInfoService;
import org.choongang.teacher.stGrooup.services.joinStG.JoinSTGSaveService;
import org.choongang.teacher.stGrooup.services.stGroup.SGInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/JoinStudyGroup")
@RequiredArgsConstructor
public class JoinStGroupController {

    private final SGInfoService sgInfoService;
    private final JoinSTGSaveService joinSTGSaveService;
    private final JoinSTGInfoService joinSTGInfoService;
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
    @PostMapping("/join")
    public String join(Model model , @ModelAttribute StGroupSearch search ,
                       @RequestParam(name = "chk" ) List<Long> chks ,
                       @RequestParam(name = "userNum" ) Long userNum){

        joinSTGSaveService.save(chks,userNum);

        ListData<StudyGroup> data = sgInfoService.getList(search);
        model.addAttribute("list" , data.getItems());
        model.addAttribute("pagination", data.getPagination());
        return "front/user/studyGroup/test";

    }

    /**
     * 가임신청
     * @param model
     * @param search
     * @return
     */
    @GetMapping("/accept")
    public String accept(Model model , @ModelAttribute StGroupSearch search){

        //joinSTGInfoService.getList();

        ListData<StudyGroup> data = sgInfoService.getList(search);
        model.addAttribute("list" , joinSTGInfoService.getList());
        //model.addAttribute("pagination", data.getPagination());
        return "front/teacher/studyGroup/acceptStudyGroup";

    }


    @PostMapping("/accept")
    public String accept1(Model model , @ModelAttribute StGroupSearch search ,
                       @RequestParam(name = "chk" ) List<Long> chks){

        System.out.println(chks);

        joinSTGSaveService.accept(chks);

        ListData<StudyGroup> data = sgInfoService.getList(search);
        model.addAttribute("list" , data.getItems());
        model.addAttribute("pagination", data.getPagination());
        return "front/user/studyGroup/test2";

    }

}
