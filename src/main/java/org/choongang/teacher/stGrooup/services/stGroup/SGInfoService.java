package org.choongang.teacher.stGrooup.services.stGroup;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.gamecontent.controllers.GameContentSearch;
import org.choongang.admin.gamecontent.entities.GameContent;
import org.choongang.admin.gamecontent.service.GameContentInfoService;
import org.choongang.commons.ListData;
import org.choongang.commons.Pagination;
import org.choongang.commons.Utils;
import org.choongang.member.constants.Authority;
import org.choongang.member.entities.Member;
import org.choongang.teacher.stGrooup.controllers.RequestStGroup;
import org.choongang.teacher.stGrooup.controllers.StGroupSearch;
import org.choongang.teacher.stGrooup.entities.QStudyGroup;
import org.choongang.teacher.stGrooup.entities.QStudyGroup;
import org.choongang.teacher.stGrooup.entities.StudyGroup;
import org.choongang.teacher.stGrooup.repositories.StGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SGInfoService {

    private final StGroupRepository stGroupRepository;
    private final EntityManager em;
    private final HttpServletRequest request;
    private final HttpSession session;
    private final GameContentInfoService gameContentInfoService;


    public ListData<StudyGroup> getList(StGroupSearch search){

        int page = Utils.onlyPositiveNumber(search.getPage(), 1);
        /*페이지 블럭 수*/
        int limit = Utils.onlyPositiveNumber(search.getLimit(), 4);
        int offset = (page - 1) * limit; // 레코드 시작 위치


        QStudyGroup studyGroup = QStudyGroup.studyGroup;
       // QGameContent gameTitle = QGameContent.gameTitle;
        BooleanBuilder andBuilder = new BooleanBuilder();

        //교육자는 본인 스터디그룹만 볼 수 있음
        if(((Member)session.getAttribute("member")).getAuthority() == Authority.TEACHER){
            andBuilder.and(studyGroup.member.eq((Member) session.getAttribute("member")));
        }

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

             // 스터디그룹 리스트에 있는 게임 컨텐츠에 게임 컨텐스 셋 해줘야함
            for(StudyGroup s : items){
                gameContentInfoService.addInfo(s.getGameContent());
            }


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
