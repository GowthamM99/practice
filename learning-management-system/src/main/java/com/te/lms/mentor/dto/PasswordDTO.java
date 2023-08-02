package com.te.lms.mentor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PasswordDTO {
	private String oldPassword;
	private String newPassword;
	private String reEnterNewPassword;
}
