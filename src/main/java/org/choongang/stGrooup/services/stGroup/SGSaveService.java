package org.choongang.stGrooup.services.stGroup;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.stGrooup.controllers.RequestStGroup;
import org.choongang.stGrooup.entities.StudyGroup;
import org.choongang.stGrooup.repositories.StGroupRepository;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class SGSaveService {

    private final StGroupRepository stGroupRepository;
    private final HttpSession session;

    public void save(RequestStGroup form){
        StudyGroup s = new StudyGroup();

        s.setName(form.getName());
        s.setStartDate(form.getStartDate());
        s.setEndDate(form.getEndDate());
        s.setMaxSubscriber(form.getMaxSubscriber());
        s.setMaxLevel(form.getMaxLevel());
        s.setText(form.getText());
        /*
        s.setMember(session.member);
        s.setGameContent(gameRepository.findbyId());
        */
        stGroupRepository.saveAndFlush(s);

    }



}
