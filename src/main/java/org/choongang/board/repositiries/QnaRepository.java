package org.choongang.board.repositiries;

import org.choongang.board.entities.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {


}
