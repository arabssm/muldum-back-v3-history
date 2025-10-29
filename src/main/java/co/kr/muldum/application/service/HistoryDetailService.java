package co.kr.muldum.application.service;

import co.kr.muldum.application.port.in.GetHistoryDetailUseCase;
import co.kr.muldum.application.port.in.response.HistoryDetailResponse;
import co.kr.muldum.application.port.out.LoadHistoryPort;
import co.kr.muldum.domain.exception.HistoryNotFoundException;
import co.kr.muldum.domain.model.History;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HistoryDetailService implements GetHistoryDetailUseCase {

    private final LoadHistoryPort loadHistoryPort;

    public HistoryDetailService(LoadHistoryPort loadHistoryPort) {
        this.loadHistoryPort = loadHistoryPort;
    }

    @Override
    public HistoryDetailResponse getHistoryDetail(Long id) {
        History history = loadHistoryPort.findById(id)
                .orElseThrow(() -> new HistoryNotFoundException(id));

        return HistoryDetailResponse.from(history);
    }
}