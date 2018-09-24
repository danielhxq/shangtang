package com.shangtang.controller;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Configuration
@Repository("faceReportRepository")
public interface FaceReportRepository extends MongoRepository<FaceReportEntity, String> {

	@Query(value = "{'createTime':{$gte:?0,$lt:?1}}")
	public Page<FaceReportEntity> query(Date beginTime, Date endTime, Pageable pageable);
}
