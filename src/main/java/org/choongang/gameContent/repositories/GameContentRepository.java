package org.choongang.gameContent.repositories;

import org.choongang.gameContent.entities.GameContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameContentRepository extends JpaRepository<GameContent, Long> {
}
