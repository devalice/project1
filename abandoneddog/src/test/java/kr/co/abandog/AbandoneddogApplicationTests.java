package kr.co.abandog;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.abandog.entity.Abandog;
import kr.co.abandog.entity.AbandogStateCD;
import kr.co.abandog.entity.AbandogTypeCD;
import kr.co.abandog.entity.Member;
import kr.co.abandog.repository.MemberRepository;

@SpringBootTest
class AbandoneddogApplicationTests {
	
	@Autowired
	private MemberRepository memberRepository;
	
}
