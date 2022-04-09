package kr.co.abandog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogImgDTO {
	
	private Integer abandog_img;
	private String animal_no;
	private String phototype;
	private Integer photonum;
	private String photoURL;

}
