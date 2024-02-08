package org.choongang.stGrooup;

import jakarta.persistence.*;
import org.choongang.commons.entities.Base;
import org.choongang.edu.entities.EduData;
import org.choongang.gameContent.entities.GameContent;
import org.choongang.homework.entities.Homework;
import org.choongang.member.entities.Member;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class StudyGroup extends Base {

    @Id
    @GeneratedValue
    private Long num;    //기본키

    @Column(length = 80 , unique = true , nullable = false)
    private String name;     //스터디그룹명

    @Column
    private LocalDateTime startDate; //시작일

    @Column
    private LocalDateTime endDate; //종료일

    @Column(length = 80 , nullable = false)
    private Long maxSubscriber;    //최대인원

    @Column
    private Long maxLevel;   //달성 레벨

    @Column(length = 80 , nullable = false)
    private String text;     //비고

    ////////////////////////////////

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member member; //작성자 회원번호


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameContentNum")
    private GameContent gameContent; //게임 번호

    @OneToMany(mappedBy = "studyGroup", fetch = FetchType.LAZY)
    private List<Homework> homeworks;


}
