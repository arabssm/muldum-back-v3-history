package co.kr.muldum.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Contributor {
    private final Long contributorId;
    private final String name;
    private final String githubUrl;

    public static Contributor of(Long contributorId, String name, String githubUrl) {
        return new Contributor(contributorId, name, githubUrl);
    }
}