package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.commons.ExceptionProcessor;
import org.choongang.commons.Utils;
import org.choongang.member.service.FindPwService;
import org.choongang.member.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements ExceptionProcessor {

  private final Utils utils;
  private final JoinService joinService;
  private final FindPwService findPwService; //비번찾기

  /**
   * 동의 및 인증
   * @param form
   * @param model
   * @return
   */
  @GetMapping("/confirmation")
  public String confirmation(@ModelAttribute RequestJoin form, Model model) {
    commonProcess("join", model);

    return "front/member/confirmation";
  }

  @PostMapping("/confirmation")
  public String confirmationPs(@Valid RequestJoin form, Errors errors,Model model) {
    commonProcess("join", model);

    joinService.process(form, errors);

    if (errors.hasErrors()) {
      return "front/member/confirmation";
    }

    return "redirect:/member/join";
  }

  /**
   * 회원가입
   * @param form
   * @param model
   * @return
   */
  @GetMapping("/join")
  public String join(@ModelAttribute RequestJoin form, Model model) {
    commonProcess("join", model);

    model.addAttribute("EmailAuthVerified", false);

    return "front/member/join";
  }

  @PostMapping("/join")
  public String joinPs(@Valid RequestJoin form, Errors errors,Model model, SessionStatus sessionStatus) {
    commonProcess("join", model);

    joinService.process(form, errors);

    if (errors.hasErrors()) {
      return "front/member/join";
    }

    sessionStatus.setComplete();

    return "redirect:/member/login";
  }

  /**
   * 로그인
   * @param model
   * @return
   */
  @GetMapping("/login")
  public String login(Model model) {
    commonProcess("login", model);

    return "front/member/login";
  }

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

    if (mode.equals("login")) { // 로그인
      pageTitle = Utils.getMessage("로그인", "commons");

    } else if (mode.equals("join")) { // 회원가입
      addCss.add("member/join");
      addScript.add("member/join");

    } else if (mode.equals("find_pw")) { // 비밀번호 찾기
      pageTitle = Utils.getMessage("비밀번호_찾기", "commons");
    }

    model.addAttribute("pageTitle", pageTitle);
    model.addAttribute("addCss", addCss);
    model.addAttribute("addScript", addScript);
  }
}