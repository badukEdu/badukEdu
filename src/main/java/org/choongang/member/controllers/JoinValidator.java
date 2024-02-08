package org.choongang.member.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.member.service.Authority;
import org.choongang.commons.validators.MobileCheck;
import org.choongang.commons.validators.PasswordValidator;
import org.choongang.member.repositories.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator, MobileCheck, PasswordValidator {

  private final MemberRepository memberRepository;
  private final HttpServletRequest request ;
  private final HttpSession session ;

  @Override
  public boolean supports(Class<?> clazz) {
    return JoinForm.class.isAssignableFrom(clazz);
  }

  public void validate(Object target, Errors errors) {

    JoinForm joinForm = (JoinForm) target;
    String userId = joinForm.getUserId();
    String password = joinForm.getPassword();
    String confirmPassword = joinForm.getConfirmPassword();
    String birth = joinForm.getBirth();
    String email = joinForm.getEmail();
    String authority = joinForm.getAuthority();
    String gender = joinForm.getGender();
    String tel = joinForm.getTel();
    boolean[] agrees = joinForm.getAgree();

    // 1. 이메일, 아이디 중복 여부 체크
    if (StringUtils.hasText(email) && memberRepository.existsByEmail(email)) {
      errors.rejectValue("email", "Duplicated");
    }

    if (StringUtils.hasText(userId) && memberRepository.existsByUserId(userId)) {
      errors.rejectValue("userId", "Duplicated");
    }

    // 2. 비밀번호 복잡성 체크 - 대소문자 1개 각각 포함, 숫자 1개 이상 포함, 특수문자도 1개 이상 포함
    if (StringUtils.hasText(password) && (!alphabetCheck(password, true) || !numberCheck(password) || !specialCharsCheck(password))) {
      errors.rejectValue("password", "Complexity");
    }

    // 3. 비밀번호, 비밀번호 확인 일치 여부 체크
    if (StringUtils.hasText(password) && StringUtils.hasText(confirmPassword) && !password.equals(confirmPassword)) {
      errors.rejectValue("confirmPassword", "Mismatch.password");
    }

    // 4. 이메일 인증 필수 여부 체크
    boolean isVerified = (boolean) session.getAttribute("EmailAuthVerified");
    /*개발 끝나고 지우기*/
    isVerified = true;
    /*개발 끝나고 지우기*/
    if (!isVerified) {
      // 이메일 인증이 안된 경우
      errors.rejectValue("email", "Required.verified");
    }

    // 5. 휴대전화 번호 입력 저장 없을 경우 체크 후 패스
    if(tel !=null && !tel.isBlank() ){
      if(!mobileFormCheck(tel))
        errors.rejectValue("mobile","Validation.mobile");

      tel = tel.replaceAll("\\D","");
      joinForm.setTel(tel);
    }

    // 6. 필수 약관 동의 체크
    if(agrees != null && agrees.length > 0){
      for(boolean agree : agrees){
        if(!agree){
          errors.reject("Validation.joinForm.agree");
          break;
        }
      }
    }
  }
}