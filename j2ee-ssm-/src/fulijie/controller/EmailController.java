package fulijie.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import fulijie.po.User;
import fulijie.service.UserService;
import fulijie.util.GetRandomString;
@Controller
public class EmailController {
	@Autowired UserService userService;
	@RequestMapping("send")
	public String send(User user,Model model,HttpSession session1){
		/*
		 * 3. 发邮件
		 */
		/*
		 * 把配置文件内容加载到prop中
		 */
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		/*
		 * 登录邮件服务器，得到session
		 */
		String host = prop.getProperty("host");//服务器主机名
		String name = prop.getProperty("username");//登录名
		String pass = prop.getProperty("password");//登录密码
		Session session = MailUtils.createSession(host, name, pass);
		
		/*
		 * 创建Mail对象
		 */
		String from = prop.getProperty("from");
		String to = user.getEmail();
		
		String subject = prop.getProperty("subject");
		// MessageForm.format方法会把第一个参数中的{0},使用第二个参数来替换。
		// 例如MessageFormat.format("你好{0}, 你{1}!", "张三", "去死吧"); 返回“你好张三，你去死吧！”
		//String content = MessageFormat.format(prop.getProperty("content"), user.getActivationCode());
		//GetRandomString getRandomString = new GetRandomString();
		
		String content =GetRandomString.getRandomString(4);
		Mail mail = new Mail(from, to, subject,"验证码"+content);
		/*
		 * 发送邮件
		 */
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	    session1.setAttribute("content",content);
   model.addAttribute("success", "发送成功，注意查收");
	 //   return "redirect:email.action";
	   return "user/email";
	}	

	@RequestMapping("checkEmail")
	public String checkEmail(User user,HttpSession session,Model model) throws Exception{
	String verifyCode = user.getVerifyCode();
	String code = (String) session.getAttribute("content");
	 if(!verifyCode.equalsIgnoreCase(code)) {
		model.addAttribute("error", "验证码错误！");
         //return "user/regiest";	
		return "user/email";
	}else{
		userService.insertUser(user);
		return "success";
	  }
	}
}

