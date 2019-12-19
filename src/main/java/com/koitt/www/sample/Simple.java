package com.koitt.www.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Simple {
	
	@RequestMapping("/simple.van")
	public ModelAndView simple(ModelAndView mv) {
		mv.setViewName("sample/simple");
		return mv;
	}
	
}
