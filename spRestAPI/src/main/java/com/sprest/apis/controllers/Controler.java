package com.sprest.apis.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprest.apis.models.RestUser; 

@RestController
@RequestMapping("/users")
public class Controler {

	Map<String, RestUser> allUsers = new HashMap<>();
	
	@PutMapping(path="/{userId}")
	public String putMethod(@PathVariable String userId, @RequestBody RestUser userDetails) {
		if(allUsers.containsKey(userId)) {
			RestUser addUser = new RestUser();
			addUser.setUserId(userDetails.getUserId());
			addUser.setUserName(userDetails.getUserName());
			addUser.setUserMail(userDetails.getUserMail());
			allUsers.put(userId, addUser);
			return "User Modified Successfully";
		} else {
			return "User ID("+userId+") not found for Modify";
		}
	}
	
	@GetMapping
	public Collection<RestUser> getMethod() {
			return allUsers.values();
	}
	
	@GetMapping(path="/{userId}")
	public RestUser getMethod(@PathVariable String userId) {
		return allUsers.get(userId);
		//return allUsers.values();
	}
		
	@PostMapping
	public String postMethod(@RequestBody RestUser userDetails) {
		RestUser addUser = new RestUser();
		addUser.setUserId(userDetails.getUserId());
		addUser.setUserName(userDetails.getUserName());
		addUser.setUserMail(userDetails.getUserMail());
		allUsers.put(userDetails.getUserId(), addUser);
		return "User Added theough Post Method";
	}
	
	@DeleteMapping(path="/{userId}")
	public String deleteMethod(@PathVariable String userId) {
		if(allUsers.containsKey(userId)) {
			allUsers.remove(userId);
			return "User Deleted from Delete Method";
		} else {
			return "User ID("+userId+") not found for Delete";
		}		
	}
}
