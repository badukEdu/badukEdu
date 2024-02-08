package org.choongang.member.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    HttpSession session = request.getSession();

    String userId = request.getParameter("userId");
    String password = request.getParameter("password");

    session.setAttribute("userId", userId);

      if (userId == null || userId.isBlank()) {
        session.setAttribute("requiredMemberId", "NotBlank.memberId");
      }

      if (password == null || password.isBlank()) {
        session.setAttribute("requiredMemberPw", "NotBlank.memberPw");
        session.setAttribute("global", "Validation.login.fail");
      }


    response.sendRedirect(request.getContextPath() + "/member/login");
  }
}
