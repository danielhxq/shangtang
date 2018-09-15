package com.shangtang.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.bson.BsonDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DuplicateKeyException;

@RestController
//@RequestMapping(value = "/st")
//@ComponentScan(basePackages = { "com.shangtang.controller" })
public class STImageController {

//	public static void main(String... args) {
//		SpringApplication.run(STImageController.class, args);
//	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private GenericConversionService conversionService;

	@Autowired
	@Qualifier("faceReportRepository")
	private FaceReportRepository faceReportRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/facereports", consumes = MediaType.APPLICATION_XML_VALUE)
//	@ResponseBody
	public void facereports(@RequestBody final FaceReports entities) {
		// faceReportRepository.save();
		FaceReportEntities e = conversionService.convert(entities, FaceReportEntities.class);
//		System.out.println(entities.getFaceReports().toString());
//		for(FaceReportEntity entity : e.getEntities()) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("group_id").is(entity.getGroup_id()));
//		query.addCriteria(Criteria.where("person_id").is(entity.getPerson_id()));
//		Update update = new Update();
////		update.push("entity", entity);
//		update.push("device_id", entity.getDevice_id());
//		update.push("camera_id", entity.getCamera_id());
//		update.push("camera_name", entity.getCamera_name());
//		update.push("timestamp", entity.getTimestamp());
//		update.push("trace_type", entity.getTrace_type());
//		update.push("image", entity.getImage());
//		update.push("lastModifiedTime", );
//			mongoTemplate.upsert(query, update, FaceReportEntity.class);
//
//				faceReportRepository.save(e.getEntities());
		mongoTemplate.insertAll(e.getEntities());

//		}
		// mongoTemplate.upse
//		faceReportRepository.save(e.getEntities());
//			return "NULL";
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public String handleException(DuplicateKeyException ex) {
//		logger.error("SQLException Occured:: URL="+ex.getErrorCode());
		String str = ex.getMessage();
		System.out.println("AAA=" + str);
		str = str.substring(str.indexOf("key:") + 4, str.indexOf("}'") + 1);
		System.out.println(str);
		String[] ss = str.split(":");
		final StringBuilder sb = new StringBuilder();
		if (ss != null && ss.length == 4) {
			sb.append(ss[0]).append("group_id:").append(ss[1]).append("person_id:").append(ss[2]).append("timestamp:")
					.append(ss[3]);
		} else {
			return str;
		}
		return sb.toString();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/facereports")
	@ResponseBody
	public List<FaceReport> test() throws Exception {
		DBObject query1 = new BasicDBObject();
		List<String> l = new ArrayList<>();
		l.add("35498");
		System.out.println("ssss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
		// Date startDate = dateFormat.parse(date);
		final Date to = dateFormat.parse("2018-09-04 09:12:29");
		final Date from = dateFormat.parse("2018-09-11 09:12:29");
		BsonDateTime t = new BsonDateTime(1536130L);
		BsonDateTime lt = new BsonDateTime(1536130036554L);
		// query1.put("device_id", new BasicDBObject("$in", l));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		query1.put("createTime", new BasicDBObject("$gte", to));
//		DBCursor dbCursor = mongoTemplate.getCollection("FaceReport-test").find(query1);
		List<FaceReport> list = new LinkedList<FaceReport>();
//		int i = 0;
//		while (dbCursor.hasNext()) {
//			DBObject object = dbCursor.next();
//			FaceReport te = new FaceReport();
//			te.setDevice_id(object.get("device_id").toString());
//			// te.setTime((Date) object.get("time"));
//			list.add(te);
//			i++;
//			if (i == 40000) {
//				break;
//			}
//		}
//		System.out.println("ssss1");
//		// for (FaceReport s : list) {
//		// System.out.println(s.getDevice_id());
//		// }
//		System.out.println(list.size());
		// return product.getId();
		return list;
	}
}
