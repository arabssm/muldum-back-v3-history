package co.kr.muldum.domain.exception;

import org.springframework.http.HttpStatus;

public class TeamServiceCommunicationException extends BusinessException {

    private static final String ERROR_CODE = "TEAM_SERVICE_COMMUNICATION_ERROR";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_GATEWAY;

    public TeamServiceCommunicationException(String message, Throwable cause) {
        super(message, HTTP_STATUS, ERROR_CODE, cause);
    }
}
