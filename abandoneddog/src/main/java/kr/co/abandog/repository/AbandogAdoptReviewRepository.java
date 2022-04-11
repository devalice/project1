package kr.co.abandog.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.abandog.entity.AbandogAdoptReview;

public interface AbandogAdoptReviewRepository extends JpaRepository<AbandogAdoptReview, Long>, SearchAdoptReviewRepository {
	
	//게시물 번호로 검색
	@Query(value = "SELECT r, m "
				   + "FROM AbandogAdoptReview r LEFT JOIN r.member m "
				   + "WHERE r.review_num = :review_num ",
			countQuery = "SELECT count(r) FROM AbandogAdoptReview r ")
	Object getReviewByReviewNum(@Param("review_num") Integer review_num);
	
	//게시물 삭제
	@Modifying
	@Transactional
	@Query("delete from AbandogAdoptReview r "
			+ "where r.review_num = :review_num ")
	public void deleteByReviewNum(@Param("review_num") Integer review_num);

}
