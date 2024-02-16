package org.choongang.stGrooup.services.joinStG;

import lombok.RequiredArgsConstructor;
import org.choongang.member.entities.Member;
import org.choongang.member.service.MemberInfo;
import org.choongang.stGrooup.entities.JoinStudyGroup;
import org.choongang.stGrooup.repositories.JoinStGroupRepository;
import org.choongang.stGrooup.services.stGroup.SGInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinSTGSaveService {


    private final JoinStGroupRepository joinStGroupRepository;
    private final SGInfoService sgInfoService;
    private final JoinSTGInfoService joinSTGInfoService;

    public void save(List<Long> chks , Long userNum){

        for(Long num : chks){
            JoinStudyGroup jsg = new JoinStudyGroup();

            //jsg.setMember(memberInfoService.getById(userNum));
            //jsg.setMember(new Member());
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
