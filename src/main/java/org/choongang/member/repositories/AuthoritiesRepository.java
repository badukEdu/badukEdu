package org.choongang.member.repositories;

import org.choongang.member.service.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authority, Long> {
}
