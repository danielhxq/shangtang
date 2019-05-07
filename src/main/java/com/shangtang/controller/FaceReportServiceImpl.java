package com.shangtang.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@WebService(targetNamespace = "http://controller.shangtang.com", endpointInterface = "com.shangtang.controller.FaceReportService")
public class FaceReportServiceImpl implements FaceReportService {

//	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	@Qualifier("faceReportNoImageRepository")
	private FaceReportNoImageRepository faceReportNoImageRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<SapFaceReport> getFaceReportEntitiesByPage(String group_id, Date startDay, Date endDay,
			Integer pageNumber) throws Exception {
		long start = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = sdf.format(startDay);
		Date today = null;
		try {
			today = sdf.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		dateTime = sdf1.format(endDay);
		Date tomorrow = null;
		try {
			tomorrow = sdf1.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(tomorrow);
		cal.add(Calendar.DATE, 1);
		tomorrow = cal.getTime();
//		DBObject query = new BasicDBObject();
//		query.put("timestampTime", new BasicDBObject("$gte", today).append("$lt", tomorrow));
		FaceReportPageable pageable = new FaceReportPageable();
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.ASC, "timestampTime"));
		pageable.setSort(new Sort(orders));
		pageable.setPagenumber(pageNumber);
		Page<SapFaceReport> p = faceReportNoImageRepository.query(today, tomorrow, group_id, pageable);
		if (p == null) {
			return new LinkedList<SapFaceReport>();
		}
		System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath());
		System.out.println("Time:" + (System.currentTimeMillis() - start));
		return p.getContent();
	}

	@Override
	public FaceReportStatistic getFaceReportStatistics(String group_id, Date startDay, Date endDay) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = sdf.format(startDay);
		Date today = null;
		try {
			today = sdf.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		dateTime = sdf1.format(endDay);
		Date tomorrow = null;
		try {
			tomorrow = sdf1.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(tomorrow);
		cal.add(Calendar.DATE, 1);
		tomorrow = cal.getTime();
		Query query = new Query();
		Criteria c1 = new Criteria();
		query.addCriteria(c1.andOperator(Criteria.where("timestampTime").gte(today).lt(tomorrow),
				Criteria.where("group_id").is(group_id)));
//		DBObject query = new BasicDBObject();
//		query.put("timestampTime", new BasicDBObject("$gte", today).append("$lt", tomorrow));
		long count = mongoTemplate.count(query, FaceReportNoImage.class);

		Query queryOne = new Query();
		Criteria c = new Criteria();

		queryOne.addCriteria(c.andOperator(Criteria.where("timestampTime").gte(today).lt(tomorrow),
				Criteria.where("group_id").is(group_id), Criteria.where("trace_type").is("0")));

		long countNewClient = mongoTemplate.count(queryOne, FaceReportNoImage.class);

		FaceReportStatistic frs = new FaceReportStatistic();

		frs.setTotalClient(count);
		frs.setNewClient(countNewClient);
		frs.setOldClient(count - countNewClient);

		return frs;
	}
}
