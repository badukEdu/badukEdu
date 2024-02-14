package org.choongang.edu.service;

import lombok.RequiredArgsConstructor;
import org.choongang.edu.controllers.RequestEduData;
import org.choongang.edu.entities.EduData;
import org.choongang.edu.repositories.EduDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class EduDataSaveService {

    private final EduDataRepository eduDataRepository;

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
