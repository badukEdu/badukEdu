package org.choongang.member.controllers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 아이디 찾기 커맨드 객체 정의
 *
 */
public record RequestFindId(
    @NotBlank @Email
    String email,

    @NotBlank
    String name

) {}
