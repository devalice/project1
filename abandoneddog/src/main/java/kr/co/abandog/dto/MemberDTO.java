package kr.co.abandog.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
public class MemberDTO extends User implements OAuth2User{
	private String member_email;
	private String member_pw;
	private Boolean fromSocial;
	
	//속성 값을 읽어오기 위한 Map
	private Map<String, Object> attr;
	
	public MemberDTO(String member_email, 
					 String member_pw, 
					 Collection<? extends GrantedAuthority> authorities) {
		
		super(member_email, member_pw, authorities);
		this.member_email = member_email;
		this.member_pw = member_pw;
	}
	
	public MemberDTO(String member_email, 
				  	 String member_pw, 
				  	 Collection<? extends GrantedAuthority> authorities,
				  	 Map<String, Object> attr) {

		super(member_email, member_pw, authorities);
		this.member_email = member_email;
		this.member_pw = member_pw;
		this.attr = attr;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attr;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
