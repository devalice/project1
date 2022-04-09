package kr.co.abandog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.abandog.dto.PageRequestDTO;
import kr.co.abandog.service.AbandogAdoptReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/review/")
public class ReviewController {
	
	private final AbandogAdoptReviewService reviewService;
	
	//pageRequestDTO: adoptreview.html에서 입력한 데이터값
	@GetMapping("/list")
	public void adoptreview(PageRequestDTO pageRequestDTO, Model model) {
		model.addAttribute("result", reviewService.getList(pageRequestDTO));
	}
	
	@GetMapping("/register")
	public void adoptreviewRegistr() {
		
	}
	
}
