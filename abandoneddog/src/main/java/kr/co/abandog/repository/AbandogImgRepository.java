package kr.co.abandog.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.abandog.entity.AbandogImg;

public interface AbandogImgRepository extends JpaRepository<AbandogImg, Long>{
	
	@Modifying
	@Query(value = "insert into abandog_img (animal_no, phototype, photonum, photoURL, inst_dtm) "
					+ "select a, b, c, d, now() "
					+ "from ( select :animal_no as a, :phototype as b, :photonum as c, :photoURL as d from dual) t1 "
					+ "where not exists (select 1 from abandog_img t2 where t1.d = t2.photoURL)", 
					nativeQuery = true)
	@Transactional
	public void mergeAbandogImg(@Param("animal_no") String animal_no, 
							 	@Param("phototype") String phototype, 
							 	@Param("photonum") Integer photonum, 
							 	@Param("photoURL") String photoURL);


}
