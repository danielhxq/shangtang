package com.shangtang.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@WebService(targetNamespace = "http://controller.shangtang.com", endpointInterface = "com.shangtang.controller.FaceReportService")
public class FaceReportServiceImpl implements FaceReportService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	@Qualifier("faceReportRepository")
	private FaceReportRepository faceReportRepository;

	@Override
	public List<SapFaceReport> getFaceReportEntities(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = sdf.format(date);
		Date a = null;
		try {
			a = sdf.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.add(Calendar.DATE, 1);
		Date tomorrow = cal.getTime();
		DBObject query = new BasicDBObject();
		query.put("createTime", new BasicDBObject("$gte", a).append("$lt", tomorrow));
		DBCursor dbCursor = mongoTemplate.getCollection("FaceReport-test").find(query);
		List<SapFaceReport> list = new LinkedList<SapFaceReport>();
		while (dbCursor.hasNext()) {
			DBObject object = dbCursor.next();
			SapFaceReport te = new SapFaceReport();
			te.setDevice_id(object.get("device_id").toString());
			te.setCamera_id(object.get("camera_id").toString());
			te.setCamera_name(object.get("camera_name").toString());
			te.setGroup_id(object.get("group_id").toString());
			te.setImage(new String((byte[]) object.get("image")));
			te.setPerson_id(object.get("person_id").toString());
			te.setTimestamp(object.get("timestamp").toString());
			te.setTrace_type(object.get("trace_type").toString());
			te.setRequest_id(object.get("request_id").toString());
			list.add(te);
		}
		return list;
	}

}
