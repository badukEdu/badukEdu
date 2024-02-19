package org.choongang.homework.service;

import lombok.RequiredArgsConstructor;
import org.choongang.homework.controllers.RequestHomework;
import org.choongang.homework.entities.Homework;
import org.choongang.homework.repositories.HomeworkRepository;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeworkSaveService {

    private final HomeworkRepository homeworkRepository;
    public void save(RequestHomework form) {
        String mode = form.getMode();
        Long num = form.getNum();

        Homework homework = null;
        if ("edit".equals(mode)) {
            homework = homeworkRepository.findById(num).orElseThrow();
        } else {
            homework = new Homework();
        }

        homework.setName(form.getName());
        homework.setContent(form.getContent());
        homework.setStudyLevel(form.getStudyLevel());
        homework.setDeadLine(form.getDeadLine());


        System.out.println("///////" + homework);
        /* 나중에 추가할 내용 S */
//        homework.setStudyGroup(그룹명?); // 그룹번호와 따로 처리해야 할까요?
//        homework.setMember(멤버명?);
//        homework.setTrainingDatas();

        /* 나중에 추가할 내용 E */

        homeworkRepository.saveAndFlush(homework);
    }
}
