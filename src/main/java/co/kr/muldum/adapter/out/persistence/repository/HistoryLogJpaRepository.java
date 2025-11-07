package co.kr.muldum.adapter.out.persistence.repository;

import co.kr.muldum.adapter.out.persistence.entity.HistoryLogJpaEntity;
import co.kr.muldum.domain.model.LogType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryLogJpaRepository extends JpaRepository<HistoryLogJpaEntity, Long> {
    List<HistoryLogJpaEntity> findByTypeOrderByLoggedAtDesc(LogType type);
    List<HistoryLogJpaEntity> findAllByOrderByLoggedAtDesc();
}