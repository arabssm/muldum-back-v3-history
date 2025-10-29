package co.kr.muldum.application.port.in.response;

import co.kr.muldum.domain.model.History;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryDetailResponse {
    private final History history;

    public static HistoryDetailResponse from(History history) {
        return new HistoryDetailResponse(history);
    }
}