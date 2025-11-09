package co.kr.muldum.application.service;

import co.kr.muldum.application.port.in.GetHistoryByUserUseCase;
import co.kr.muldum.application.port.in.GetHistoryDetailUseCase;
import co.kr.muldum.application.port.in.response.HistoryDetailResponse;
import co.kr.muldum.application.port.out.LoadHistoryPort;
import co.kr.muldum.application.port.out.TeamQueryPort;
import co.kr.muldum.domain.exception.HistoryNotFoundException;
import co.kr.muldum.domain.model.History;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HistoryDetailService implements GetHistoryDetailUseCase, GetHistoryByUserUseCase {

    private final LoadHistoryPort loadHistoryPort;
    private final TeamQueryPort teamQueryPort;

    public HistoryDetailService(LoadHistoryPort loadHistoryPort, TeamQueryPort teamQueryPort) {
        this.loadHistoryPort = loadHistoryPort;
        this.teamQueryPort = teamQueryPort;
    }

    @Override
    public HistoryDetailResponse getHistoryDetail(Long id) {
        History history = loadHistoryPort.findById(id)
                .orElseThrow(() -> new HistoryNotFoundException(id));

        return HistoryDetailResponse.from(history);
    }

    @Override
    public HistoryDetailResponse getHistoryDetailByUserId(Long userId) {
        Long teamId = teamQueryPort.getTeamIdByUserId(userId);
        return getHistoryDetail(teamId);
    }
}
