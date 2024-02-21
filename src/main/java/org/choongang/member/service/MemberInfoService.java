package org.choongang.member.service;

import com.querydsl.core.BooleanBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.commons.ListData;
import org.choongang.commons.Pagination;
import org.choongang.commons.Utils;
import org.choongang.member.constants.Authority;
import org.choongang.member.controllers.MemberSearch;
//import org.choongang.member.entities.Authorities;
import org.choongang.member.entities.Member;
import org.choongang.member.entities.QMember;
import org.choongang.member.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final HttpServletRequest request;
//    private final EntityManager em;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username) // 이메일 조회
            .orElseGet(() -> memberRepository.findByUserId(username) // 아이디로 조회
                .orElseThrow(() -> new UsernameNotFoundException(username)));

//        List<SimpleGrantedAuthority> authorities = null;
        Authority authority = Objects.requireNonNullElse(member.getAuthority(), Authority.USER);
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(authority.name()));


//        if (tmp != null) {
//            authorities = tmp.stream()
//                .map(s -> new SimpleGrantedAuthority(s.getAuthority().name()))
//                .toList();
//        }
//        System.out.println("===== 체크 =======");
//        System.out.println(member);



        // 추가 정보 처리
        addMemberInfo(member);

        return MemberInfo.builder()
            .email(member.getEmail())
            .userId(member.getUserId())
            .password(member.getPassword())
            .member(member)
            .authorities(authorities)
            .enable(member.isEnable())
          //  .lock(member.isLock())
            .build();
    }

    /**
     * 회원 목록
     *
     * @param search
     * @return
     */
    public ListData<Member> getList(MemberSearch search) {

        int page = Utils.onlyPositiveNumber(search.getPage(), 1); // 페이지 번호
        int limit = Utils.onlyPositiveNumber(search.getLimit(), 20); // 1페이지당 레코드 갯수
//        int offset = (page - 1) * limit; // 레코드 시작 위치 번호

        BooleanBuilder andBuilder = new BooleanBuilder();
        QMember member = QMember.member;

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));

        Page<Member> data = memberRepository.findAll(andBuilder, pageable);

        Pagination pagination = new Pagination(page, (int)data.getTotalElements(), 10, limit, request);

        //        PathBuilder<Member> pathBuilder = new PathBuilder<>(Member.class, "member");
//
//        List<Member> items = new JPAQueryFactory(em)
//            .selectFrom(member)
//            .leftJoin(member.authorities)
//            .fetchJoin()
//            .where(andBuilder)
//            .limit(limit)
//            .offset(offset)
//            .orderBy(new OrderSpecifier(Order.DESC, pathBuilder.get("createdAt")))
//            .fetch();
//
//        /* 페이징 처리 S */
//        int total = (int)memberRepository.count(andBuilder); // 총 레코드 갯수
//
//        Pagination pagination = new Pagination(page, total, 10, limit, request);
        /* 페이징 처리 E */

        return new ListData<>(data.getContent(), pagination);
    }

    /**
     * 회원 추가 정보 처리
     *
     * @param member
     */
    public void addMemberInfo(Member member) {
    }
}