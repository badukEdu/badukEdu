package org.choongang.homework.repositories;

import org.choongang.homework.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
