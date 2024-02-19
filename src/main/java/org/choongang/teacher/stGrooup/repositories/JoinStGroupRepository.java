package org.choongang.teacher.stGrooup.repositories;

import org.choongang.teacher.stGrooup.entities.JoinStudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface JoinStGroupRepository extends JpaRepository<JoinStudyGroup, Long>,
        QuerydslPredicateExecutor<JoinStudyGroup> {
}
