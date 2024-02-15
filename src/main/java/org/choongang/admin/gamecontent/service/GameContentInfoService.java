package org.choongang.admin.gamecontent.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.gamecontent.controllers.GameContentSearch;
import org.choongang.admin.gamecontent.entities.GameContent;
import org.choongang.admin.gamecontent.entities.QGameContent;
import org.choongang.admin.gamecontent.repositories.GameContentRepository;
import org.choongang.commons.ListData;
import org.choongang.commons.Pagination;
import org.choongang.commons.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class GameContentInfoService {

    private final GameContentRepository gameContentRepository;
    private final HttpServletRequest request;


    /**
     * 게임 콘텐츠 검색
     * @return
     */
    public ListData<GameContent> getList(GameContentSearch search) {

        int page = Utils.onlyPositiveNumber(search.getPage(), 1);
        int limit = Utils.onlyPositiveNumber(search.getLimit(), 20);
        String sopt = search.getSopt();
        String skey = search.getSkey();
        sopt = StringUtils.hasText(sopt) ? sopt : "ALL";

        QGameContent gameContent = QGameContent.gameContent;

        /* 검색 조건 처리 S */
        BooleanBuilder andBuilder = new BooleanBuilder();
        if (StringUtils.hasText(skey)) {
            skey = skey.trim();
            BooleanExpression gameTitleConds = gameContent.gameTitle.contains(skey);
            BooleanExpression gameContentConds = gameContent.packageContents.contains(skey);
            if (sopt.equals("ALL")) {
                BooleanBuilder orBuilder = new BooleanBuilder();
                andBuilder.and(orBuilder.or(gameTitleConds).or(gameContentConds));

            } else if (sopt.equals("gameTitle")) {
                andBuilder.and(gameTitleConds);
            } else if (sopt.equals("packageContents")) {
                andBuilder.and(gameContentConds);
            }

        }

        /* 검색 조건 처리 E */

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));

        Page<GameContent> data = gameContentRepository.findAll(andBuilder, pageable);
        int total = (int)gameContentRepository.count(andBuilder);

        Pagination pagination = new Pagination(page, total, limit, 20, request);

        return new ListData<>(data.getContent(), pagination);
    }

}
