package fulijie.service.impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import fulijie.mapper.UserMapperCustom;
import fulijie.po.User;
import fulijie.service.UserService;

public class UserServiceImpl implements UserService{
	@Autowired  UserMapperCustom userMapperCustom;
	@Override
	public void insertUser(User user) throws Exception {
		user.setDate(new Date());
		user.setDegree(0);
		userMapperCustom.insertUser(user);

	}
	@Override
	public User loginUser(User user) throws Exception {
		
		return userMapperCustom.loginUser(user);
	}
	@Override
	public void updateDegree(User user) throws Exception {
		userMapperCustom.updateDegree(user);
		
	}
	@Override
	public User selectDegree(User user) throws Exception {
		// TODO Auto-generated method stub
		return userMapperCustom.selectDegree(user);
	}
	@Override
	public User selectByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return userMapperCustom.selectByUsername(username);
	}
	@Override
	public User selectByEmail(String email) throws Exception {

		return userMapperCustom.selectByEmail(email);
	} 

}
