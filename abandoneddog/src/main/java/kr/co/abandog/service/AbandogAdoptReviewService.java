package kr.co.abandog.service;

import kr.co.abandog.dto.AbandogAdoptReviewDTO;
import kr.co.abandog.dto.PageRequestDTO;
import kr.co.abandog.dto.PageResultDTO;
import kr.co.abandog.entity.AbandogAdoptReview;
import kr.co.abandog.entity.Member;

public interface AbandogAdoptReviewService {
	
	//이 메서드를 클래스에서 구현해도 되지만(오버라이딩 or private 형식으로)
	//클래스에는 비즈니스 로직을 구현하는 걸 원칙으로 한다면 여기에 두는 게 나음
	//아니면 아예 별도의 클래스에 static 메서드로 만들어 둬도 되는데 이러한 경우 클래스 이름에 Wrapper를 붙이는 게 원칙
	default AbandogAdoptReview dtoToEntity(AbandogAdoptReviewDTO dto) {
		
		
		Member member = Member.builder().member_email(dto.getMember_email()).build();
		
		AbandogAdoptReview review = AbandogAdoptReview.builder().review_num(dto.getReview_num())
																.review_title(dto.getReview_title())
																.review_content(dto.getReview_content())
																.member(member)
																.inst_dtm(dto.getInst_dtm())
																.updt_dtm(dto.getUpdt_dtm())
																.build();
		return review;
	}
	
	default AbandogAdoptReviewDTO entityToDTO(AbandogAdoptReview review, Member member) {
		AbandogAdoptReviewDTO dto = AbandogAdoptReviewDTO.builder()
														 .review_num(review.getReview_num())
														 .review_title(review.getReview_title())
														 .review_content(review.getReview_content())
														 .member_email(member.getMember_email())
														 .member_name(member.getMember_name())
														 .inst_dtm(review.getInst_dtm())
														 .updt_dtm(review.getUpdt_dtm())
														 .build();
		
		return dto;
	}
	
	//목록 보기 요청
	public PageResultDTO<AbandogAdoptReviewDTO, Object[]> getList(PageRequestDTO dto);
	
	//상세 보기 요청
	public AbandogAdoptReviewDTO get(Integer review_num);
	
	//게시물 등록
	public Integer reviewRegister(AbandogAdoptReviewDTO dto);
	
	//게시물 수정
	public void reviewModify(AbandogAdoptReviewDTO dto);
	
	//게시물 삭제
	public void reviewRemove(Integer review_num);
	
}
