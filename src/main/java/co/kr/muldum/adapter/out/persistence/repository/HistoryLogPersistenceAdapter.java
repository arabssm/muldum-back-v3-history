package co.kr.muldum.adapter.out.persistence.repository;

import co.kr.muldum.adapter.out.persistence.mapper.HistoryLogMapper;
import co.kr.muldum.application.port.out.LoadHistoryLogPort;
import co.kr.muldum.domain.model.HistoryLog;
import co.kr.muldum.domain.model.LogType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryLogPersistenceAdapter implements LoadHistoryLogPort {

    private final HistoryLogJpaRepository historyLogJpaRepository;

    public HistoryLogPersistenceAdapter(HistoryLogJpaRepository historyLogJpaRepository) {
        this.historyLogJpaRepository = historyLogJpaRepository;
    }

    @Override
    public List<HistoryLog> findByType(LogType type) {
        return historyLogJpaRepository.findByTypeOrderByLoggedAtDesc(type).stream()
                .map(HistoryLogMapper::toDomain)
                .toList();
    }

    @Override
    public List<HistoryLog> findAll() {
        return historyLogJpaRepository.findAllByOrderByLoggedAtDesc().stream()
                .map(HistoryLogMapper::toDomain)
                .toList();
    }
}