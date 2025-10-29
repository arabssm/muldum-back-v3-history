package co.kr.muldum.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Detail {
    private final Long detailId;
    private final String background;
    private final String features;
    private final String research;
    private final List<Contributor> contributors;

    public static Detail of(Long detailId, String background, String features, String research, List<Contributor> contributors) {
        return new Detail(detailId, background, features, research, contributors);
    }
}