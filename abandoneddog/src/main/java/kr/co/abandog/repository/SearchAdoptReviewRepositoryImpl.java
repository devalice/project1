package kr.co.abandog.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import kr.co.abandog.entity.AbandogAdoptReview;
import kr.co.abandog.entity.QAbandogAdoptReview;
import kr.co.abandog.entity.QMember;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchAdoptReviewRepositoryImpl extends QuerydslRepositorySupport implements SearchAdoptReviewRepository{

	public SearchAdoptReviewRepositoryImpl() {
		super(AbandogAdoptReview.class);
	}

	
	@Override
	public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
		log.info("searchPage 메서드 호출");

		//객체 생성
		QAbandogAdoptReview review = QAbandogAdoptReview.abandogAdoptReview;
		QMember member = QMember.member;
		
		JPQLQuery<AbandogAdoptReview> jpqlQuery = from(review);
		jpqlQuery.join(member).on(review.member.eq(member));
		
		//후기게시판의 고객 이메일
		JPQLQuery <Tuple> tuple = jpqlQuery.select(review, member);
		
		//동적 쿼리 생성
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		log.info("review_num: "+review.review_num);
		
		//조건 생성
		BooleanExpression expression = review.review_num.gt(0);
		//조건 추가
		booleanBuilder.and(expression);
		
		
		log.info("비교 시작");
		//t: title, c: content, w: writer
		if(type != null) {
			String [] typeAr = type.split("");
			BooleanBuilder conditionBuilder = new BooleanBuilder();
			
			for(String t:typeAr) {
				switch(t) {
				case "t":
					conditionBuilder.or(review.review_title.contains(keyword));
					break;
				case "c":
					conditionBuilder.or(review.review_content.contains(keyword));
					break;
				case "w":
					conditionBuilder.or(member.member_email.contains(keyword));
					break;					
				}
			}
			
			booleanBuilder.and(conditionBuilder);
		}
		
		//조건 검색 추가
		tuple.where(booleanBuilder);
		
		log.info("정렬 시작");
		//정렬 추가
		Sort sort = pageable.getSort();
		sort.stream().forEach(order -> {
			//정렬 방향 찾아오기 오름차순인지 내림차순인지
			Order direction = order.isAscending() ? Order.ASC : Order.DESC;
			String prop = order.getProperty();
			
			log.info("prop: "+prop);
			
			PathBuilder orderByExpression = new PathBuilder(AbandogAdoptReview.class, "abandogAdoptReview");
			tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));					
		});
		
		
		log.info("그룹화");
		//그룹화
		tuple.groupBy(review);
		
		//페이지 처리
		tuple.offset(pageable.getOffset()); //페이지 번호
		tuple.limit(pageable.getPageSize()); //페이지 사이즈
		
		log.info("데이터 가져오기");
		//데이터 가져오기
		List<Tuple> result = tuple.fetch();
	
		log.info("리턴");
		return new PageImpl<Object []>(result.stream()
											 .map(t -> t.toArray())
											 .collect(Collectors.toList())
									  , pageable
									  , tuple.fetchCount());
									  
	}

}
