package kr.co.abandog;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.co.abandog.dto.AbandogAdoptReviewDTO;
import kr.co.abandog.dto.PageRequestDTO;
import kr.co.abandog.dto.PageResultDTO;
import kr.co.abandog.entity.AbandogAdoptReview;
import kr.co.abandog.service.AbandogAdoptReviewService;

@WebAppConfiguration
@SpringBootTest
public class BoardTest {
	
	@Autowired
	private AbandogAdoptReviewService reviewService;
	
	@Test
	public void Test() {
		
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		pageRequestDTO.setPage(1);
		pageRequestDTO.setSize(10);
		
		PageResultDTO<AbandogAdoptReviewDTO, Object[]> dto = reviewService.getList(pageRequestDTO);
		System.out.println(dto.getPage());
		System.out.println(dto.getSize());
		
		
		List<AbandogAdoptReviewDTO> list = dto.getDtoList();
		System.out.println(list.get(0));
		
		
	}

}
