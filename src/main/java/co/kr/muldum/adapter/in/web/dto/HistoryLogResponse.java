package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.domain.model.HistoryLog;
import co.kr.muldum.domain.model.LogStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HistoryLogResponse {

    private final LogStatus status;

    @JsonProperty("logged_by")
    private final Long loggedBy;

    @JsonProperty("logged_at")
    private final LocalDateTime loggedAt;

    @JsonProperty("log_contents")
    private final LogContents logContents;

    private HistoryLogResponse(LogStatus status, Long loggedBy, LocalDateTime loggedAt, LogContents logContents) {
        this.status = status;
        this.loggedBy = loggedBy;
        this.loggedAt = loggedAt;
        this.logContents = logContents;
    }

    public static HistoryLogResponse from(HistoryLog historyLog) {
        LogContents logContents = new LogContents(historyLog.getTitle(), historyLog.getContent());
        return new HistoryLogResponse(
                historyLog.getStatus(),
                historyLog.getLoggedBy(),
                historyLog.getLoggedAt(),
                logContents
        );
    }

    @Getter
    public static class LogContents {
        private final String title;
        private final String content;

        public LogContents(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }
}