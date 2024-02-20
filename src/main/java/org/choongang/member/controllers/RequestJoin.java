package org.choongang.member.controllers;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.choongang.member.constants.Authority;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class RequestJoin {

  @NotBlank
  private String name;

  @NotBlank
  @Size(min=10, max=11)
  private String tel;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Size(min=6)
  private String userId;

  @NotBlank
  @Size(min=8)
  private String password;

  @NotBlank
  private String confirmPassword;

  @NotBlank
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private String bDay;

  private String gender;

  private String authority;

  @AssertTrue
  private boolean agree;  // 이용 동의

  private boolean SMSAgree;

  private boolean EmailAgree;

  @AssertTrue
  private boolean agree2;  // 개인정보 필수항목에 대한 처리 및 이용(필수)

  private boolean agree3;  // 개인정보 선택항목에 대한 처리 및 이용

  private boolean agree4;  // 개인정보 마케팅 및 광고 활용

  private boolean agree5;  // 개인정보의 위탁
}
