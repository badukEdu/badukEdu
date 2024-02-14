package org.choongang.admin.gameContent.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.gameContent.controllers.RequestGameContentData;
import org.choongang.admin.gameContent.entities.GameContent;
import org.choongang.admin.gameContent.repositories.GameContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameContentSaveService {

    private final GameContentRepository gameContentRepository;

    /**
     * 게임 콘텐츠 등록
     * @param form
     */
    public void save(RequestGameContentData form) {

        GameContent gameContent = new GameContent();

        gameContent.setGameTitle(form.getGameTitle()); // 게임콘텐츠명
        gameContent.setTotalGameLevels(form.getTotalGameLevels()); // 총게임레벨
        gameContent.setSubscriptionMonths(form.getSubscriptionMonths()); // 구독개월
        gameContent.setStartDate(form.getStartDate()); //구독 시작일
        gameContent.setEndDate(form.getEndDate()); //구독 종료일
        gameContent.setMaxSubscriber(form.getMaxSubscriber()); //최대인원
        gameContent.setOriginalPrice(form.getOriginalPrice());
        gameContent.setDiscountRate(form.getDiscountRate()); // 할인율
        gameContent.setSalePrice(form.getSalePrice()); // 판매가
        gameContent.setPackageContents(form.getPackageContents()); // 패키지내용
        //gameContent.setThumbnail(form.getThumbnail()); // 썸네일

        gameContentRepository.saveAndFlush(gameContent);

    }

}