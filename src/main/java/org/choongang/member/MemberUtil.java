package org.choongang.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.member.constants.Authority;
//import org.choongang.member.entities.Authorities;
import org.choongang.member.entities.Member;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtil {

    private final HttpSession session;

    public boolean isAdmin() {

//        if (isLogin()) {
//            return getMember().getAuthorities()
//                .stream().map(Authorities::getAuthority)
//                .anyMatch(a -> a == Authority.ADMIN);
//        }

        return isLogin() && getMember().getAuthority() == Authority.ADMIN;
    }

    public boolean isTeacher() {

//        if (isLogin()) {
//            return getMember().getAuthorities()
//                .stream().map(Authorities::getAuthority)
//                .anyMatch(a -> a == Authority.TEACHER);
//        }

        return isLogin() && getMember().getAuthority() == Authority.TEACHER;
    }

    public boolean isStudent() {

//        if (isLogin()) {
//            return getMember().getAuthorities()
//                .stream().map(Authorities::getAuthority)
//                .anyMatch(a -> a == Authority.STUDENT);
//        }

        return isLogin() && getMember().getAuthority() == Authority.STUDENT;
    }

    public boolean isUser() {

//        if (isLogin()) {
//            return getMember().getAuthorities()
//                .stream().map(Authorities::getAuthority)
//                .anyMatch(a -> a == Authority.USER);
//        }

        return isLogin() && getMember().getAuthority() == Authority.USER;
    }

    public boolean isLogin() {

        return getMember() != null;
    }

    public Member getMember() {
        Member member = (Member) session.getAttribute("member");

        return member;
    }

    public static void clearLoginData(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("NotBlank_username");
        session.removeAttribute("NotBlank_password");
        session.removeAttribute("Global_error");
    }

}
