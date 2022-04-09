package kr.co.abandog.dto;

import java.util.Date;

import kr.co.abandog.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class AbandogAdoptReviewDTO {
	
	private Integer review_num;
	private String review_title;
	private String review_content;
	private String review_file;
	private Date review_datetime;
	
	//작성자 정보
	private String member_email;
	private String member_name;

}
