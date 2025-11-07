package co.kr.muldum.adapter.out.persistence.mapper;

import co.kr.muldum.adapter.out.persistence.entity.AwardJpaEntity;
import co.kr.muldum.adapter.out.persistence.entity.ContributorJpaEntity;
import co.kr.muldum.adapter.out.persistence.entity.DetailJpaEntity;
import co.kr.muldum.adapter.out.persistence.entity.HistoryJpaEntity;
import co.kr.muldum.domain.model.Award;
import co.kr.muldum.domain.model.Contributor;
import co.kr.muldum.domain.model.Detail;
import co.kr.muldum.domain.model.History;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryMapper {

    public static History toDomain(HistoryJpaEntity entity) {
        List<Award> awards = entity.getAwards().stream()
                .map(HistoryMapper::awardToDomain)
                .collect(Collectors.toList());

        Detail detail = entity.getDetail() != null ? detailToDomain(entity.getDetail()) : null;

        return History.of(
                entity.getId(),
                entity.getName(),
                entity.getGeneration(),
                entity.getClubType(),
                entity.getDescription(),
                entity.getLogoUrl(),
                entity.getSlogan(),
                awards,
                detail
        );
    }

    private static Award awardToDomain(AwardJpaEntity entity) {
        return Award.of(
                entity.getAwardId(),
                entity.getAwardType(),
                entity.getGivenAt()
        );
    }

    private static Detail detailToDomain(DetailJpaEntity entity) {
        List<Contributor> contributors = entity.getContributors().stream()
                .map(HistoryMapper::contributorToDomain)
                .collect(Collectors.toList());

        return Detail.of(
                entity.getDetailId(),
                entity.getBackground(),
                entity.getFeatures(),
                entity.getResearch(),
                contributors
        );
    }

    private static Contributor contributorToDomain(ContributorJpaEntity entity) {
        return Contributor.of(
                entity.getContributorId(),
                entity.getName(),
                entity.getGithubUrl()
        );
    }
}