package co.kr.muldum.application.port.out;

import co.kr.muldum.domain.model.History;

import java.util.List;
import java.util.Optional;

public interface LoadHistoryPort {
    List<History> findByGeneration(Integer generation);

    Optional<Integer> findMaxGeneration();

    Optional<History> findById(Long id);
}