package kr.co.abandog.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
public class MemberDTO extends User{
	private String member_email;
	
	public MemberDTO(String member_email, 
					 String password, 
					 Collection<? extends GrantedAuthority> authorities) {
		
		super(member_email, password, authorities);
		
		this.member_email = member_email;
	}

}
