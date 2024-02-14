package org.choongang.admin.edu.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.edu.entities.EduData;
import org.choongang.admin.edu.repositories.EduDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EduDataInfoService {

    private final EduDataRepository eduDataRepository;

    /**
     * 교육 자료 조회
     * @return
     */
    public List<EduData> getList() {
        List<EduData> eduInfo = eduDataRepository.findAll();

        return eduInfo;
    }
}
