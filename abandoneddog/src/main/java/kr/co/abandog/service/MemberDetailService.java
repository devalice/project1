package kr.co.abandog.service;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.abandog.dto.MemberDTO;
import kr.co.abandog.entity.Member;
import kr.co.abandog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("loadByUserName" + username);
		
		Member member = memberRepository.findByEmail(username).get();
		
		MemberDTO memberDTO = new MemberDTO(member.getMember_email(),
											member.getMember_pw(),
											member.getRoleSet()
												  .stream()
												  .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
												  .collect(Collectors.toSet()));
		
		memberDTO.setMember_email(memberDTO.getMember_email());
		
		return memberDTO;
	}

}
