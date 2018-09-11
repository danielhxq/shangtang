package com.shangtang.controller;

import java.util.Date;

import org.bson.BsonBinary;
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
			entity.setTrace_type(fr.getTrace_type());
			entity.setImage(new BsonBinary(fr.getImage().getBytes()));
			entity.setCreateTime(new Date());
			entity.setLastModifiedTime(new Date());
			entities.getEntities().add(entity);
		}
		return entities;
	}

}