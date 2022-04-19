package kr.co.abandog.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.co.abandog.dto.MemberDTO;
import kr.co.abandog.entity.Member;
import kr.co.abandog.entity.MemberRole;
import kr.co.abandog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class OAuthMemberService extends DefaultOAuth2UserService{
	
	private final MemberRepository memberRepository;
	
	//비밀번호 암호화를 위한 인스턴스
	private final PasswordEncoder passwordEncoder;
	
	private final HttpServletRequest request;
	
	private Member saveSocialMember(String email) {
		
		//DB에 회원 정보가 있는지 확인
		Optional<Member> result = memberRepository.findByEmail(email);
		
		if(result.isPresent()) {
			return result.get();
		}
		
		//DB에 없으면 추가
		Member member = Member.builder().member_email(email)
										.member_pw(passwordEncoder.encode("1111"))
										.member_name(email)
										.fromSocial(true)
										.admin_yn("N")
										.build();
		
		member.addMemberRole(MemberRole.USER);
		memberRepository.save(member);
		return member;
	}
	
	private String getIP() {
		String ip = request.getHeader("X-Forwarded-For");
       if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        
        return ip;
	}
	
	//oauth를 이용해서 로그인 했을 때 호출되는 메서드
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		String clientName = userRequest.getClientRegistration().getClientName();
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		//email을 가져오기
		String email = null;
		
		if(clientName.trim().toLowerCase().indexOf("google") >= 0) {
			email = oAuth2User.getAttribute("email");
		}
		
		Date today = new Date();
    	SimpleDateFormat  date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
    	
		log.info("[소셜 로그인 시간: "+date.format(today)+" "
					+ "| 소셜 로그인 IP: "+getIP()+" "
					+ "| 소셜 로그인 사용자:"+ email+"]");
		
		//DB 저장
		Member member = saveSocialMember(email);
		MemberDTO memberDTO = new MemberDTO(member.getMember_email(),
										    member.getMember_pw(),
										    member.getRoleSet()
										          .stream()
										          .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
										          .collect(Collectors.toList()),
										    oAuth2User.getAttributes()
										    );
		
		return memberDTO;
	}
}
