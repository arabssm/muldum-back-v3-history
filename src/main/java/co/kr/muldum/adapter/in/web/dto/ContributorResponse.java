package co.kr.muldum.adapter.in.web.dto;

import co.kr.muldum.domain.model.Contributor;
import lombok.Getter;

@Getter
public class ContributorResponse {

    private final String name;
    private final String githubUrl;

    private ContributorResponse(String name, String githubUrl) {
        this.name = name;
        this.githubUrl = githubUrl;
    }

    public static ContributorResponse from(Contributor contributor) {
        return new ContributorResponse(
                contributor.getName(),
                contributor.getGithubUrl()
        );
    }
}