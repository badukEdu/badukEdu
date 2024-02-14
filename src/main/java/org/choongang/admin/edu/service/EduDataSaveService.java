package org.choongang.admin.edu.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.edu.controllers.RequestEduData;
import org.choongang.admin.edu.entities.EduData;
import org.choongang.admin.edu.repositories.EduDataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EduDataSaveService {

    private final EduDataRepository eduDataRepository;

    /**
     * 교육 자료 등록
     * @param form
     */
    public void save(RequestEduData form) {

        EduData eduData = new EduData();

        eduData.setName(form.getName());
        eduData.setDataType(form.getDataType());
        eduData.setContentType(form.getContentType());
        eduData.setServiceType(form.getServiceType());
        eduData.setContent(form.getContent());
        eduData.setFileAddress(form.getFileAddress());
//        eduData.setThumbnail(form.getThumbnail());

        eduDataRepository.saveAndFlush(eduData);

    }
}
