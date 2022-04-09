package kr.co.abandog.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity {
	
	@CreatedDate
	@Column(name = "inst_dtm", updatable=false, nullable= false)
	private LocalDateTime inst_dtm;
	
	@LastModifiedDate
	@Column(name="updt_dtm")
	private LocalDateTime updt_dtm;
	
	@Column(name="del_dtm")
	private LocalDateTime del_dtm;
}

