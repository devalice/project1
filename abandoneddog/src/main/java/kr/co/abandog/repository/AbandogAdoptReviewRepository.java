package kr.co.abandog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.abandog.entity.AbandogAdoptReview;

public interface AbandogAdoptReviewRepository extends JpaRepository<AbandogAdoptReview, Long>, SearchAdoptReviewRepository {

}
