package com.legolas.domain;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.legolas.domain.UserGroup.Add;
import com.legolas.domain.UserGroup.Del;
import com.legolas.domain.UserGroup.Get;
import com.legolas.domain.UserGroup.Update;

public class User implements Serializable{
	
	private static final long serialVersionUID = -512662344600109384L;
	
	@NotNull(groups= {Del.class,Get.class,Update.class},
			message="用户id不能为空")
	private Integer id;
	@NotEmpty(groups= {Add.class,Update.class},message="用户名不能为空")
	private String name;
	@Min(groups= {Add.class,Update.class},value=18,message="年龄不能小于18岁")
	private Integer age;
	
	
	public User() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public User(Integer id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
}
