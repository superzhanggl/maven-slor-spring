package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description 题目
 * @author xxx
 * @time 2019-3-7下午4:59:08
 */
@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(){
		
		return "redirect:/goods/list";
	}
	
}
