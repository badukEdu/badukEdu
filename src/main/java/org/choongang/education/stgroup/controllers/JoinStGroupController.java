package org.choongang.education.stgroup.controllers;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.commons.ListData;
import org.choongang.education.stgroup.entities.JoinStudyGroup;
import org.choongang.education.stgroup.services.joinStG.JoinSTGInfoService;
import org.choongang.education.stgroup.services.joinStG.JoinSTGSaveService;
import org.choongang.member.entities.Member;
import org.choongang.teacher.stGrooup.controllers.StGroupSearch;
import org.choongang.teacher.stGrooup.entities.StudyGroup;
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
    private final HttpSession session;

    /**
     * 스터디그룹 목록
     * @param model
     * @param search
     * @return
     */
    @GetMapping
    public String list(Model model , @ModelAttribute StGroupSearch search){
        search.setType("joinstg");
        ListData<StudyGroup> data = sgInfoService.getList(search);
       // model.addAttribute("list" , data.getItems());
        model.addAttribute("list" , validstg(data.getItems()));
        for(StudyGroup s : data.getItems()){
        }

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
        return "redirect:/JoinStudyGroup";

    }

    /**
     * 가임신청
     * @param model
     * @param search
     * @return
     */
    @GetMapping("/accept")
    public String accept(Model model , @ModelAttribute JoinStGroupSearch search){

        //joinSTGInfoService.getList();
        model.addAttribute("num" , 1);
        ListData<JoinStudyGroup> data = joinSTGInfoService.getList(search);
        //System.out.println(data.getItems()+"dddddddddddddddddddddddddddddddddddddddddd"+search);
        model.addAttribute("list" , data.getItems());
        //System.out.println("dddddddddddddddddddddddddddddddddddddddddd"+data.getItems());
        model.addAttribute("pagination" , data.getPagination());
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
        return "redirect:/JoinStudyGroup/accept";


    }


    /**
     * 이미 신청한 스터디그룹 제외
     * @param list
     * @return
     */
    public List<StudyGroup> validstg(List<StudyGroup> list){    //신청 가능한 스터디그룹 목록

        //신청 한 스터디그룹
        List<JoinStudyGroup> joinList = joinSTGInfoService.getAll();
        //로그인 회원 정보
        Member member = (Member)session.getAttribute("member");
        for(JoinStudyGroup jsg : joinList){
            if(member.getNum().equals(jsg.getMember().getNum())) {
                for(int k=list.size()-1; k>=0; k--){
                    if(list.get(k).getNum().equals(jsg.getStudyGroup().getNum())){
                        list.remove(sgInfoService.getById(jsg.getStudyGroup().getNum()));
                    }
                }
            }
        }
        return list;
    }


    public int count(int num){
        return num++;
    }

}
