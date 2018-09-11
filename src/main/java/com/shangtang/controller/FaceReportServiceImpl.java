package com.shangtang.controller;

import java.util.Date;

import javax.jws.WebService;

@WebService(targetNamespace = "http://cxf.cgj", endpointInterface = "com.shangtang.controller.FaceReportService")
public class FaceReportServiceImpl implements FaceReportService {

	@Override
	public FaceReportEntities getFaceReportEntities(Date date) {
		return new FaceReportEntities();
	}

}
