package co.kr.muldum.adapter.in.web;

import co.kr.muldum.adapter.in.web.dto.HistoryDetailResponse;
import co.kr.muldum.adapter.in.web.dto.HistoryResponse;
import co.kr.muldum.application.port.in.GetHistoryByUserUseCase;
import co.kr.muldum.application.port.in.GetHistoryDetailUseCase;
import co.kr.muldum.application.port.in.GetHistoryUseCase;
import co.kr.muldum.application.port.in.response.HistoryListResponse;
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

@Tag(name = "Histories", description = "전공동아리 역사 관리 API")
@RestController
@RequestMapping("/ara/history")
public class HistoryController {

    private final GetHistoryUseCase getHistoryUseCase;
    private final GetHistoryDetailUseCase getHistoryDetailUseCase;
    private final GetHistoryByUserUseCase getHistoryByUserUseCase;

    public HistoryController(GetHistoryUseCase getHistoryUseCase,
                             GetHistoryDetailUseCase getHistoryDetailUseCase,
                             GetHistoryByUserUseCase getHistoryByUserUseCase) {
        this.getHistoryUseCase = getHistoryUseCase;
        this.getHistoryDetailUseCase = getHistoryDetailUseCase;
        this.getHistoryByUserUseCase = getHistoryByUserUseCase;
    }

    @Operation(summary = "전공동아리 역사 목록 조회", description = "특정 세대의 전공동아리 역사를 조회합니다. generation 파라미터가 없으면 최신 세대를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = HistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (0 이하의 세대 번호 또는 잘못된 파라미터 타입)",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "해당 세대의 전공동아리 정보를 찾을 수 없음",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<HistoryResponse>> getHistoryList(
            @Parameter(description = "조회할 세대 번호 (양의 정수, 미입력 시 최신 세대)")
            @RequestParam(required = false) Integer generation) {
        HistoryListResponse historyListResponse = getHistoryUseCase.getHistoryList(generation);

        List<HistoryResponse> responses = historyListResponse.getHistories().stream()
                .map(HistoryResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "전공동아리 상세 정보 조회", description = "특정 전공동아리의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = HistoryDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "해당 ID의 전공동아리 정보를 찾을 수 없음",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                    content = @Content)
    })
    @GetMapping("/{team-id}")
    public ResponseEntity<HistoryDetailResponse> getHistoryDetail(
            @Parameter(description = "전공동아리 ID")
            @PathVariable("team-id") Long id) {
        co.kr.muldum.application.port.in.response.HistoryDetailResponse historyDetailResponse =
                getHistoryDetailUseCase.getHistoryDetail(id);

        HistoryDetailResponse response = HistoryDetailResponse.from(historyDetailResponse.getHistory());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "사용자 기준 전공동아리 상세 조회", description = "사용자가 속한 전공동아리의 역사를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = HistoryDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자에게 연결된 전공동아리를 찾을 수 없음",
                    content = @Content),
            @ApiResponse(responseCode = "502", description = "팀 서비스와의 통신 오류",
                    content = @Content)
    })
    @GetMapping("/users/{user-id}")
    public ResponseEntity<HistoryDetailResponse> getHistoryDetailByUserId(
            @Parameter(description = "사용자 ID")
            @PathVariable("user-id") Long userId) {
        co.kr.muldum.application.port.in.response.HistoryDetailResponse historyDetailResponse =
                getHistoryByUserUseCase.getHistoryDetailByUserId(userId);

        HistoryDetailResponse response = HistoryDetailResponse.from(historyDetailResponse.getHistory());

        return ResponseEntity.ok(response);
    }
}
