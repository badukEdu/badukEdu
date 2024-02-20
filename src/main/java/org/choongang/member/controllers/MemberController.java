package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.commons.ExceptionProcessor;
import org.choongang.commons.Utils;
import org.choongang.member.service.FindIdService;
import org.choongang.member.service.FindPwService;
import org.choongang.member.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@SessionAttributes({"EmailAuthVerified", "requestJoin"})
public class MemberController implements ExceptionProcessor {

  private final Utils utils;
  private final JoinService joinService;
  private final FindPwService findPwService;
  private final FindIdService findIdService;
  private final FindIdValidator findIdValidator;

  /**
   * 동의 및 인증
   * @return
   */
  @ModelAttribute("requestJoin")
  public RequestJoin setEmpty() {
    return new RequestJoin();
  }

  @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
  public String confirmation( RequestJoin form, Model model) {
    commonProcess("confirmation", model);
    model.addAttribute("requestJoin", new RequestJoin());

    return "front/member/confirmation";
  }

  @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
  public String confirmationPs(@ModelAttribute("requestJoin") RequestJoin form, Errors errors,Model model) {
    commonProcess("confirmation", model);

    // 필수 필드가 비어 있는지 확인
    if (form.getName() == null || form.getName().isEmpty()) {
      errors.rejectValue("name", "required", "이름을 입력하세요.");
    }
    if (!form.isAgree()) {
      errors.rejectValue("agree", "required", "이용 약관에 동의해주세요.");
    }
    if (!form.isAgree2()) {
      errors.rejectValue("agree2", "required", "개인정보 처리 및 이용에 동의해주세요.");
    }
    if (form.getEmail() == null || form.getEmail().isEmpty()) {
      errors.rejectValue("email", "required", "이메일을 입력하세요.");
    }

    if (errors.hasErrors()) {
      System.out.println(errors + "////////////////");
      return "front/member/confirmation";
    }

    return "redirect:/member/join";
  }

  @GetMapping("/join")
  public String join(@ModelAttribute RequestJoin form, Model model) {
    commonProcess("join", model);

    // 이메일 인증 여부 false로 초기화
    model.addAttribute("EmailAuthVerified", false);

    return "front/member/join";
  }

  @PostMapping("/join")
  public String joinPs(@Valid RequestJoin form, Errors errors,Model model, SessionStatus sessionStatus) {
    commonProcess("join", model);

    joinService.process(form, errors);

    if (errors.hasErrors()) {
      System.out.println(errors + "@@@@@@@@@@@@");
      return "front/member/join";
    }

    // EmailAuthVerified, requestJoin 세션값 비우기 */
    sessionStatus.setComplete();

    return "redirect:/member/login";
  }

  @GetMapping("/login")
  public String login(Model model) {
    commonProcess("login", model);

    return "front/member/login";
  }

  @PostMapping("/login")
  public String loginPs(@Valid RequestJoin form, Errors errors,Model model) {
    commonProcess("login", model);

    return "redirect:/studyGroup";
  }

  /* 아이디 찾기 S */

  /**
   * 아이디 찾기 양식
   *
   * @param form
   * @param model
   * @return
   */

  @GetMapping("/find_id")
  public String findId(@ModelAttribute RequestFindId form, Model model) {
    commonProcess("find_id", model);

    return "front/member/find_id";
  }

  /**
   * 아이디 찾기 처리
   *
   * @param form
   * @param errors
   * @param model
   * @return
   */

  @PostMapping("/find_id")
  public String findIdPs(@Valid RequestFindId form, Errors errors, Model model) {
    commonProcess("find_id", model);

    findIdValidator.validate(form, errors);

    if (errors.hasErrors()) {

      return "front/member/find_id";
    }

    findIdService.sendUserId(form.name(), form.email());

    return "front/member/find_id_done";
  }


  @GetMapping("/find_id_done")

  public String toFindIdDone() {

    return "front/member/find_id_done";
  }

  @PostMapping("/member/find_id_done")
  public String findIdDone() {

    return "front/member/find_id_done";
  }

  /* 아이디 찾기 E */

  /**
   * 비밀번호 찾기 양식
   *
   * @param model
   * @return
   */
  @GetMapping("/find_pw")
  public String findPw(@ModelAttribute RequestFindPw form, Model model) {
    commonProcess("find_pw", model);

    return "front/member/find_pw";
  }

  /**
   * 비밀번호 찾기 처리
   *
   * @param model
   * @return
   */
  @PostMapping("/find_pw")
  public String findPwPs(@Valid RequestFindPw form, Errors errors, Model model) {
    commonProcess("find_pw", model);

    findPwService.process(form, errors); // 비밀번호 찾기 처리

    if (errors.hasErrors()) {
      return "front/member/find_pw";
    }

    // 비밀번호 찾기에 이상 없다면 완료 페이지로 이동
    return "redirect:/member/find_pw_done";
  }

  /**
   * 비밀번호 찾기 완료 페이지
   *
   * @param model
   * @return
   */
  @GetMapping("/find_pw_done")
  public String findPwDone(Model model) {
    commonProcess("find_pw", model);

    return "front/member/find_pw_done";
  }


  private void commonProcess(String mode, Model model) {
    mode = StringUtils.hasText(mode) ? mode : "join";
    String pageTitle = Utils.getMessage("회원가입", "commons");

    List<String> addCss = new ArrayList<>();
    List<String> addScript = new ArrayList<>();
    List<String> addCommonScript = new ArrayList<>();

    if (mode.equals("login")) { // 로그인
      pageTitle = Utils.getMessage("로그인", "commons");

    } else if (mode.equals("join")) { // 회원가입
      addCss.add("member/join");
      addScript.add("member/join");
      addCommonScript.add("address");

    } else if (mode.equals("find_id")) { // 아이디 찾기
      pageTitle = Utils.getMessage("아이디_찾기", "commons");


    } else if (mode.equals("find_pw")) { // 비밀번호 찾기
      pageTitle = Utils.getMessage("비밀번호_찾기", "commons");
    }

    model.addAttribute("pageTitle", pageTitle);
    model.addAttribute("addCss", addCss);
    model.addAttribute("addScript", addScript);
    model.addAttribute("addCommonScript", addCommonScript);

  }


}