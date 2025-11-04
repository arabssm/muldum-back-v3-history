package co.kr.muldum.application.port.in.response;

import co.kr.muldum.domain.model.HistoryLog;
import lombok.Getter;

import java.util.List;

@Getter
public class HistoryLogListResponse {
    private final List<HistoryLog> logs;

    public HistoryLogListResponse(List<HistoryLog> logs) {
        this.logs = logs;
    }
}