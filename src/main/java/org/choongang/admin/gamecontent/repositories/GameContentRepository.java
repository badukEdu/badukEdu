package org.choongang.admin.gamecontent.repositories;

import org.choongang.admin.gamecontent.entities.GameContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameContentRepository extends JpaRepository<GameContent, Long> {
}
