package co.kr.muldum.application.port.in;

import co.kr.muldum.application.port.in.response.HistoryDetailResponse;

public interface GetHistoryByUserUseCase {
    HistoryDetailResponse getHistoryDetailByUserId(Long userId);
}
