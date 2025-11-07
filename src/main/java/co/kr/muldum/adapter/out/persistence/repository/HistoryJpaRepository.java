package co.kr.muldum.adapter.out.persistence.repository;

import co.kr.muldum.adapter.out.persistence.entity.HistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HistoryJpaRepository extends JpaRepository<HistoryJpaEntity, Long> {
    List<HistoryJpaEntity> findByGeneration(Integer generation);

    @Query("SELECT MAX(h.generation) FROM HistoryJpaEntity h")
    Optional<Integer> findMaxGeneration();
}