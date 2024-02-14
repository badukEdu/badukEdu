package org.choongang.stGrooup.services.stGroup;

import lombok.RequiredArgsConstructor;
import org.choongang.stGrooup.repositories.StGroupRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SGDeleteService {

    private final StGroupRepository stGroupRepository;


    public void delete(Long num){

        stGroupRepository.deleteById(num);
    }

}
