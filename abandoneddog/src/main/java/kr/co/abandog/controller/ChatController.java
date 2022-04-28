package kr.co.abandog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.abandog.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ChatController {
	
	@GetMapping("/chat")
	public void echo(@AuthenticationPrincipal MemberDTO memberDTO, Model model) {
		log.info("chat...");
		
		model.addAttribute("member", memberDTO); //로그인 세션 정보
	}

}
