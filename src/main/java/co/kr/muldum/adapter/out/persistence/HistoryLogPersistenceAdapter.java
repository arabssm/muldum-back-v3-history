package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.application.port.out.LoadHistoryLogPort;
import co.kr.muldum.domain.model.HistoryLog;
import co.kr.muldum.domain.model.LogType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoryLog> findAll() {
        return historyLogJpaRepository.findAllByOrderByLoggedAtDesc().stream()
                .map(HistoryLogMapper::toDomain)
                .collect(Collectors.toList());
    }
}