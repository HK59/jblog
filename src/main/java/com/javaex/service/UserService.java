package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	
	//id 중복체크
	public String IdCheck(String id) {
		System.out.println("userservier.IcCheck()");

		String result;
		
		UserVo userVo = userDao.selectById(id);
		
		if(userVo == null) {
			result = "can";
		}else {
			result = "cant";
		}
		
		return result;
	}
	
	//회원가입 & 블로그 & 카테고리 초기값 입력
	public int join(UserVo userVo) {
		System.out.println("userservice.join()");
		
		//블로그 초기값 할당
		userVo.setBlogtitle(userVo.getUsername()+ "의 블로그입니다.");
		userVo.setLogofile("/assets/images/spring-logo.jpg");
		
		//user정보 등록
		int count=userDao.insertUser(userVo);
		
		//blog정보 등록
		userDao.insertBlog(userVo);
		
		return count;
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("userservice.login()");
		
		return userDao.selectByIdNPw(userVo);
		
	}
}
