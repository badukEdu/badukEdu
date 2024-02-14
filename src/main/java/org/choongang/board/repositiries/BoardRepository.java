package org.choongang.board.repositiries;

import org.choongang.board.entities.Notice_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Notice_, Long> {
}
