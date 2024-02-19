package org.choongang.board.repositiries;

import org.choongang.board.entities.Notice_;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Notice_, Long> {

    // 노출 여부를 기준으로 게시물을 조회하는 메소드
    List<Notice_> findByOrderByOnTopDesc();
}
