package com.shangtang.controller;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace="http://cxf.cgj")
public interface FaceReportService {

	@WebMethod(operationName="getFaceReportEntities")
	FaceReportEntities getFaceReportEntities(@WebParam(name="date") Date date);
}
