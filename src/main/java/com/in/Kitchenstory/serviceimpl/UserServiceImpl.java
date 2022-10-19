	package com.in.Kitchenstory.serviceimpl;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.in.Kitchenstory.constants.KitchenstoryConstants;
import com.in.Kitchenstory.dao.UserDao;
import com.in.Kitchenstory.jwt.CustomerUserDetailsService;
import com.in.Kitchenstory.jwt.JwtFilter;
import com.in.Kitchenstory.jwt.JwtUtil;
import com.in.Kitchenstory.pojo.User;
import com.in.Kitchenstory.service.UserService;
import com.in.Kitchenstory.utils.KitchenstoryUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserServiceImpl implements UserService {


	
	@Autowired
	UserDao userdao;
	
	@Autowired
	AuthenticationManager authenticationmanager;
	
	@Autowired
	CustomerUserDetailsService customeruserdetailsservice;
	
	@Autowired
	JwtUtil jwtutil;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		
		
		log.info("Inside Signup{}", requestMap);
		
		try
		{
		if(validateSignupMap(requestMap))
		{
			User user = userdao.findByEmail(requestMap.get("email"));
			if(Objects.isNull(user))
			{
				userdao.save(getUserFromMap(requestMap));
				return KitchenstoryUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
			}
			else
			{
				return KitchenstoryUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
			}
		}
		else
		{
			return KitchenstoryUtils.getResponseEntity(KitchenstoryConstants.Invalid_Data, HttpStatus.BAD_REQUEST);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return KitchenstoryUtils.getResponseEntity(KitchenstoryConstants.Something_Went_Wrong, HttpStatus.BAD_REQUEST);
		
	}
	
	private boolean validateSignupMap(Map<String, String> requestMap)
	{
		if(requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("contactNumber")
		&& requestMap.containsKey("password"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private User getUserFromMap(Map<String, String> requestmap)
	{
		User user = new User();
		user.setEmail(requestmap.get("email"));
		user.setName(requestmap.get("name"));
		user.setContactNumber(requestmap.get("contactNumber"));
		user.setPassword(requestmap.get("password"));
		user.setStatus("false");
		user.setRole("user");
		return user;
		
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		
		log.info("Inside Login");
		
		try
		{
			Authentication auth = authenticationmanager.authenticate
													(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
		if(auth.isAuthenticated())
		{
			if(customeruserdetailsservice.getUserDetail().getStatus().equalsIgnoreCase("true"))
			{
				return new ResponseEntity<String>("{\"token\":\""+
			jwtutil.generateToken(customeruserdetailsservice.getUserDetail().getEmail(), 
					customeruserdetailsservice.getUserDetail().getRole())+"\"}", HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String> ("{\"message\":\""+"wait for admin approval. "+"\"}", HttpStatus.BAD_REQUEST);
			}
						
		}
		}
		catch (Exception e)
		{
			log.error("{}", e);
		}
		return new ResponseEntity<String> ("{\"message\":\""+"Bad Credentials. "+"\"}", HttpStatus.BAD_REQUEST);
	}
	
	
	
	

}
