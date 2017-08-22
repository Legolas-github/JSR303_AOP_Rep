package com.legolas.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.legolas.domain.User;

@Service
public class UserService {
	private static Map<Integer,User> userMap=new HashMap<>();
	private static int counter=1;
	
	public Integer add(User user) {
		user.setId(counter);
		userMap.put(counter,user);
		counter++;
		return user.getId();
	}
	
	public boolean del(Integer id) {
		User user = userMap.remove(id);
		return user==null?false:true;
	}
	
	public boolean update(User user) {
		Integer id = user.getId();
		User user2 = userMap.replace(id, user);
		return user2==null?false:true;
	}
	
	public User get(Integer id) {
		return userMap.get(id);
	}
	
}
