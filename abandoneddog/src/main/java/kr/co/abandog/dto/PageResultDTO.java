package kr.co.abandog.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDTO<DTO, EN> {
	
	private List<DTO> dtoList;
	
	private int totalPage, start, end;
	private int page; //현재 페이지
	private int size; //페이지당 출력 갯수
	
	private boolean prev, next;
	
	private List<Integer> pageList;
	
	//생성자
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		//Entity로 넘어온 결과를 DTO로 변환
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		
		//전체 페이지 개수 구하기
		totalPage = result.getTotalPages();
		
		//페이지 번호 목록 구하기
		makePageList(result.getPageable());
	}
	
	private void makePageList(Pageable pageable) {
		//현재 페이지
		this.page = pageable.getPageNumber() + 1;
		
		//페이지 당 출력되는 데이터 개수
		this.size = pageable.getPageSize();
		
		//시작 페이지 번호와 종료 페이지 번호 계산
		int tempEnd = (int)(Math.ceil(page / 10.0)) * 10;
		start = tempEnd -9;
		prev = start > 1;
		
		end = totalPage > tempEnd ? tempEnd : totalPage;
		next = totalPage > tempEnd;
		
		pageList = new ArrayList<>();
		for(int i=start; i <= end; i=i+1) {
			pageList.add(i);
		}
	}

}
