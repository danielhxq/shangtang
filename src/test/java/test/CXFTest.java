package test;

import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.shangtang.controller.FaceReportStatistic;
import com.shangtang.controller.SapFaceReport;

public class CXFTest {
	public static void main(String[] args) throws Exception {
		test3();
	}

	public static void test1() throws Exception {
		long start = System.currentTimeMillis();
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf.createClient("http://101.230.217.197:9090/soap/facereport?wsdl");
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		xgcal.setDay(11);
		Object[] objects = client.invoke("getFaceReportEntitiesByPage", "7540688D852E4F99AD6D154D9E989FB7", xgcal, 1);

		for (Object obj : objects) {
			List<SapFaceReport> list = (List<SapFaceReport>) obj;
			System.out.println(list.size());
			for (SapFaceReport e : list) {
//				byte[] encodeBase64 = Base64.decodeBase64(new String(report.getImage()));
//				
//				FileOutputStream out = new FileOutputStream("/Users/daniel/Desktop/ffe6.jpeg");
//				out.write(encodeBase64);
//				out.close();
//				System.out.println("*****" + new String(report.getImage()));
				System.out.println("*****" + e.getCamera_id());
				System.out.println("*****" + e.getPerson_id());
				System.out.println("*****" + e.getImageDownloadUrl());

				System.out.println("*****" + e.getDevice_id());
				System.out.println("*****" + e.getCamera_id());
				System.out.println("*****" + e.getCamera_name());
				System.out.println("*****" + e.getGroup_id());
				System.out.println("*****" + e.getPerson_id());
				System.out.println("*****" + e.getTimestamp());
				System.out.println("*****" + e.getTrace_type());
				System.out.println("*****" + e.getRequest_id());
			}
		}
	}

	public static void test2() throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf.createClient("http://localhost:9090/soap/facereport?wsdl");
		long start = System.currentTimeMillis();
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		xgcal.setDay(20);
		Object[] objects = client.invoke("getFaceReportEntitiesByPage", "tasx2", xgcal, 2);

//		Object[] objects = client.invoke("getFaceReportEntities", xgcal);
//		System.out.println(objects.length);
		for (Object obj : objects) {
			List<SapFaceReport> list = (List<SapFaceReport>) obj;
			System.out.println(list.size());
			for (SapFaceReport e : list) {
//				byte[] encodeBase64 = Base64.decodeBase64(new String(report.getImage()));
//				
//				FileOutputStream out = new FileOutputStream("/Users/daniel/Desktop/ffe6.jpeg");
//				out.write(encodeBase64);
//				out.close();
//				System.out.println("*****" + new String(report.getImage()));
				System.out.println("*****" + e.getCamera_id());
				System.out.println("*****" + e.getPerson_id());
				System.out.println("*****" + e.getImageDownloadUrl());

				System.out.println("*****" + e.getDevice_id());
				System.out.println("*****" + e.getCamera_id());
				System.out.println("*****" + e.getCamera_name());
				System.out.println("*****" + e.getGroup_id());
				System.out.println("*****" + e.getPerson_id());
				System.out.println("*****" + e.getTimestamp());
				System.out.println("*****" + e.getTrace_type());
				System.out.println("*****" + e.getRequest_id());
				System.out.println("*****" + e.getCreateTime());
			}
		}
		System.out.println((System.currentTimeMillis() - start));
	}

	public static void test3() throws Exception {
		long start = System.currentTimeMillis();
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf.createClient("http://101.230.217.197:9090/soap/facereport?wsdl");
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		xgcal.setDay(11);

		GregorianCalendar gcal1 = new GregorianCalendar();
		XMLGregorianCalendar xgcal1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal1);
		xgcal1.setDay(11);
		System.out.println(xgcal.toString());
		Object[] objects = client.invoke("getFaceReportStatistics", "7540688D852E4F99AD6D154D9E989FB7", xgcal, xgcal1);

//		Object[] objects = client.invoke("getFaceReportEntities", xgcal);
//		System.out.println(objects.length);
		for (Object obj : objects) {
			FaceReportStatistic list = (FaceReportStatistic) obj;
			System.out.println("Length" + list.getTotalClient());
			System.out.println("Length" + list.getNewClient());
			System.out.println("Length" + list.getOldClient());
		}
		System.out.println((System.currentTimeMillis() - start));
	}
}
