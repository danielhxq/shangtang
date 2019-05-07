package com.shangtang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ReadingConverter
public class FaceReportNoImageConverter implements Converter<FaceReportNoImage, SapFaceReport> {

	@Override
	public SapFaceReport convert(FaceReportNoImage e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		SapFaceReport sfr = new SapFaceReport();
		sfr.setDevice_id(e.getDevice_id());
		sfr.setCamera_id(e.getCamera_id());
		sfr.setCamera_name(e.getCamera_name());
		sfr.setGroup_id(e.getGroup_id());
		sfr.setPerson_id(e.getPerson_id());
		sfr.setTimestamp(e.getTimestamp());
		sfr.setTrace_type(e.getTrace_type());
		sfr.setRequest_id(e.getRequest_id());
		sfr.setImageDownloadUrl((String) request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + "/st/image/" + e.getId());
		sfr.setCreateTime(e.getCreateTime());
		return sfr;
	}

}
