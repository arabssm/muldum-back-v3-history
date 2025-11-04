package co.kr.muldum.application.service;

import co.kr.muldum.application.port.in.GetHistoryLogUseCase;
import co.kr.muldum.application.port.in.response.HistoryLogListResponse;
import co.kr.muldum.application.port.out.LoadHistoryLogPort;
import co.kr.muldum.domain.model.HistoryLog;
import co.kr.muldum.domain.model.LogType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HistoryLogQueryService implements GetHistoryLogUseCase {

    private final LoadHistoryLogPort loadHistoryLogPort;

    public HistoryLogQueryService(LoadHistoryLogPort loadHistoryLogPort) {
        this.loadHistoryLogPort = loadHistoryLogPort;
    }

    @Override
    public HistoryLogListResponse getHistoryLogs(LogType type) {
        List<HistoryLog> logs = (type != null)
                ? loadHistoryLogPort.findByType(type)
                : loadHistoryLogPort.findAll();

        return new HistoryLogListResponse(logs);
    }
}