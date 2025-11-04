package co.kr.muldum.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HistoryLog {
    private final Long id;
    private final LogType type;
    private final LogStatus status;
    private final Long loggedBy;
    private final LocalDateTime loggedAt;
    private final String title;
    private final String content;

    public HistoryLog(Long id, LogType type, LogStatus status, Long loggedBy,
                      LocalDateTime loggedAt, String title, String content) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.loggedBy = loggedBy;
        this.loggedAt = loggedAt;
        this.title = title;
        this.content = content;
    }
}