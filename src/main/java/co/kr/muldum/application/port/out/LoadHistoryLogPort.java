package co.kr.muldum.application.port.out;

import co.kr.muldum.domain.model.HistoryLog;
import co.kr.muldum.domain.model.LogType;

import java.util.List;

public interface LoadHistoryLogPort {
    List<HistoryLog> findByType(LogType type);
    List<HistoryLog> findAll();
}