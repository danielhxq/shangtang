package com.shangtang.controller;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://controller.shangtang.com")
public interface FaceReportService {

//	@WebMethod(operationName = "getFaceReportEntities")
//	List<SapFaceReport> getFaceReportEntities(@WebParam(name = "date") Date date) throws Exception;

	@WebMethod(operationName = "getFaceReportEntitiess")
	List<SapFaceReport> getFaceReportEntitiess(@WebParam(name = "date") Date date,
			@WebParam(name = "pageNumber") Integer pageNumber) throws Exception;
}
