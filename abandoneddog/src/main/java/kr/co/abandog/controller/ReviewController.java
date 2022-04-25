package kr.co.abandog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.abandog.dto.AbandogAdoptReviewDTO;
import kr.co.abandog.dto.AbandogAdoptReviewFileDTO;
import kr.co.abandog.dto.MemberDTO;
import kr.co.abandog.dto.PageRequestDTO;
import kr.co.abandog.service.AbandogAdoptReviewFileService;
import kr.co.abandog.service.AbandogAdoptReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/review/")
public class ReviewController {
	
	private final AbandogAdoptReviewService reviewService;
	private final AbandogAdoptReviewFileService reviewFileService;
	
	@GetMapping("/list")
	public void adoptReview(PageRequestDTO pageRequestDTO, Model model) {
		log.info("list get...");
		model.addAttribute("result", reviewService.getList(pageRequestDTO));
	}
	
	@GetMapping({"/read","modify"})
	public void adoptReviewRead(@AuthenticationPrincipal MemberDTO memberDTO,//loadUserByUsername에서 반환하는 객체
								@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, 
								Integer review_num,
								Model model) {
		
		AbandogAdoptReviewDTO dto = reviewService.get(review_num);
		model.addAttribute("dto" , dto);
		model.addAttribute("member", memberDTO); //로그인 세션 정보
		
	}
	
	@PostMapping("/modify")
	public String adoptReviewModify(AbandogAdoptReviewDTO dto,
								    @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
								    RedirectAttributes rattr) {
		
		log.info("modify post...");
		
		reviewService.reviewModify(dto);
		rattr.addAttribute("page", requestDTO.getPage());
		rattr.addAttribute("type", requestDTO.getType());
		rattr.addAttribute("keyword", requestDTO.getKeyword());
		rattr.addAttribute("review_num", dto.getReview_num());
		
		return "redirect:/review/read";
	}
	
	@GetMapping("/register")
	public void adoptReviewRegistr(@AuthenticationPrincipal MemberDTO memberDTO, Model model) {
		log.info("regiser get...");
		model.addAttribute("member", memberDTO);
	}
	
	@PostMapping("/register")
	public String adoptReviewRegistr(AbandogAdoptReviewDTO dto,
								     RedirectAttributes rattr,
								     AbandogAdoptReviewFileDTO uploadFiles) {
		
		log.info("regiser post...");
		//게시물 등록
		Integer review_num = reviewService.reviewRegister(dto);
		
		//게시물 파일 등록
		reviewFileService.reviewFileRegister(review_num, uploadFiles);
		
		rattr.addFlashAttribute("msg", review_num + "등록");
		
		return "redirect:/review/list";		
	}
	
	@PostMapping("/remove")
	public String adoptReviewRemove(Integer review_num, RedirectAttributes rattr) {
		reviewService.reviewRemove(review_num);
		
		rattr.addFlashAttribute("msg", review_num + "삭제");
		return "redirect:/review/list";
	}
	
}
