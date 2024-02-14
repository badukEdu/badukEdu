package org.choongang.member.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.choongang.board.entities.Notice_;
import org.choongang.commons.entities.Base;
import org.choongang.edu.entities.EduData;
import org.choongang.gameContent.entities.GameContent;
import org.choongang.homework.entities.Homework;

import org.choongang.stGrooup.entities.JoinStudyGroup;
import org.choongang.stGrooup.entities.StudyGroup;
import org.choongang.stGrooup.entities.TrainingData;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

import java.util.List;

@Data
@Entity
public class Member extends Base {
  @Id //PK
  @GeneratedValue //자동 키 생성
  private Long num; //회원 번호
  @Column(length = 30, nullable = false, unique = true)
  private String userId; //사용자 아이디
  @Column(length = 30, nullable = false, unique = true)
  private String type; //사용자 구분1(운영자 / 교육자 / 일반학습자 / 학생학습자)
  @Column
  private String authorities; //사용자 구분2(유료회원 / 무료회원)
  @Column(length = 30, nullable = false)
  private String name; //사용자 성명
  @Column(length=30, nullable = false)
  private String password; //비밀번호
  @Column(length=30, nullable = false)
  private Long levels; //레벨 (??)
  @Column(nullable = false)
  private String tel; //전화번호
  @Column
  private String phonNum; //집전화
  @Column(nullable = false)
  private String birth; //생년월일
  @Column
  private String gender; //성별 (M / F)
  @Column(length=40, unique = true)
  private String email; //이메일

  @Column
  private boolean agree; //수신동의(이메일 E , SMS 수신 S, 모두 수신 ES, 수신 X)
  @Column
  private boolean use;  // 계정 상태 (정상 1 / 정지,탈퇴 0)

  ////////////////////////////////////////////////

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<StudyGroup> StudyGroups;

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<GameContent> gameContents;

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<EduData> eduDatas;

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Notice_> notices;

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Homework> homeworks;

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<TrainingData> trainingDatas;

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<JoinStudyGroup> joinStudyGroups;
}
