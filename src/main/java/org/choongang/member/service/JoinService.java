package org.choongang.member.service;

import lombok.RequiredArgsConstructor;
import org.choongang.member.controllers.JoinValidator;
import org.choongang.member.controllers.RequestJoin;
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
public class JoinService {

  private final MemberRepository memberRepository;
  private final AuthoritiesRepository authoritiesRepository;
  private final JoinValidator validator;
  private final PasswordEncoder encoder;

  public void process(RequestJoin form, Errors errors) {
    validator.validate(form, errors);
    if (errors.hasErrors()) {
      return;
    }

    // 비밀번호 BCrypt로 해시화
    String hash = encoder.encode(form.getPassword());

    Member member = new Member();
    member.setEmail(form.getEmail());
    member.setName(form.getName());
    member.setPassword(hash);
    member.setGender(form.getGender());
    member.setUserId(form.getUserId());
    member.setTel(form.getTel());
    member.setBirth(form.getBDay());
    member.setAgree(form.isAgree());
    member.setAuthority(form.getAuthority());

    process(member);

//    Authorities authorities = new Authorities();
//    authorities.setMember(member);
//    authorities.setAuthority(Authority.USER);
//    authoritiesRepository.saveAndFlush(authorities);

  }

  public void process(Member member) {
    memberRepository.saveAndFlush(member);
  }
}
