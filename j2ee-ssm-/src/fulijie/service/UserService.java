package fulijie.service;

import org.springframework.stereotype.Service;





import fulijie.po.User;

@Service
public interface UserService {
	//用户注册
	 public void insertUser (User user) throws Exception; 
	//用户登陆
	public User loginUser(User user) throws Exception;
	
	public void updateDegree(User user) throws Exception; 
	public User selectDegree(User user) throws Exception;
	
	//根据用户名查询用户
	public User selectByUsername(String username) throws Exception;
	//根据邮箱查询用户
	public User selectByEmail(String email) throws Exception;
}
