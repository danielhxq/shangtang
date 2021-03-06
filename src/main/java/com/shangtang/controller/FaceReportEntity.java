package com.shangtang.controller;

import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = CollectionName.COLLECTION_NAME)
@CompoundIndexes({
		@CompoundIndex(name = "group_peron_timestamp_id", def = "{'group_id': 1, 'person_id': 1, 'timestamp': 1}", unique = true) })
public class FaceReportEntity {

	@Id
	private String id;

	private String request_id;

	private String group_id;

	private String person_id;

	private String device_id;

	private String camera_id;

	private String camera_name;

	private String timestamp;

	private String trace_type;

	private Binary base64Image;

	private Binary binaryImage;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createTime;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date lastModifiedTime;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Indexed(direction = IndexDirection.DESCENDING)
	private Date timestampTime;

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

	public Binary getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(Binary base64Image) {
		this.base64Image = base64Image;
	}

	public Binary getBinaryImage() {
		return binaryImage;
	}

	public void setBinaryImage(Binary binaryImage) {
		this.binaryImage = binaryImage;
	}

	public Date getTimestampTime() {
		return timestampTime;
	}

	public void setTimestampTime(Date timestampTime) {
		this.timestampTime = timestampTime;
	}

	@Override
	public String toString() {
		return "FaceReportEntity [id=" + id + ", request_id=" + request_id + ", group_id=" + group_id + ", person_id="
				+ person_id + ", device_id=" + device_id + ", camera_id=" + camera_id + ", camera_name=" + camera_name
				+ ", timestamp=" + timestamp + ", timestampTime=" + timestampTime + ", trace_type=" + trace_type
				+ ", base64Image=" + base64Image + ", binaryImage=" + binaryImage + ", createTime=" + createTime
				+ ", lastModifiedTime=" + lastModifiedTime + "]";
	}

}
