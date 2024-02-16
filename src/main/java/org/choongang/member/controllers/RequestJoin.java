package org.choongang.member.controllers;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.choongang.member.constants.Authority;

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
  @Size(min=8, max = 10)
  private String password;

  @NotBlank
  private String confirmPassword;

  @NotBlank
  private String bDay;

  private String gender;

  private String authority;

  @AssertTrue
  private boolean agree;  // 이용 동의


}
