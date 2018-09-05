package com.shangtang.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Configuration
@Repository("faceReportRepository")
public interface FaceReportRepository extends CrudRepository<FaceReportEntity, String> {

}
