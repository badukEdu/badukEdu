package org.choongang.member.service;

import lombok.RequiredArgsConstructor;
import org.choongang.member.entities.Member;
import org.choongang.member.repositories.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

  private final MemberRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = repository.findByUserId(username);
    if (member == null)
      throw new UsernameNotFoundException(username);

//    List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(member.getRole().toString()));

    return MemberInfo.builder()
        .num(member.getNum())
        .userId(member.getUserId())
        .password(member.getPassword())
        .email(member.getEmail())
//        .authorities(authorities)
        .build();
  }
}
