package co.kr.muldum.adapter.out.persistence.repository;

import co.kr.muldum.adapter.out.persistence.mapper.HistoryMapper;
import co.kr.muldum.application.port.out.LoadHistoryPort;
import co.kr.muldum.domain.model.History;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HistoryPersistenceAdapter implements LoadHistoryPort {

    private final HistoryJpaRepository historyJpaRepository;

    public HistoryPersistenceAdapter(HistoryJpaRepository historyJpaRepository) {
        this.historyJpaRepository = historyJpaRepository;
    }

    @Override
    public List<History> findByGeneration(Integer generation) {
        return historyJpaRepository.findByGeneration(generation).stream()
                .map(HistoryMapper::toDomain)
                .toList();
    }

    @Override
    public Integer findMaxGeneration() {
        return historyJpaRepository.findMaxGeneration().orElse(null);
    }

    @Override
    public Optional<History> findById(Long id) {
        return historyJpaRepository.findById(id)
                .map(HistoryMapper::toDomain);
    }
}