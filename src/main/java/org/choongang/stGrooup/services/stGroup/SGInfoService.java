package org.choongang.stGrooup.services.stGroup;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.commons.ListData;
import org.choongang.commons.Pagination;
import org.choongang.commons.Utils;
import org.choongang.stGrooup.controllers.RequestStGroup;
import org.choongang.stGrooup.controllers.StGroupSearch;
import org.choongang.stGrooup.entities.QStudyGroup;
import org.choongang.stGrooup.entities.StudyGroup;
import org.choongang.stGrooup.repositories.StGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.http.HttpRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SGInfoService {

    private final StGroupRepository stGroupRepository;
    private final EntityManager em;
    private final HttpServletRequest request;


    public ListData<StudyGroup> getList(StGroupSearch search){

        int page = Utils.onlyPositiveNumber(search.getPage(), 1);
        /*페이지 블럭 수*/
        int limit = Utils.onlyPositiveNumber(search.getLimit(), 4);
        int offset = (page - 1) * limit; // 레코드 시작 위치


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
                .offset(offset)
                .limit(limit)
                .where(andBuilder)
                .fetch();
            long total = stGroupRepository.count(andBuilder);
            Pagination pagination = new Pagination(page, (int)total, 5, limit, request);

            return new ListData <> (items , pagination);
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
