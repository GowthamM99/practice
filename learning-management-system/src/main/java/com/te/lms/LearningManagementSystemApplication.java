package com.te.lms;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.te.lms.admin.entity.Admin;
import com.te.lms.admin.entity.Batch;
import com.te.lms.admin.entity.Mentor;
import com.te.lms.admin.entity.Technology;
import com.te.lms.admin.repository.AdminRepository;
import com.te.lms.admin.repository.TechnologyRepository;
import com.te.lms.mentor.entity.AppUser;
import com.te.lms.mentor.entity.Role;
import com.te.lms.mentor.repository.AppUserRepository;
import com.te.lms.mentor.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class LearningManagementSystemApplication {

	private final TechnologyRepository technologyRepository;
	private final RoleRepository roleRepository;
	private final AdminRepository adminRepository;
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LearningManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return args -> {
			Optional<Role> roleName = roleRepository.findByRoleName("ROLE_EMPLOYEE");
			if (!roleName.isPresent()) {
				Admin admin01 = Admin.builder().adminId("ADMIN01").adminName("Admin01").build();

				AppUser adminAppUser = AppUser.builder().username("Admin01")
						.password(passwordEncoder.encode("admin01@$")).build();

				Role admin = Role.builder().roleName("ROLE_ADMIN").appUsers(new ArrayList<AppUser>()).build();

				Role mentor = Role.builder().roleName("ROLE_MENTOR").appUsers(new ArrayList<AppUser>()).build();

				Role employee = Role.builder().roleName("ROLE_EMPLOYEE").appUsers(new ArrayList<AppUser>()).build();

				roleRepository.save(mentor);
				roleRepository.save(employee);
				adminAppUser.setRoles(List.of(admin));
				admin.setAppUsers(List.of(adminAppUser));
				adminRepository.save(admin01);
				appUserRepository.save(adminAppUser);

				Technology techOne = Technology.builder().technologyName("React").batches(new ArrayList<Batch>())
						.mentors(new ArrayList<Mentor>()).build();
				Technology techTwo = Technology.builder().technologyName("Angular").batches(new ArrayList<Batch>())
						.mentors(new ArrayList<Mentor>()).build();
				Technology techThree = Technology.builder().technologyName("Java + Spring Boot")
						.batches(new ArrayList<Batch>()).mentors(new ArrayList<Mentor>()).build();
				Technology techFour = Technology.builder().technologyName("Node & Express JS")
						.batches(new ArrayList<Batch>()).mentors(new ArrayList<Mentor>()).build();

				technologyRepository.save(techOne);
				technologyRepository.save(techTwo);
				technologyRepository.save(techThree);
				technologyRepository.save(techFour);
			}
		};
	}

}
