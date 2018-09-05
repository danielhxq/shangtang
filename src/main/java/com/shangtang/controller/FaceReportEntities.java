package com.shangtang.controller;

import java.util.LinkedList;
import java.util.List;

public class FaceReportEntities {

	private List<FaceReportEntity> entities;

	public List<FaceReportEntity> getEntities() {
		if (entities == null) {
			entities = new LinkedList<FaceReportEntity>();
		}
		return entities;
	}

	public void setEntities(List<FaceReportEntity> entities) {
		this.entities = entities;
	}

	@Override
	public String toString() {
		return "FaceReportEntities [entities=" + entities + "]";
	}

}
