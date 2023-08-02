package com.te.lms.admin.service;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.lms.exception.UsernameNotRegisteredException;
import com.te.lms.mentor.entity.AppUser;
import com.te.lms.mentor.entity.Role;
import com.te.lms.mentor.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final AppUserRepository appUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> optionalAU = appUserRepository.findById(username);
		if (optionalAU.isPresent()) {
			AppUser appUser = optionalAU.get();
			Function<Role, SimpleGrantedAuthority> function = r -> {
				return new SimpleGrantedAuthority(r.getRoleName());
			};
			Set<SimpleGrantedAuthority> authorities = appUser.getRoles().stream().map(function)
					.collect(Collectors.toSet());

			return new User(username, appUser.getPassword(), authorities);
		}
		throw new UsernameNotRegisteredException("Username not found");
	}

}
