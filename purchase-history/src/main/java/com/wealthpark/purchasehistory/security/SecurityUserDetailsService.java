package com.wealthpark.purchasehistory.security;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wealthpark.purchasehistory.schema.Purchaser;
import com.wealthpark.purchasehistory.security.jpa.SecurityRepository;
import com.wealthpark.purchasehistory.security.model.SecurityUserDetail;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	SecurityRepository securityRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Purchaser> user = securityRepository.findByUserName(userName);		
		user.orElseThrow(() -> new UsernameNotFoundException("User name does not exist in database"));
		return user.map(SecurityUserDetail :: new).get();
	}
}
