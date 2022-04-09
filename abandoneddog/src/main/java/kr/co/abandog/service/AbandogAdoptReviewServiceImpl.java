package kr.co.abandog.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.co.abandog.dto.AbandogAdoptReviewDTO;
import kr.co.abandog.dto.PageRequestDTO;
import kr.co.abandog.dto.PageResultDTO;
import kr.co.abandog.entity.AbandogAdoptReview;
import kr.co.abandog.entity.Member;
import kr.co.abandog.repository.AbandogAdoptReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class AbandogAdoptReviewServiceImpl implements AbandogAdoptReviewService {
	private final AbandogAdoptReviewRepository adopReviewRepository;
	
	//목록 보기 요청
	@Override
	public PageResultDTO<AbandogAdoptReviewDTO, Object[]> getList(PageRequestDTO dto) {
		log.info("getList 호출");
		Page<Object []> result = adopReviewRepository.searchPage(dto.getType(), 
																 dto.getKeyword(), 
																 dto.getPageable(Sort.by("review_num").descending()));
		
		Function<Object[], AbandogAdoptReviewDTO> fn = (en -> entityToDTO((AbandogAdoptReview)en[0],
																		  (Member)en[1]));
		
		
		return new PageResultDTO<>(result, fn);
	}
}
