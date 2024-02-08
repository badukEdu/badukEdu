package org.choongang.member.repositories;

import org.choongang.member.entities.Member;
import org.choongang.member.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

  //아이디조회
  Member findByUserId(String userId);

  //일치 여부
  default boolean existsByEmail(String email){
    QMember member = QMember.member;
    return exists(member.userId.eq(email));
  }

  default boolean existsByUserId(String userId){
    QMember member = QMember.member;
    return exists(member.userId.eq(userId));
  }
  default boolean existsByUserName(String userName){
    QMember member = QMember.member;
    return exists(member.userId.eq(userName));
  }
}
