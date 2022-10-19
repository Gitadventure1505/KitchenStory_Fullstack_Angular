package com.in.Kitchenstory.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.in.Kitchenstory.constants.KitchenstoryConstants;
import com.in.Kitchenstory.rest.UserRest;
import com.in.Kitchenstory.service.UserService;
import com.in.Kitchenstory.utils.KitchenstoryUtils;


@RestController
public class UserRestImpl implements UserRest {

	
	@Autowired
	UserService userservice;
	
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try
		{
			return userservice.signUp(requestMap);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return KitchenstoryUtils.getResponseEntity(KitchenstoryConstants.Something_Went_Wrong, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		
		try
		{
			return userservice.login(requestMap);
		}
		catch (Exception e)
		{
			
		}
		
		return KitchenstoryUtils.getResponseEntity(KitchenstoryConstants.Something_Went_Wrong, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
