package kr.co.abandog.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.abandog.entity.AbandogAdoptReviewFile;

public interface AbandogAdoptReviewFileRepository extends JpaRepository<AbandogAdoptReviewFile, Long>{
		//게시물 파일 검색
		@Query(value = "SELECT rf "
				       + "FROM AbandogAdoptReviewFile rf "
				       + "WHERE rf.fileKey.review.review_num = :review_num ")
		List<Object> getReviewByReviewNum(@Param("review_num") Integer review_num);
	
	
		//게시물 파일 삭제
		@Modifying
		@Transactional
		@Query("delete from AbandogAdoptReviewFile rf "
				+ "where rf.fileKey.review.review_num = :review_num ")
		public void deleteByReviewNum(@Param("review_num") Integer review_num);
	
}
