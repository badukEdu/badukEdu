package org.choongang.admin.edu.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.edu.controllers.RequestEduData;
import org.choongang.admin.edu.entities.EduData;
import org.choongang.admin.edu.repositories.EduDataRepository;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.service.FileInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EduDataInfoService {

    private final FileInfoService fileInfoService;
    private final EduDataRepository eduDataRepository;

    /**
     * 교육 자료 조회
     * @return
     */
    public List<EduData> getList() {
        List<EduData> eduInfo = eduDataRepository.findAll();

        return eduInfo;
    }

    public EduData getById(Long num) {

        EduData data = eduDataRepository.getById(num);
        addInfo(data);

        return data;
    }

    public RequestEduData getForm(Long num) {
        EduData data = getById(num);
        RequestEduData form = new ModelMapper().map(data, RequestEduData.class);
        form.setNum(data.getNum());
        return form;
    }

    private void addInfo(EduData data) {
        List<FileInfo> items = fileInfoService.getListDone(data.getGid());
        if(items != null && !items.isEmpty()) data.setThumbnail(items.get(0));

    }
}
