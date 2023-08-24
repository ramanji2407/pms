package com.spring.pms.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.pms.Service.Jwtservice;
import com.spring.pms.Service.Userinfouserdetailservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class Authfilter extends OncePerRequestFilter{
	@Autowired
	private Jwtservice jwtservice;
	@Autowired
	private Userinfouserdetailservice userinfouserdetailservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authheader=request.getHeader("Authorization");
		String token=null;
		String username=null;
		if(authheader!=null&&authheader.startsWith("Bearer "))
		{
			token=authheader.substring(7);
			username=jwtservice.extractUsername(token);
			
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {	
			UserDetails userdetail=userinfouserdetailservice.loadUserByUsername(username);
			if (jwtservice.validateToken(token, userdetail)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetail, null, userdetail.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
			
		}
		filterChain.doFilter(request, response);
		
		
	}

		
	}


