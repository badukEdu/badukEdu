package org.choongang.gameContent.service;

import lombok.RequiredArgsConstructor;
import org.choongang.gameContent.entities.GameContent;
import org.choongang.gameContent.repositories.GameContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameContentInfoService {

    private final GameContentRepository gameContentRepository;

    /* 게임 콘텐츠 조회 */
    public List<GameContent> getList() {
        List<GameContent> gameContentInfo = gameContentRepository.findAll();

        return gameContentInfo;
    }

}
