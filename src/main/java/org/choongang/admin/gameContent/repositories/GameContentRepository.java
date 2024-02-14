package org.choongang.admin.gameContent.repositories;

import org.choongang.admin.gameContent.entities.GameContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameContentRepository extends JpaRepository<GameContent, Long> {
}
