package kr.co.abandog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.abandog.dto.AbandogDTO;
import kr.co.abandog.dto.AbandogImgDTO;
import kr.co.abandog.entity.Abandog;
import kr.co.abandog.entity.AbandogImg;
import kr.co.abandog.entity.Member;
import kr.co.abandog.entity.MemberRole;
import kr.co.abandog.repository.AbandogImgRepository;
import kr.co.abandog.repository.AbandogRepository;
import kr.co.abandog.repository.AbandogStateCDRepository;
import kr.co.abandog.repository.AbandogTypeCDRepository;
import kr.co.abandog.repository.MemberRepository;

@SpringBootTest
public class RepositoryTest {
	@Autowired
	private AbandogRepository abandogRepository;
	
	@Autowired
	private AbandogImgRepository abandogImgRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AbandogStateCDRepository abandogStateCDRepository;
	
	@Autowired
	private AbandogTypeCDRepository abandogTypeCDRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AbandogDTO entityToDTO(Abandog abandog) {
		AbandogDTO abandogDTO = AbandogDTO.builder().abandog_id(abandog.getAbandog_id())
												    .abandog_name(abandog.getAbandog_name())
												    .abandog_age(abandog.getAbandog_age())
												    .abandog_date(abandog.getAbandog_date())
												    .build();
		return abandogDTO;
	}
	
	public AbandogImgDTO ImgentityToDTO(AbandogImg abandogImg, Abandog abandog) {
		AbandogImgDTO abandogImgDTO = AbandogImgDTO.builder().abandog_img(abandogImg.getAbandog_img())
														     .animal_no(abandog.getAnimal_no())
														     .photonum(abandogImg.getPhotonum())
														     .phototype(abandogImg.getPhototype())
														     .photoURL(abandogImg.getPhotoURL())
														     .build();
				
		return abandogImgDTO;
	}
	
	//@Test
	public void test1() {
		abandogRepository.mergeAbandog("W", 5, "2022-01-13", 
				   "까마", "1462.0", "openapi@naver.com", "P", "D");
	}
	
	//@Test
	public void test2() {
		
		Member member = Member.builder().member_email("gkstjfgml@naver.com")
										.admin_yn("N")
										.member_pw(passwordEncoder.encode("1234"))
										.build();
		
		member.addMemberRole(MemberRole.USER);
										
		memberRepository.save(member);
	}
	
	@Test
	public void test3() {
		List<Object []> abandog = abandogRepository.getAbandogList();
		
		System.out.println(abandog.size());
		
		List<Object []> arr = new ArrayList<>();
		/*
		for(Object [] ob : abandog) {
			obj[0] = new Object();
			obj[0] = entityToDTO((Abandog)ob[0]);
			//System.out.println(obj[0].toString());
			
			obj[1] = new Object();
			obj[1] = ImgentityToDTO((AbandogImg)ob[1], (Abandog)ob[0]);
			
			System.out.println(Arrays.toString(obj));
			
			arr.add(obj);
		}
		*/
		
		for(int i=0; i<abandog.size(); i++) {
			Object[] obj = new Object[abandog.size()];
			obj[0] = new Object();
			obj[0] = entityToDTO((Abandog)abandog.get(i)[0]);
			
			obj[1] = new Object();
			obj[1] = ImgentityToDTO((AbandogImg)abandog.get(i)[1], (Abandog)abandog.get(i)[0]);
			
			//System.out.println(Arrays.toString(obj));
			
			arr.add(i, obj);
			System.out.println(arr.get(i)[0]);
		}
		
		System.out.println("**********************");
				
		for(int i=0; i< 3; i++) {
			System.out.println(arr.get(i)[1]);
		}		
		
	}
	
	//@Test
	public void test4() {
		Abandog abandog = abandogRepository.getAbandog("295.0");
			System.out.println(abandog);
	}
	
	//@Test
	public void test5() {
		Abandog abandog = abandogRepository.getAbandog("295.0");
		abandogImgRepository.mergeAbandogImg(abandog.getAnimal_no(), "img", 0, "http");
	}
}
