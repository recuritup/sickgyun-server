package com.sickgyun.server.profile.presentation.dto;

import com.sickgyun.server.profile.domain.Profile;
import com.sickgyun.server.profile.domain.value.Major;

public record SimpleProfileResponse(
	Integer admissionYear,
	String imageUrl,
	Major major

) {
	public static SimpleProfileResponse from(Profile profile) {
		return new SimpleProfileResponse(
			profile.getInformation().getAdmissionYear(),
			profile.getInformation().getImageUrl(),
			profile.getInformation().getMajor()
		);
	}
}
