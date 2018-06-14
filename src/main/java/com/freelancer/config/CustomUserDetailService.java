package com.freelancer.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.freelancer.model.UserEntity;
import com.freelancer.repository.UserRepository;


@Component
public class CustomUserDetailService implements UserDetailsService{

	
	private final UserRepository userR;
	
	@Autowired
	public CustomUserDetailService(UserRepository userRepository) {
		this.userR = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
		UserEntity user = userR.findByNickname(nickname);
		
		List<GrantedAuthority> authoritListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ADMIN");
		List<GrantedAuthority> authoritListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
		return new org.springframework.security.core.userdetails.User(user.getNickname(),user.getPassword(), user.isAdmin() ? authoritListAdmin : authoritListUser);
		
		
		
	}

}