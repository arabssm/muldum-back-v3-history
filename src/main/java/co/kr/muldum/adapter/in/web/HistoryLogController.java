package co.kr.muldum.adapter.in.web;

import co.kr.muldum.adapter.in.web.dto.HistoryLogResponse;
import co.kr.muldum.application.port.in.GetHistoryLogUseCase;
import co.kr.muldum.application.port.in.response.HistoryLogListResponse;
import co.kr.muldum.domain.model.LogType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Super Admin - History Logs", description = "Super Admin용 역대 전공동아리 로그 조회 API")
@RestController
@RequestMapping("/history/logs")
public class HistoryLogController {

    private final GetHistoryLogUseCase getHistoryLogUseCase;

    public HistoryLogController(GetHistoryLogUseCase getHistoryLogUseCase) {
        this.getHistoryLogUseCase = getHistoryLogUseCase;
    }

    @Operation(summary = "역대 전공동아리 로그 조회",
               description = "Super Admin이 역대 전공동아리 관련 기능들의 로그를 조회합니다. type 파라미터로 특정 작업(CREATE, UPDATE, DELETE)만 필터링할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = HistoryLogResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (잘못된 LogType)",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<HistoryLogResponse>> getHistoryLogs(
            @Parameter(description = "로그 타입 필터 (CREATE, UPDATE, DELETE). 미입력 시 모든 로그 반환")
            @RequestParam(required = false) LogType type) {

        HistoryLogListResponse historyLogListResponse = getHistoryLogUseCase.getHistoryLogs(type);

        List<HistoryLogResponse> responses = historyLogListResponse.getLogs().stream()
                .map(HistoryLogResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}