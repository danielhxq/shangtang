package com.shangtang.controller;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Configuration
@Repository("faceReportNoImageRepository")
public interface FaceReportNoImageRepository extends MongoRepository<FaceReportNoImage, SapFaceReport> {

	@Query(value = "{'timestampTime':{$gte:?0, $lt:?1}, 'group_id' : ?2}")
	public Page<SapFaceReport> query(Date beginTime, Date endTime, String group_id, Pageable pageable);
}
