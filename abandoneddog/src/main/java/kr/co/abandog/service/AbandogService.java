package kr.co.abandog.service;

import java.util.List;

import kr.co.abandog.dto.AbandogDTO;
import kr.co.abandog.dto.AbandogImgDTO;
import kr.co.abandog.entity.Abandog;
import kr.co.abandog.entity.AbandogImg;
import kr.co.abandog.entity.AbandogStateCD;
import kr.co.abandog.entity.AbandogTypeCD;
import kr.co.abandog.entity.Member;

public interface AbandogService {
	
	//호출 가능한 총 갯수
	public Long abandogCnt(String json);
	
	//서울시 유기견 데이터 DB 저장
	public String abandogput(String json);
	
	//서울시 유기견 이미지 DB 저장
	public String abandogImgput(String json);
	
	
	default Abandog dtoToEntity(AbandogDTO dto) {
		Member member = Member.builder()
							  .member_email(dto.getMember_email())
							  .build();
		
		AbandogStateCD abandogStateCD = AbandogStateCD.builder()
									   .state_cd(dto.getState_cd())
									   .build();
		
		AbandogTypeCD abandogTypeCD = AbandogTypeCD.builder()
												   .type_cd(dto.getType_cd())
												   .build();
		
		Abandog abandog = Abandog.builder()
								 .abandog_name(dto.getAbandog_name())
								 .abandog_age(dto.getAbandog_age())
								 .aban_dog_gender(dto.getAban_dog_gender())
								 .abandog_date(dto.getAbandog_date())
								 .abandog_location(dto.getAbandog_location())
								 .abandog_guardian(member.getMember_name())
								 .animal_no(dto.getAnimal_no())
								 .member(member)
								 .stateCD(abandogStateCD)
								 .typeCD(abandogTypeCD)
								 .build();
		
		return abandog;
	}

	
	default AbandogDTO entityToDTO(Abandog abandog) {
		AbandogDTO abandogDTO = AbandogDTO.builder().abandog_id(abandog.getAbandog_id())
												    .abandog_name(abandog.getAbandog_name())
												    .abandog_age(abandog.getAbandog_age())
												    .abandog_date(abandog.getAbandog_date())
												    .animal_no(abandog.getAnimal_no())
												    .build();
		return abandogDTO;
	}
	
	default AbandogImgDTO ImgentityToDTO(AbandogImg abandogImg, Abandog abandog) {
		AbandogImgDTO abandogImgDTO = AbandogImgDTO.builder().abandog_img(abandogImg.getAbandog_img())
														     .animal_no(abandog.getAnimal_no())
														     .photonum(abandogImg.getPhotonum())
														     .phototype(abandogImg.getPhototype())
														     .photoURL(abandogImg.getPhotoURL())
														     .build();
				
		return abandogImgDTO;
	}
	
	
	//유기견 리스트 가져오기
	public List<Object []> getList();
	

}
