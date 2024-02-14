package org.choongang.edu.repositories;

import org.choongang.edu.entities.EduData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EduDataRepository extends JpaRepository<EduData, Long> {
}
