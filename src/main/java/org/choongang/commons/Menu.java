package org.choongang.commons;

import java.util.*;

public class Menu {

    private final static Map<String, List<List<String>>> menus;

    static {
        menus = new HashMap<>();

        menus.put("introduction", Arrays.asList(

                //pageTitle , 메뉴 이름 , 뷰 경로

                Arrays.asList("이용안내" , "사이트 소개" , "") ,
                Arrays.asList("이용안내" , "이용 가이드" , "") ,
                Arrays.asList("이용안내" , "로그인" , "") ,
                Arrays.asList("이용안내" , "회원가입" , "") ,
                Arrays.asList("이용안내" , "상품소개" , "") ,
                Arrays.asList("이용안내" , "공지" , "") ,
                Arrays.asList("이용안내" , "FaQ" , "") ,
                Arrays.asList("이용안내" , "Qna" , "")
        ));

        menus.put("subscribe", Arrays.asList(
                Arrays.asList("구독 서비스" , "게임 컨텐츠 구매" , "") ,
                Arrays.asList("구독 서비스" , "내 게임 컨텐츠 조회" , "")
        ));

        menus.put("education", Arrays.asList(
                Arrays.asList("학습 서비스" , "학습그룹 가입 신청" , "")
        ));


        menus.put("teacher", Arrays.asList(
                Arrays.asList("교육자마당" , "학습그룹 등록" , "") ,
                Arrays.asList("교육자마당" , "학습그룹 조회" , "") ,
                Arrays.asList("교육자마당" , "학습그룹 가입 승인" , "") ,
                Arrays.asList("교육자마당" , "학습그룹 수정" , "") ,
                Arrays.asList("교육자마당" , "숙제 생성" , "") ,
                Arrays.asList("교육자마당" , "숙제 배포" , "") ,
                Arrays.asList("교육자마당" , "숙제 학습진도 조회" , "")
        ));

        menus.put("admin", Arrays.asList(
                Arrays.asList("운영마당" , "게임 컨텐츠 등록" , "") ,
                Arrays.asList("운영마당" , "교육자료 등록" , "") ,
                Arrays.asList("운영마당" , "게시물 등록" , "") ,
                Arrays.asList("운영마당" , "매출 조회" , "") ,
                Arrays.asList("운영마당" , "회원 조회" , "")
        ));
    }

}
