package com.legolas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legolas.domain.Result;
import com.legolas.domain.User;
import com.legolas.domain.UserGroup;
import com.legolas.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value="/get")
	public Result<?> getUser(@Validated(UserGroup.Get.class) User user,BindingResult bindingResult) {
		User retUser = userService.get(user.getId());
		if(retUser!=null) {
			return Result.success(retUser);
		}else {
			return Result.fail("查询失败");
		}
	}
	
	@PostMapping(value="/add")
	public Result<?> addUser(@Validated(value= {UserGroup.Add.class}) User user,BindingResult bindingResult){
		Integer i = userService.add(user);
		if(i>0) {
			return Result.success(user);
		}else {
			return Result.fail("新增失败");
		}
	}
	
	
}
