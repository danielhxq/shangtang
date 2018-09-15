package com.shangtang.controller;

import java.io.Serializable;
import java.util.Date;

public class SapFaceReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6596547974040304903L;

	private String id;

	private String request_id;

	private String group_id;

	private String person_id;

	private String device_id;

	private String camera_id;

	private String camera_name;

	private String timestamp;

	private String trace_type;

	private String image;
	private Date createTime;
	private Date lastModifiedTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getCamera_id() {
		return camera_id;
	}

	public void setCamera_id(String camera_id) {
		this.camera_id = camera_id;
	}

	public String getCamera_name() {
		return camera_name;
	}

	public void setCamera_name(String camera_name) {
		this.camera_name = camera_name;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTrace_type() {
		return trace_type;
	}

	public void setTrace_type(String trace_type) {
		this.trace_type = trace_type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@Override
	public String toString() {
		return "SAPFaceReport [id=" + id + ", request_id=" + request_id + ", group_id=" + group_id + ", person_id="
				+ person_id + ", device_id=" + device_id + ", camera_id=" + camera_id + ", camera_name=" + camera_name
				+ ", timestamp=" + timestamp + ", trace_type=" + trace_type + ", image=" + image + ", createTime="
				+ createTime + ", lastModifiedTime=" + lastModifiedTime + "]";
	}

}
