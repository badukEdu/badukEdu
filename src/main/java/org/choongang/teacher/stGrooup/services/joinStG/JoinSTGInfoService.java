package org.choongang.teacher.stGrooup.services.joinStG;

import lombok.RequiredArgsConstructor;
import org.choongang.teacher.stGrooup.entities.JoinStudyGroup;
import org.choongang.teacher.stGrooup.repositories.JoinStGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinSTGInfoService {


    private final JoinStGroupRepository joinStGroupRepository;

    public JoinStudyGroup findById(Long num){

        return joinStGroupRepository.findById(num).orElse(null);
    }

    public List<JoinStudyGroup> getList(){

        return joinStGroupRepository.findAll();
    }


}
