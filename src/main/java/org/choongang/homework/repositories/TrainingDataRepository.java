package org.choongang.homework.repositories;

import org.choongang.homework.entities.TrainingData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingDataRepository extends JpaRepository<TrainingData, Long> {
}
