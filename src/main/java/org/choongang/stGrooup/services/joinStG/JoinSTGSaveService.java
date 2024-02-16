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

    public void save(List<Long> chks , Long userNum){

        for(Long num : chks){
            JoinStudyGroup jsg = new JoinStudyGroup();

            //jsg.setMember(memberInfoService.getById(userNum));
            //jsg.setMember(new Member());
            jsg.setStudyGroup(sgInfoService.getById(num));
            joinStGroupRepository.saveAndFlush(jsg);
        }

    }
}
