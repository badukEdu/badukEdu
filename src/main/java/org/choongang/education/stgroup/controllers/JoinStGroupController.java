
package org.choongang.education.stgroup.controllers;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.commons.ListData;
import org.choongang.education.stgroup.entities.JoinStudyGroup;
import org.choongang.education.stgroup.services.joinStG.JoinSTGInfoService;
import org.choongang.member.entities.Member;
import org.choongang.teacher.stGrooup.controllers.StGroupSearch;
import org.choongang.teacher.stGrooup.entities.StudyGroup;
import org.choongang.teacher.stGrooup.services.joinStG.JoinSTGSaveService;
import org.choongang.teacher.stGrooup.services.stGroup.SGInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/education/JoinStudyGroup")
@RequiredArgsConstructor
public class JoinStGroupController {

    private final SGInfoService sgInfoService;
    private final JoinSTGSaveService joinSTGSaveService;
    private final JoinSTGInfoService joinSTGInfoService;
    private final HttpSession session;

    /**
     * ( 학생이 가입 신청 하는)
     * 가입 가능한 스터디그룹 목록
     * @param model
     * @param search
     * @return
     */
    @GetMapping
    public String list(Model model , @ModelAttribute StGroupSearch search){
        search.setType("joinstg");
        ListData<StudyGroup> data = sgInfoService.getList(search);

        //validstg -> 이미 가입 한 스터디그룹은 목록에서 제외 / andBuilder로 처리한 것이 아니라 pagination 사용 불가
        model.addAttribute("list" , validstg(data.getItems()));
        //model.addAttribute("pagination", data.getPagination());
        return "front/user/studyGroup/join";
    }

    /**
     * 가입신청
     * @param model
     * @param search
     * @return
     */
    @PostMapping("/join")
    public String join(Model model , @ModelAttribute StGroupSearch search ,
                       @RequestParam(name = "chk" ) List<Long> chks ){

        //가입 신청 내역 저장(칼럼 : accept -> (미승인)false == 0)
        joinSTGSaveService.save(chks);

        return "redirect:/education/JoinStudyGroup";

    }



    /**
     * 현재 로그인 회원이 이미 가입 신청한 스터디그룹은 목록에서 제외 처리해주는 메서드
     * @param list
     * @return
     */
    public List<StudyGroup> validstg(List<StudyGroup> list){

        //가입 승인 대기 / 완료 목록
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


}
