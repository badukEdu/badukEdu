package org.choongang.stGrooup.services.stGroup;

import lombok.RequiredArgsConstructor;
import org.choongang.stGrooup.entities.StudyGroup;
import org.choongang.stGrooup.repositories.StGroupRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SGInfoService {

    private final StGroupRepository stGroupRepository;


    public List<StudyGroup> getList(){

        return stGroupRepository.findAll();
    }

    public StudyGroup getById(Long num){

        return stGroupRepository.getById(num);
    }

}
