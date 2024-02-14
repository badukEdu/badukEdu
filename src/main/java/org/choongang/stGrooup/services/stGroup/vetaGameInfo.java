package org.choongang.stGrooup.services.stGroup;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.gameContent.entities.GameContent;
import org.choongang.admin.gameContent.repositories.GameContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class vetaGameInfo {

    private final GameContentRepository gameContentRepository;


    //////////////이 부분은 infoService 만드시면 지워야 함 임의로 사용할라고 만든거(표찬)
    public List<GameContent> getList(){
        return gameContentRepository.findAll();
    }
    public GameContent getById(Long num){
        return gameContentRepository.findById(num).orElse(new GameContent());
    }
    //////////////////////////////

}
