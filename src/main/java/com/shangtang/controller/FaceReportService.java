package com.shangtang.controller;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://controller.shangtang.com")
public interface FaceReportService {

	@WebMethod(operationName = "getFaceReportEntitiesByPage")
	List<SapFaceReport> getFaceReportEntitiesByPage(@WebParam(name = "group_id") String group_id,
			@WebParam(name = "startDay") Date startDay, @WebParam(name = "endDay") Date endDay,
			@WebParam(name = "pageNumber") Integer pageNumber) throws Exception;

	@WebMethod(operationName = "getFaceReportStatistics")
	FaceReportStatistic getFaceReportStatistics(@WebParam(name = "group_id") String group_id,
			@WebParam(name = "startDay") Date startDay, @WebParam(name = "endDay") Date endDay) throws Exception;
}
