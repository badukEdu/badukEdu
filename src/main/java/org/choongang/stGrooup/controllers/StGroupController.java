package org.choongang.stGrooup.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.gamecontent.controllers.GameContentSearch;
import org.choongang.admin.gamecontent.entities.GameContent;
import org.choongang.admin.gamecontent.service.GameContentInfoService;
import org.choongang.commons.ListData;
import org.choongang.stGrooup.entities.StudyGroup;
import org.choongang.stGrooup.services.stGroup.SGDeleteService;
import org.choongang.stGrooup.services.stGroup.SGInfoService;
import org.choongang.stGrooup.services.stGroup.SGSaveService;
import org.choongang.stGrooup.services.stGroup.vetaGameInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/studyGroup")
@RequiredArgsConstructor
public class StGroupController {


    private final SGSaveService sgSaveService;
    private final SGInfoService sgInfoService;
    private final SGDeleteService sgDeleteService;
    private final vetaGameInfo vetaGameInfo; //게임 인포서비스 만들어지면 이거 지워야함
    private final HttpSession session;
    private final GameContentInfoService gameContentInfoService;

    /**
     * 스터디 그룹 목록
     * @param model
     * @param search
     * @return
     */
    @GetMapping
    public String list(Model model , @ModelAttribute StGroupSearch search){

        ListData<StudyGroup> data = sgInfoService.getList(search);
        model.addAttribute("list" , data.getItems());
        model.addAttribute("pagination", data.getPagination());
        return "front/teacher/studyGroup/list";
    }

    /**
     * 스터디그룹 상세 (list -> detail)
     * @param num
     * @param model
     * @param search
     * @return
     */
    @GetMapping("/detail/{num}")
    public String detail(@PathVariable("num") Long num, Model model, @ModelAttribute StGroupSearch search){

        model.addAttribute("list" , sgInfoService.getList(search));
        model.addAttribute("item" , sgInfoService.getForm(num));

        return "front/teacher/studyGroup/detail";
    }

    /**
     * 상세 페이지 내에서 다른 스터디 그룹 선택시 (detail -> detail)
     * @param num
     * @param model
     * @param search
     * @return
     */
    @GetMapping("/detail")
    public String detail2(@RequestParam("num") Long num, Model model, @ModelAttribute StGroupSearch search){

        model.addAttribute("list" , sgInfoService.getList(search));
        model.addAttribute("item" , sgInfoService.getForm(num));

        return "front/teacher/studyGroup/detail";
    }

    /**
     * 스터디그룹 등록 1. 게임 컨텐츠 설정
     * @param model
     * @param form
     * @return
     */
    @GetMapping("/add")
    public String add1(Model model , @ModelAttribute RequestStGroup form ,@ModelAttribute GameContentSearch search){
        model.addAttribute("mode" , "add1");

        ListData<GameContent> data = gameContentInfoService.getList(search);
        model.addAttribute("items" , data.getItems());
        model.addAttribute("pagination" , data.getPagination());

        return "front/teacher/studyGroup/add";
    }

    /**
     * 스터디 그룹 등록 2. 스터디 그룹 상세 설정
     * @param model
     * @param form
     * @param num
     * @return
     */
    @PostMapping("/add2")
    public String add2(Model model , @ModelAttribute RequestStGroup form
            , @RequestParam(name = "num" , required = false) Long num){

        if(num == null){
            model.addAttribute("mode" , "add1");
            model.addAttribute("gameList" , vetaGameInfo.getList());
            model.addAttribute("emsg" , "게임 컨텐츠를 선택하세요");
            return "front/teacher/studyGroup/add";
        }

        model.addAttribute("mode" , "add2");
        session.setAttribute("game" , vetaGameInfo.getById(num));  //폼을 두 번 이동해야해서 session에 저장
        return "front/teacher/studyGroup/add";
    }

    /**
     * 스터디 그룹 수정
     * @param num
     * @param model
     * @return
     */
    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") Long num, Model model){
        model.addAttribute("mode" , "edit");
        RequestStGroup stg = sgInfoService.getForm(num);

        model.addAttribute("requestStGroup" , stg);
        session.setAttribute("game" , vetaGameInfo.getById(stg.getGameContentNum()));
        return "front/teacher/studyGroup/edit";
    }

    /**
     * 스터디 그룹 생성/수정
     * @param form
     * @param errors
     * @param model
     * @return
     */
    @PostMapping("/save")
    public String save( @Valid RequestStGroup form , Errors errors , Model model){

        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(System.out::println);
            model.addAttribute("mode" , "add2");
            return "front/teacher/studyGroup/add";
        }
        sgSaveService.save(form);
        session.removeAttribute("game");    //session 비워주기
        return "redirect:/studyGroup";
    }

    /**
     * 단일 삭제
     * @param num
     * @param model
     * @return
     */
    @GetMapping("/delete/{num}")
    public String delete(@PathVariable("num") Long num , Model model){
            sgDeleteService.delete(num);
        return "redirect:/studyGroup";
    }

    /**
     * 선택삭제 (여러개 동시)
     * @param chks
     * @param model
     * @return
     */
    @DeleteMapping
    public String deletes(@RequestParam(name = "chk" ) List<Long> chks ,Model model){
            for(Long n : chks){
                sgDeleteService.delete(n);
            }
        return "redirect:/studyGroup";
    }

}
