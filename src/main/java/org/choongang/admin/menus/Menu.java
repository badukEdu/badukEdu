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
                new MenuDetail("add", "Q&A", "/guid/qna/add")
        ));

        // 구독서비스 / 권한 - TEACHER , USER
        menus.put("구독서비스", Arrays.asList(
                new MenuDetail("", "게임콘텐츠 구매", "/subscription/"),
                new MenuDetail("", "내 게임콘텐츠 조회", "/subscription/")
        ));

        // 학습서비스 / 권한 - STUDENT , ADMIN
        menus.put("학습서비스", Arrays.asList(
                new MenuDetail("join", "학습그룹 가입 신청", "/education/")
        ));

        // 교육자마당 / 권한 - TEACHER , ADMIN
        menus.put("교육자마당", Arrays.asList(
                new MenuDetail("add", "학습 그룹 등록", "/teacher/"),
                new MenuDetail("list", "학습 그룹 조회", "/teacher/"),
                new MenuDetail("accept", "학습 그룹 가입 승인", "/teacher/"),
                new MenuDetail("", "숙제 생성", "/teacher/"),
                new MenuDetail("", "숙제 배포", "/teacher/"),
                new MenuDetail("", "숙제 학습 진도 조회", "/teacher/")
        ));
        // 운영마당 / 권한 - ADMIN
        menus.put("운영마당", Arrays.asList(
                new MenuDetail("", "게임콘텐츠 등록", "/admin/"),
                new MenuDetail("", "교육 자료 등록", "/admin/"),
                new MenuDetail("add", "공지사항 등록", "/admin/board/add"), // NOTICE, FAQ 같이 등록
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