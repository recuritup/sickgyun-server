package com.sickgyun.server.profile.presentation.dto;

import com.sickgyun.server.profile.domain.Profile;
import com.sickgyun.server.profile.domain.value.Major;

public record SimpleProfileResponse(
	Long id,
	String name,
	String imageUrl,
	Major major,
	Boolean isRecruited,
	String company,
	String introduction
) {
	public static SimpleProfileResponse from(Profile profile) {
		String introduction = profile.getInformation().getIntroduction();
		if (introduction != null && introduction.length() >= 20) {
			introduction = introduction.substring(0, 20);
		}

		return new SimpleProfileResponse(
			profile.getId(),
			profile.getWriter().getName(),
			profile.getInformation().getImageUrl(),
			profile.getInformation().getMajor(),
			profile.getCompany().getIsRecruited(),
			profile.getCompany().getCompany(),
			introduction
		);
	}
}
