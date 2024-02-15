package org.choongang.stGrooup.services.stGroup;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.choongang.stGrooup.controllers.RequestStGroup;
import org.choongang.stGrooup.controllers.StGroupSearch;
import org.choongang.stGrooup.entities.QStudyGroup;
import org.choongang.stGrooup.entities.StudyGroup;
import org.choongang.stGrooup.repositories.StGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SGInfoService {

    private final StGroupRepository stGroupRepository;
    private final EntityManager em;


    public List<StudyGroup> getList(StGroupSearch search){

        QStudyGroup studyGroup = QStudyGroup.studyGroup;
       // QGameContent gameTitle = QGameContent.gameTitle;
        BooleanBuilder andBuilder = new BooleanBuilder();

        String sopt = search.getSopt();
        String skey = search.getSkey().trim();

        if(StringUtils.hasText(skey)){

            BooleanExpression groupNameCond = studyGroup.name.contains(skey);
            BooleanExpression gameNameCond = studyGroup.gameTitle.contains(skey);
            if(sopt.equals("groupName")){
                andBuilder.and(groupNameCond);
            } else if (sopt.equals("gameName")) {
                andBuilder.and(gameNameCond);
            } else if (sopt.equals("ALL")) {
                BooleanBuilder orBuilder = new BooleanBuilder();
                orBuilder.or(groupNameCond)
                        .or(gameNameCond);

                andBuilder.and(orBuilder);
            }

        }
        PathBuilder<StudyGroup> pathBuilder = new PathBuilder<>(StudyGroup.class, "stGroup");

        List<StudyGroup> items = new JPAQueryFactory(em)
                .selectFrom(studyGroup)
                .where(andBuilder)
                .fetch();


            return items;
    }

    public StudyGroup getById(Long num){

        return stGroupRepository.getById(num);
    }

    public RequestStGroup getForm(Long num) {
        StudyGroup data = getById(num);
        RequestStGroup form = new ModelMapper().map(data, RequestStGroup.class);
        form.setNum(data.getNum());
        return form;
    }

}
