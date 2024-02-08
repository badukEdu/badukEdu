package org.choongang.member.controllers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinForm {
  @NotBlank
  @Size(min = 5, max = 20)
  private String userId;
  @NotBlank
  @Size(min = 8)
  private String password;
  @NotBlank
  private String confirmPassword;
  @NotBlank
  private String birth;
  @NotBlank @Email
  private String email;
  @NotBlank
  private String authority;
  private String gender;
  @NotBlank
  private String tel;
  private boolean[] agree;//동의 여러개 체크박스
}
