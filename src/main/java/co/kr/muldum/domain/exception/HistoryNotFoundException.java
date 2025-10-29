package co.kr.muldum.domain.exception;

import org.springframework.http.HttpStatus;

public class HistoryNotFoundException extends BusinessException {

    private static final String ERROR_CODE = "HISTORY_NOT_FOUND";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public HistoryNotFoundException(Integer generation) {
        super(generation + "세대의 전공동아리 정보를 찾을 수 없습니다.", HTTP_STATUS, ERROR_CODE);
    }

    public HistoryNotFoundException(Long id) {
        super("ID " + id + "에 해당하는 전공동아리 정보를 찾을 수 없습니다.", HTTP_STATUS, ERROR_CODE);
    }
}