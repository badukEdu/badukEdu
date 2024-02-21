//package org.choongang.member.controllers;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.choongang.commons.ExceptionProcessor;
//import org.choongang.commons.exceptions.UnAuthorizedException;
//import org.choongang.member.MemberUtil;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Component
//@RequiredArgsConstructor
//public class AccessInterceptor implements HandlerInterceptor, ExceptionProcessor {
//
//  private final MemberUtil memberUtil;
//
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    String requestURI = request.getRequestURI();
//    String redirectURL = null;
//
//    if (requestURI.indexOf("/student") != -1 && memberUtil.isStudent()) {
//      redirectURL = "/student";
//    }
//
//    if (requestURI.indexOf("/teacher") != -1 && memberUtil.isTeacher()) {
//      redirectURL = "/teacher";
//    }
//
//    if (requestURI != null) {
//      response.sendRedirect(request.getContextPath() + redirectURL);
//    }
//
//
///*
//    // Admin 페이지 접근 권한 확인
//    if (requestURI.startsWith("/admin")) {
//      if (!memberUtil.isAdmin()) {
//        throw new UnAuthorizedException("관리자 권한이 필요합니다.");
//      }
//    }
//
//    // TEACHER 페이지 접근 권한 확인
//    if (requestURI.startsWith("/teacher")) {
//      if (!memberUtil.isTeacher()) {
//        throw new UnAuthorizedException("교사 권한이 필요합니다.");
//      }
//    }
//
//    // USER 페이지 접근 권한 확인
//    if (requestURI.startsWith("/user")) {
//      if (!memberUtil.isUser()) {
//        throw new UnAuthorizedException("사용자 권한이 필요합니다.");
//      }
//    }
//
//    // STUDENT 페이지 접근 권한 확인
//    if (requestURI.startsWith("/student")) {
//      if (!memberUtil.isStudent()) {
//        throw new UnAuthorizedException("학생 권한이 필요합니다.");
//      }
//    }
//*/
//    return redirectURL == null;
//  }
//
//}
//
