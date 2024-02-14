package org.choongang.stGrooup.repositories;

import org.choongang.stGrooup.entities.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StGroupRepository extends JpaRepository<StudyGroup, Long>,
        QuerydslPredicateExecutor<StudyGroup> {
}
