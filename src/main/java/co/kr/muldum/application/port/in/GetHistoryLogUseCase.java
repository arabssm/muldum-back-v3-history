package co.kr.muldum.application.port.in;

import co.kr.muldum.application.port.in.response.HistoryLogListResponse;
import co.kr.muldum.domain.model.LogType;

public interface GetHistoryLogUseCase {
    HistoryLogListResponse getHistoryLogs(LogType type);
}