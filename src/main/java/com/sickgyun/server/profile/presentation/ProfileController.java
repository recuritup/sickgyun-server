package com.sickgyun.server.profile.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sickgyun.server.profile.presentation.dto.ProfileCreateRequest;
import com.sickgyun.server.profile.service.CommandProfileService;
import com.sickgyun.server.user.domain.User;
import com.sickgyun.server.user.service.UserTempService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("profiles")
public class ProfileController {
	private final CommandProfileService commandService;
	private final UserTempService userTempService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody ProfileCreateRequest requestDto) {
		//TODO getCurrent User
		User writer = userTempService.getUserId1();

		commandService.create(requestDto.toEntity(), writer);
	}

}