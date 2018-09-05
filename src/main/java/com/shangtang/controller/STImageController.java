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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/st")
@ComponentScan(basePackages = { "com.shangtang.controller" })
public class STImageController {

	public static void main(String... args) {
		SpringApplication.run(STImageController.class, args);
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private GenericConversionService conversionService;

	@Autowired
	@Qualifier("faceReportRepository")
	private FaceReportRepository faceReportRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/facereports", consumes = MediaType.APPLICATION_XML_VALUE)
	public void facereports(@RequestBody final FaceReports entities) {
		// faceReportRepository.save();
		FaceReportEntities e = conversionService.convert(entities, FaceReportEntities.class);
//		System.out.println(entities.getFaceReports().toString());
		faceReportRepository.save(e.getEntities());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/idinfo")
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
		DBCursor dbCursor = mongoTemplate.getCollection("FaceReport-test").find(query1);
		List<FaceReport> list = new LinkedList<FaceReport>();
		int i = 0;
		while (dbCursor.hasNext()) {
			DBObject object = dbCursor.next();
			FaceReport te = new FaceReport();
			te.setDevice_id(object.get("device_id").toString());
			// te.setTime((Date) object.get("time"));
			list.add(te);
			i++;
			if (i == 40000) {
				break;
			}
		}
		System.out.println("ssss1");
		// for (FaceReport s : list) {
		// System.out.println(s.getDevice_id());
		// }
		System.out.println(list.size());
		// return product.getId();
		return list;
	}
}
