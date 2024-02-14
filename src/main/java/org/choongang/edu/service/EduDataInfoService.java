package org.choongang.edu.service;

import lombok.RequiredArgsConstructor;
import org.choongang.edu.entities.EduData;
import org.choongang.edu.repositories.EduDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EduDataInfoService {

    private final EduDataRepository eduDataRepository;
    
    /* 교육 자료 조회 */
    public List<EduData> getList() {
        List<EduData> eduInfo = eduDataRepository.findAll();

        return eduInfo;
    }
}
