package fulijie.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;





import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.vcode.utils.VerifyCode;
import fulijie.po.User;
import fulijie.service.UserService;

@Controller
public class UserController {
	@Autowired UserService userService;
	
	//邮箱测试页面
	@RequestMapping("email")
	private String email(){
		return "user/email";
	}
	

	@RequestMapping("insertUser")
	public String insertUser(User user,HttpSession session,Model model) throws Exception{
	

		String verifyCode = user.getVerifyCode();
		String vcode = (String) session.getAttribute("vCode");
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			model.addAttribute("error", "验证码不能为空！");
			return "user/regiest";
		} else if(!verifyCode.equalsIgnoreCase(vcode)) {
			model.addAttribute("error", "验证码错误！");
			return "user/regiest";
		}
	
		
		userService.insertUser(user);
		return "success";
	}
	@RequestMapping("regiest")
	private String regiest(){
		return "user/regiest";
	}
	
	@RequestMapping("login")
	public String login(){
		return "user/login";
	}
	@RequestMapping("loginUser")
	public String loginUser(Model model,User user,HttpSession session) throws Exception{
       
		User user3 =userService.selectByUsername(user.getUsername());
		if(user3==null){
			model.addAttribute("error","该用户不存在");
			return "user/login";
		}
		
		User user1 = userService.loginUser(user);

		User user2= userService.selectDegree(user);
		if(user2.getDegree()<=3){
			if(user1==null){
				int degree = user2.getDegree();
				int degree1  =  degree +1;
				user2.setDegree(degree1);
				userService.updateDegree(user2);	
				int a =3-degree;
					if(a==0){
				model.addAttribute("error1","账户被锁请联系管理员");
					}else{
						model.addAttribute("error", "用户名或者密码不正确");
						model.addAttribute("error1","还有"+a+"次");
						}
				return "user/login";
			}else{
			session.setAttribute("username",user1.getUsername());
			return "user/list";
			}
		}else{
			model.addAttribute("error", "账户被锁请联系管理员");
			return "user/login";
		}
	
	}
	@RequestMapping("loginOut")
	public String loginOut(HttpSession session){
		session.invalidate();
		return "redirect:login.action";
	}
  //ajax异步校验用户名
	@RequestMapping("findUsername")
  public String findUsername(User user,HttpServletResponse response) throws Exception{
		User exitUser = userService.selectByUsername(user.getUsername());
		response.setContentType("text/html;charset=UTF-8");
		if(exitUser!=null){
			response.getWriter().println("<font color='red'>该用户名已经被注册</font>");
		}else{
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		 return null;
	}
  //ajax异步校验邮箱
	@RequestMapping("checkEmail1")
	public String checkEmail(User user,HttpServletResponse response) throws Exception{
		User exitUser1 = userService.selectByEmail(user.getEmail());
		response.setContentType("text/html;charset=UTF-8");
		if(exitUser1!=null){
			response.getWriter().println("<font color='red'>该邮箱已被注册</font>");
		}else{
			response.getWriter().println("<font color='green'>邮箱可以使用</font>");
		}
		return null;
	}
}
