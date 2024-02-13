package org.choongang.homework.service;

import lombok.RequiredArgsConstructor;
import org.choongang.homework.controllers.RequestHomework;
import org.choongang.homework.entities.Homework;
import org.choongang.homework.repositories.HomeworkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkInfoService {
    private final HomeworkRepository homeworkRepository;

    public List<Homework> getList() {
        List<Homework> items = homeworkRepository.findAll();

        return items;
    }

    public RequestHomework getForm(Long num) {
        Homework homework = homeworkRepository.findById(num).orElseThrow();
        RequestHomework form = new ModelMapper().map(homework, RequestHomework.class);
        return form;
    }
}
