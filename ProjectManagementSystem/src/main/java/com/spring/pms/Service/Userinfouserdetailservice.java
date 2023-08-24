package com.spring.pms.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import com.spring.pms.Entity.User;

import com.spring.pms.Repository.UserRepo;
@Component
public class Userinfouserdetailservice implements UserDetailsService {
@Autowired
  private UserRepo userrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User>user=	userrepo.findByName(username);
		 return  	user.map(userdetailsuserinfo::new).orElseThrow(()-> new UsernameNotFoundException("user not found with "+username));
		

	}

}
