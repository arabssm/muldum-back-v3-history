package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.domain.model.Detail;
import lombok.Getter;

import java.util.List;

@Getter
public class DetailResponse {

    private final String background;
    private final String features;
    private final String research;
    private final List<ContributorResponse> contributors;

    private DetailResponse(String background, String features, String research, List<ContributorResponse> contributors) {
        this.background = background;
        this.features = features;
        this.research = research;
        this.contributors = contributors;
    }

    public static DetailResponse from(Detail detail) {
        List<ContributorResponse> contributorResponses = detail.getContributors().stream()
                .map(ContributorResponse::from)
                .toList();

        return new DetailResponse(
                detail.getBackground(),
                detail.getFeatures(),
                detail.getResearch(),
                contributorResponses
        );
    }
}