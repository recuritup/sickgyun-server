package com.sickgyun.server.profile.infra.database;

import static com.sickgyun.server.profile.domain.QProfile.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sickgyun.server.profile.domain.Profile;
import com.sickgyun.server.profile.domain.repository.ProfileQueryRepository;
import com.sickgyun.server.profile.domain.value.Filter;
import com.sickgyun.server.profile.domain.value.Major;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileQueryRepositoryImpl implements ProfileQueryRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Profile> findAllFiltered(Filter filter) {
		return queryFactory
			.selectFrom(profile)
			.where(
				majorFilter(filter.majors()),
				reqruitedFilter(filter.isReqruited()),
				admissionYearFilter(filter.admissionYear())
			).fetch();
	}

	private BooleanExpression majorFilter(List<Major> majors) {
		if (majors == null) {
			return null;
		}

		return profile.information.major.in(majors);
	}

	private BooleanExpression reqruitedFilter(Boolean isReqruited) {
		if (isReqruited == null) {
			return null;
		}

		return profile.company.isRecruited.eq(isReqruited);
	}

	private BooleanExpression admissionYearFilter(String admissionYear) {
		if (admissionYear == null) {
			return null;
		}

		List<Integer> list = Arrays.stream(admissionYear.split("-"))
			.map(Integer::parseInt)
			.toList();

		return profile.information.admissionYear.between(list.get(0), list.get(1));
	}
}
