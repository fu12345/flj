package fulijie.mapper;

import fulijie.po.User;

public interface UserMapperCustom {
	//用户注册
	public void insertUser (User user) throws Exception;
	//用户登陆
	public User loginUser(User user) throws Exception;
	//登陆次数更新
	public void updateDegree(User user) throws Exception;
	//查询登陆次数
	public User selectDegree(User user) throws Exception;
	//根据用户名查询用户
	public User selectByUsername(String username) throws Exception;
	//根据邮箱查询用户
	public User selectByEmail(String email) throws Exception;
}
