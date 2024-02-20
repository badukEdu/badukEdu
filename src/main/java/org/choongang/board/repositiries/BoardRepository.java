package org.choongang.board.repositiries;

import org.choongang.admin.gamecontent.entities.GameContent;
import org.choongang.board.entities.Notice_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Notice_, Long>, QuerydslPredicateExecutor<Notice_> {

    // 노출 여부를 기준으로 게시물을 조회하는 메소드
    List<Notice_> findByOrderByOnTopDesc();

    // 게시글 번호로 상세 페이지를 조회하는 메소드
    Optional<Notice_> findByNum(Long num);
}
