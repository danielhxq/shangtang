package com.shangtang.controller;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.bson.types.Binary;
import org.springframework.core.convert.converter.Converter;

public class FaceReportsConverter implements Converter<FaceReports, FaceReportEntities> {

	@Override
	public FaceReportEntities convert(FaceReports source) {
		if (source == null) {
			return new FaceReportEntities();
		}
		FaceReportEntities entities = new FaceReportEntities();
		for (FaceReport fr : source.getFaceReports()) {
			FaceReportEntity entity = new FaceReportEntity();
			entity.setRequest_id(fr.getRequest_id());
			entity.setGroup_id(fr.getGroup_id());
			entity.setPerson_id(fr.getPerson_id());
			entity.setDevice_id(fr.getDevice_id());
			entity.setCamera_id(fr.getCamera_id());
			entity.setCamera_name(fr.getCamera_name());
			entity.setTimestamp(fr.getTimestamp());
			entity.setTimestampTime(new Date(Long.parseLong(fr.getTimestamp()) * 1000));
			entity.setTrace_type(fr.getTrace_type());
			byte[] image = fr.getImage().getBytes();
			entity.setBase64Image(new Binary(image));
			entity.setBinaryImage(new Binary(Base64.decodeBase64(image)));
			Date now = new Date();
			entity.setCreateTime(now);
			entity.setLastModifiedTime(now);
			entities.getEntities().add(entity);
		}
		return entities;
	}

}
