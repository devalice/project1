package kr.co.abandog.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.abandog.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	@EntityGraph(attributePaths = {"roleSet"}, type=EntityGraph.EntityGraphType.LOAD)
	@Query("select m from Member m where m.member_email=:member_email")
	Optional<Member> findByEmail(@Param("member_email") String member_email);

}
