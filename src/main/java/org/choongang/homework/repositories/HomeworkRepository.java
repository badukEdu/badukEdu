package org.choongang.homework.repositories;

import org.choongang.homework.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface HomeworkRepository extends JpaRepository<Homework, Long>, QuerydslPredicateExecutor<Homework> {
}
