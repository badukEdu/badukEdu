package org.choongang.member.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "USER_MEMBER")
public class Member {
  @Id //PK
  @GeneratedValue //자동 키 생성
  @Column(name = "USER_NUM")
  private Long num; //회원 번호
  @Column(length = 30, nullable = false, unique = true)
  private String userId; //사용자 아이디
  @Column(length = 30, nullable = false)
  private String userName; //사용자 성명
  @Column(nullable = false)
  private String tel; //전화번호
  @Column
  private String phonNum; //집전화
  @Column(length=40, unique = true)
  private String email; //이메일
  @Column(length=30, nullable = false)
  private String password; //비밀번호
  @Column(length=30, nullable = false)
  private String confirmPassword; //비밀번호 확인
  @Column(nullable = false)
  private String birth; //생년월일
  @Column
  private String gender; //성별
  private boolean agree; //수신동의(이메일 수신 , SMS 수신 , 모두 수신 , 수신X)
  private Long subscriptionCnt; //구독 횟수
  private Date SDate; //가입일자
  private String authorities; //자격(유료, 무료)
  private String type;// 회원 구분(관리자 , 교육자 , 학생 학습자 , 일반 학습자)

}
