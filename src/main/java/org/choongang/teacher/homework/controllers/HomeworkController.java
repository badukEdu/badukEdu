package org.choongang.teacher.homework.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.commons.ListData;
import org.choongang.teacher.homework.entities.Homework;
import org.choongang.teacher.homework.service.HomeworkInfoService;
import org.choongang.teacher.homework.service.HomeworkSaveService;
import org.choongang.teacher.homework.service.TrainingDataSaveService;
import org.choongang.member.MemberUtil;
import org.choongang.member.entities.Member;
import org.choongang.member.repositories.MemberRepository;
import org.choongang.teacher.stGrooup.controllers.StGroupSearch;
import org.choongang.teacher.stGrooup.entities.StudyGroup;
import org.choongang.teacher.stGrooup.services.stGroup.SGInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher/homework")
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkSaveService homeworkSaveService;
    private final HomeworkInfoService homeworkInfoService;
    private final SGInfoService sgInfoService;
    private final TrainingDataSaveService trainingDataSaveService;
    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;

    /** 교육자 index 페이지
     *
     * @param model
     * @return
     */
    @GetMapping
    public String homework(Model model) {

        // 내가(한 교육자가) 담당하는 그룹만 조회할 수 있도록
        Member member = memberUtil.getMember();
        if (member == null) {
            return "redirect:/member/login";
        }

        List<Homework> items = homeworkInfoService.getList(member.getNum());

//        List<Homework> items = homeworkInfoService.getList();
        model.addAttribute("items", items);

        return "front/teacher/homework/list";
    }

    /** 교육자 - 숙제 등록 페이지
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add(@ModelAttribute RequestHomework form, Model model) {

        return "front/teacher/homework/add";
    }

    /** 교육자 - 숙제 등록 처리
     *
     * @param form
     * @param model
     * @return
     */
    @PostMapping("/add")
    public String addPs(@Valid RequestHomework form, Model model) {

        homeworkSaveService.save(form);

        return "redirect:/homework";
    }

    /** 교육자 - 숙제 수정 페이지
     *
     * @param num
     * @param model
     * @return
     */
    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") Long num, Model model) {

        RequestHomework form = homeworkInfoService.getForm(num);
        model.addAttribute("requestForm", form);

        return "front/teacher/homework/edit";
    }

    /** 교육자 - 숙제 수정 처리
     *
     * @param form
     * @param num
     * @param model
     * @return
     */
    @PostMapping("/edit/{num}")
    public String editPs(@Valid RequestHomework form, @PathVariable("num") Long num, Model model) {
        form.setMode("edit");
        form.setNum(num);
        homeworkSaveService.save(form);

        return "redirect:/homework";
    }

    /** 교육자 - 숙제 전송 페이지
     *
     * @return
     */
    @GetMapping("/post")
    public String post(@ModelAttribute StGroupSearch search, Model model) {
        /*
        학습그룹 조회, 숙제 조회
        체크박스로 체크하여 숙제를 해당 인원들에게 전송.
         */
        Member member = memberUtil.getMember();
        if (member == null) {
            return "redirect:/member/login";
        }
        List<Homework> items = homeworkInfoService.getList(member.getNum());


        ListData<StudyGroup> data = sgInfoService.getList(search);

        model.addAttribute("list" , data.getItems());
        model.addAttribute("pagination", data.getPagination());

        model.addAttribute("items", items);

        return "front/teacher/homework/post";
    }

    /** 교육자 - 숙제 전송 처리 (작업중)
     *
     * @return
     */
    @PostMapping("/post")
    public String postPs(@RequestParam("chk") List<Integer> chks, @ModelAttribute Homework homework, Long num) {
        // 선택한 학생들에게 선택한 숙제를 연결..
        // 학생들이 homework를 조회하려면
        // trainingdata 숙제 생성일,
/*
        for (int chk : chks) {
            Member member = memberRepository.findById(Long.valueOf(chk)).orElseThrow();

            homework.setMember(member);

            TrainingData trainingData = new TrainingData();
            trainingData.setHomework(homework);
            trainingDataSaveService.save(homework);
        }
*/

        return "redirect:/homework/post";
    }

    /** 교육자 - 숙제 평가 페이지 (작업중)
     * num : 교육자 num
     * @return
     */
    @GetMapping("/assess/{num}")
    public String assess() {
        // trainigdata 점수, 평가일


        return "front/teacher/homework/assess";
    }

    /** 교육자 - 숙제 평가 처리 (작업중)
     *
     * @return
     */
    @PostMapping("/assess/{num}")
    public String assessPs() {
        // 그룹 학습자들이 제출한 내용을 가져갈 수 있도록.

        return "redirect:/homework/assess";
    }



    /** 학습자 - 학습그룹 x / 학습자에게 주어진 숙제 리스트 (작업중)
     *
     * @return
     */
    @GetMapping("/list")
    public String homeworkList() {
        // 학습그룹 num을 주소로
        return "/front/user/homework/list";
    }


    /** 사용자 - 숙제 작성 페이지 (작업중)
     *
     * @param
     * @param
     * @param model
     * @return
     */
    @GetMapping("/submit")
    public String submit(@ModelAttribute RequestTrainingData requestTrainingData, Model model) {
//        model.addAttribute("requestTrainingData", requestTrainingData);

        return "front/user/homework/submit";
    }


    /** 사용자 - 숙제 등록 처리 (작업중)
     *
     * @return
     */
    @PostMapping("/submit")
    public String submitPs() {


        return "redirect:/homework/list";
    }

    @GetMapping("get_table_data")
    @ResponseBody
    public String getTableData(@RequestParam("option") String selectedOption) {

        // 선택된 학습 그룹의 데이터를 조회
        List<Member> members = sgInfoService.getJoinMember(Long.valueOf(selectedOption));

        System.out.println("members :" +  members);

        // 조회된 데이터를 HTML 형식으로 생성
        StringBuilder tableData = new StringBuilder();


        for (Member member : members) {
            tableData.append("<tr>");
            tableData.append("<td><input type='checkbox' name='chk' th:id='*{'chk_' + num}>").append("</td>"); // 체크박스
            tableData.append("<td>").append(member.getName()).append("</td>"); // 학습자명
            tableData.append("<td>").append(member.getTel()).append("</td>"); // 전화번호
            tableData.append("<td>").append(member.getLevels()).append("</td>"); // 현재 레벨
            tableData.append("</tr>");
        }

        return tableData.toString();
    }

}