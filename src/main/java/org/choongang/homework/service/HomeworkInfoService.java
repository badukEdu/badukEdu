package org.choongang.homework.service;

import lombok.RequiredArgsConstructor;
import org.choongang.homework.entities.Homework;
import org.choongang.homework.repositories.HomeworkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkInfoService {
    private final HomeworkRepository homeworkRepository;
    public List<Homework> getList() {
        List<Homework> items = homeworkRepository.findAll();

        return items;
    }
}
