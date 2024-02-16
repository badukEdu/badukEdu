package org.choongang.homework.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.choongang.homework.controllers.RequestHomework;
import org.choongang.homework.entities.Homework;
import org.choongang.homework.entities.QHomework;
import org.choongang.homework.repositories.HomeworkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkInfoService {
    private final HomeworkRepository homeworkRepository;


    /** 숙제 전체조회
     *
     * @return
     */
    public List<Homework> getList() {
        List<Homework> items = homeworkRepository.findAll();

        return items;
    }

    /** 학습그룹별 숙제 조회 : 확인 필요
     *
     * @param memberNum
     * @return
     */
    public List<Homework> getList(Long memberNum) {

        QHomework homework = QHomework.homework;

        BooleanBuilder andBuilder = new BooleanBuilder();
        andBuilder.and(homework.studyGroup.num.eq(homework.num));

        List<Homework> items = (List<Homework>) homeworkRepository.findAll(andBuilder, Sort.by(Sort.Order.asc("createdAt")));

        return items;
    }

    /** 숙제 하나 조회
     *
     * @param num
     * @return
     */
    public RequestHomework getForm(Long num) {
        Homework homework = homeworkRepository.findById(num).orElseThrow();
        RequestHomework form = new ModelMapper().map(homework, RequestHomework.class);
        return form;
    }
}
