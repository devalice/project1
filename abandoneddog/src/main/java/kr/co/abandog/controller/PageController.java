package kr.co.abandog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class PageController {

	@GetMapping("/")
	public String root() {
		log.info("시작 요청");
		return "layout/home";
	}
	
	@GetMapping("/home")
	public String home() {
		return "layout/home";
	}
	
	@GetMapping("login/login")
	public void login() {
	}
	
	
	@GetMapping("/test")
	public void test() {
	}
}
