package org.choongang.admin.menus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    public final static Map<String, List<MenuDetail>> menus;

    static {
        menus = new HashMap<>();
        //이용안내 / 권한 - ALL
        menus.put("이용안내", Arrays.asList(
                new MenuDetail("", "사이트 소개", "/guid/"),
                new MenuDetail("", "이용 가이드", "/guid/"),
                new MenuDetail("", "상품 소개", "/guid/"),
                new MenuDetail("", "FaQ", "/guid/") ,
                new MenuDetail("", "Q&A", "/guid/")
        ));

        // 구독서비스 / 권한 - TEACHER , USER
        menus.put("구독서비스", Arrays.asList(
                new MenuDetail("", "게임콘텐츠 구매", "/subscription/gamecontent/subscribe"),
                new MenuDetail("", "내 게임콘텐츠 조회", "/subscription/gamecontent/list")
        ));

        // 학습서비스 / 권한 - STUDENT , ADMIN
        menus.put("학습서비스", Arrays.asList(
                new MenuDetail("join", "학습그룹 가입 신청", "/education/JoinStudyGroup")
        ));

        // 교육자마당 / 권한 - TEACHER , ADMIN
        menus.put("교육자마당", Arrays.asList(
                new MenuDetail("add", "학습 그룹 등록", "/teacher/studyGroup/add"),//
                new MenuDetail("list", "학습 그룹 조회", "/teacher/studyGroup"),//
                new MenuDetail("accept", "학습 그룹 가입 승인", "/teacher/studyGroup/accept"),
                new MenuDetail("", "숙제 생성", "/teacher/homework/add"),
                new MenuDetail("", "숙제 배포", "/teacher/homework/post"),
                new MenuDetail("", "숙제 학습 진도 조회", "/teacher/homework")
        ));
        // 운영마당 / 권한 - ADMIN
        menus.put("운영마당", Arrays.asList(
                new MenuDetail("", "게임콘텐츠 등록", "/admin/gamecontent/add"),
                new MenuDetail("", "게임콘텐츠 조회", "/admin/gamecontent/list"),
                new MenuDetail("", "교육 자료 등록", "/admin/edu/add"),
                new MenuDetail("", "교육 자료 조회", "/admin/edd/list"),
                new MenuDetail("", "게시물 등록", "/admin/"),
                new MenuDetail("", "매출 조회", "/admin/"),
                new MenuDetail("", "회원 조회", "/admin/")
        ));
        /*
        // 회원 / 권한 - ALL
        menus.put("member", Arrays.asList(
                new MenuDetail("list", "로그인", "/admin/board/list"),
                new MenuDetail("add", "회원가입", "/admin/board/add")
        ));
        */

    }

    public static List<MenuDetail> getMenus(String key) {
        return menus.get(key);
    }
}