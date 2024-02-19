package org.choongang.teacher.stGrooup.services.joinStG;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.member.entities.Member;
import org.choongang.member.service.MemberInfoService;
import org.choongang.teacher.stGrooup.entities.JoinStudyGroup;
import org.choongang.teacher.stGrooup.repositories.JoinStGroupRepository;
import org.choongang.teacher.stGrooup.services.stGroup.SGInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinSTGSaveService {


    private final JoinStGroupRepository joinStGroupRepository;
    private final SGInfoService sgInfoService;
    private final JoinSTGInfoService joinSTGInfoService;
    private final HttpSession session;


    public void save(List<Long> chks , Long userNum){

        for(Long num : chks){
            JoinStudyGroup jsg = new JoinStudyGroup();

            jsg.setMember((Member) session.getAttribute("member"));
            jsg.setStudyGroup(sgInfoService.getById(num));
            joinStGroupRepository.saveAndFlush(jsg);
        }

    }

    public void accept(List<Long> chks ){

        for(Long num : chks){
            JoinStudyGroup jsg = joinStGroupRepository.getById(num);
            if(jsg != null){
                jsg.setAccept(true);
                joinStGroupRepository.saveAndFlush(jsg);
                System.out.println("승인완료");
            }
        }

    }

}
