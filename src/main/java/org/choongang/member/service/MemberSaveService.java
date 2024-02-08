package org.choongang.member.service;

import lombok.RequiredArgsConstructor;
import org.choongang.member.controllers.JoinForm;
import org.choongang.member.controllers.JoinValidator;
import org.choongang.member.entities.Member;
import org.choongang.member.repositories.AuthoritiesRepository;
import org.choongang.member.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberSaveService {

  private final MemberRepository memberRepository;
  private final AuthoritiesRepository authoritiesRepository;
  private final PasswordEncoder encoder;
  private final JoinValidator validator;

  public void save(JoinForm form, Errors errors) {
    validator.validate(form, errors);
    if (errors.hasErrors()) {
      return;
    }

    // 비밀번호 BCrypt로 해시화
    String hash = encoder.encode(form.getPassword());

    Member member = new Member();
    member.setUserId(form.getUserId());
    member.setPassword(hash);
    member.setBirth(form.getBirth());
    member.setEmail(form.getEmail());
    member.setGender(form.getGender());
    member.setTel(form.getTel());

    process(member);

  }

  public void process(Member member) {
    memberRepository.saveAndFlush(member);
  }
}