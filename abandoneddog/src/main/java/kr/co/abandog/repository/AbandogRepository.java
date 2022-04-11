package kr.co.abandog.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.abandog.entity.Abandog;

public interface AbandogRepository extends JpaRepository<Abandog, Long>{
	
	@Modifying
	@Query(value = "insert into abandog(inst_dtm, updt_dtm, "
					+ "aban_dog_gender, abandog_age, abandog_date, "
					+ "abandog_guardian, abandog_name, animal_no, member_email,"
					+ "state_cd, type_cd) "
					+ "values(now(), NULL, :aban_dog_gender, :abandog_age, :abandog_date, '서울시보호소', "
					+ 		 ":abandog_name, :animal_no, :member_email, "
					+        ":state_cd, :type_cd) as new "
					+ "on duplicate key update updt_dtm = now(), "
					+ 						  "state_cd = new.state_cd, "
					+ 						  "type_cd = new.type_cd",
			nativeQuery = true)
	@Transactional
	public void mergeAbandog(@Param("aban_dog_gender") String aban_dog_gender, 
							 @Param("abandog_age") Integer abandog_age, 
							 @Param("abandog_date") String abandog_date, 
							 @Param("abandog_name") String abandog_name, 
							 @Param("animal_no") String animal_no, 
							 @Param("member_email") String member_email,
							 @Param("state_cd") String state_cd,
							 @Param("type_cd") String type_cd);
	
	
	//서울시 보호 유기견과 이미지(한개만) 가져오기
	@Query("select a, ai "
			+ "from Abandog a join AbandogImg ai "
			+ "on ai.abandog = a.animal_no "
			+ "where a.animal_no is not null "
			+ "group by a.animal_no")
	public List<Object[]> getAbandogList();
	
	//유기견 아이디로 찾기
	@Query("select d from Abandog d where d.animal_no = :animal_no")
	public Abandog getAbandog(@Param("animal_no") String animal_no);
	

}
