package com.shangtang.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@WebService(targetNamespace = "http://controller.shangtang.com", endpointInterface = "com.shangtang.controller.FaceReportService")
public class FaceReportServiceImpl implements FaceReportService {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	@Qualifier("faceReportRepository")
	private FaceReportRepository faceReportRepository;

//	@Override
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

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		while (dbCursor.hasNext()) {
			DBObject object = dbCursor.next();
			SapFaceReport te = new SapFaceReport();
			te.setDevice_id((String) object.get("device_id"));
			te.setCamera_id((String) object.get("camera_id"));
			te.setCamera_name((String) object.get("camera_name"));
			te.setGroup_id((String) object.get("group_id"));
//			byte[] image = (byte[]) object.get("image");
//			if (image == null) {
//				te.setImage(null);
//			} else {
//				te.setImage(new String(image));
//			}
			te.setPerson_id((String) object.get("person_id"));
			te.setTimestamp((String) object.get("timestamp"));
			te.setTrace_type((String) object.get("trace_type"));
			te.setRequest_id((String) object.get("request_id"));
			te.setImageDownloadUrl((String) request.getScheme() + "://" + request.getServerName() + ":"
					+ request.getServerPort() + "/st/image/" + object.get("_id"));
			list.add(te);
		}
		return list;
	}

	@Override
	public List<SapFaceReport> getFaceReportEntitiess(Date date, Integer pageNumber) throws Exception {
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
		FaceReportPageable pageable = new FaceReportPageable();
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.ASC, "createTime"));
		Sort sort = new Sort(orders);
		pageable.setSort(sort);
		pageable.setPagenumber(pageNumber);
		Page<FaceReportEntity> p = faceReportRepository.query(a, tomorrow, pageable);
		List<SapFaceReport> faceReportEntities = null;
		if (p == null) {
			return new LinkedList<SapFaceReport>();
		}
		faceReportEntities = new LinkedList<SapFaceReport>();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		for (FaceReportEntity e : p.getContent()) {
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
			faceReportEntities.add(sfr);
		}
		return faceReportEntities;
	}

}
