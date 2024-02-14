package org.choongang.admin.gameContent.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.gameContent.entities.GameContent;
import org.choongang.admin.gameContent.repositories.GameContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameContentInfoService {

    private final GameContentRepository gameContentRepository;

    /**
     * 게임 콘텐츠 조회
     * @return
     */
    public List<GameContent> getList() {
        List<GameContent> gameContentInfo = gameContentRepository.findAll();

        return gameContentInfo;
    }

}
