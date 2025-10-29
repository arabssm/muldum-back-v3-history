package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.domain.model.History;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class HistoryDetailResponse {

    private final Long id;
    private final String name;
    private final Integer generation;
    private final String logoUrl;
    private final String description;
    private final String slogan;
    private final DetailResponse detail;

    private HistoryDetailResponse(Long id, String name, Integer generation, String logoUrl,
                                   String description, String slogan, DetailResponse detail) {
        this.id = id;
        this.name = name;
        this.generation = generation;
        this.logoUrl = logoUrl;
        this.description = description;
        this.slogan = slogan;
        this.detail = detail;
    }

    public static HistoryDetailResponse from(History history) {
        DetailResponse detailResponse = history.getDetail() != null
                ? DetailResponse.from(history.getDetail())
                : null;

        return new HistoryDetailResponse(
                history.getId(),
                history.getName(),
                history.getGeneration(),
                history.getLogoUrl(),
                history.getDescription(),
                history.getSlogan(),
                detailResponse
        );
    }
}