package com.shangtang.controller;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FaceReports")
@XmlAccessorType(XmlAccessType.FIELD)
public class FaceReports {

	@XmlElement(name = "FaceReport")
	private List<FaceReport> faceReports;

	public List<FaceReport> getFaceReports() {
		return faceReports;
	}

	public void setFaceReports(List<FaceReport> faceReports) {
		this.faceReports = faceReports;
	}

	@Override
	public String toString() {
		return "FaceReports [faceReports=" + faceReports + "]";
	}

}