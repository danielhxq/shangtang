package com.shangtang.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class STImageController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private GenericConversionService conversionService;

	@Autowired
	@Qualifier("faceReportRepository")
	private FaceReportRepository faceReportRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/facereports", consumes = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseContent> facereports(@RequestBody final FaceReports entities) {
		FaceReportEntities e = conversionService.convert(entities, FaceReportEntities.class);
		mongoTemplate.insertAll(e.getEntities());
		return ResponseEntity.ok(new ResponseContent("200", ""));
	}

	@GetMapping("image/{id}")
	@ResponseBody
	public ResponseEntity<Object> showImage(@PathVariable String id) throws UnsupportedEncodingException {
		FaceReportEntity entity = faceReportRepository.findOne(id);

		if (entity != null) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").header("Connection", "close")
					.body(entity.getBinaryImage().getData());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not found");
		}
	}

	@GetMapping("download/image/{id}")
	@ResponseBody
	public ResponseEntity<Object> downloadImage(@PathVariable String id) throws UnsupportedEncodingException {
		FaceReportEntity entity = faceReportRepository.findOne(id);

		if (entity != null) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + id + ".jpeg")
					.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream").header("Connection", "close")
					.body(entity.getBinaryImage().getData());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not found");
		}
	}
}
