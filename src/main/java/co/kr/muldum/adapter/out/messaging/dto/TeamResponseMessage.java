package co.kr.muldum.adapter.out.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseMessage {
    private Long teamId;
    private String correlationId;
}