package test;

import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.shangtang.controller.SapFaceReport;

public class CXFTest {
	public static void main(String[] args) throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf.createClient("http://101.230.217.197:9090/soap/facereport?wsdl");
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		Object[] objects = client.invoke("getFaceReportEntities", xgcal);
		System.out.println(objects.length);
		for (Object obj : objects) {
			List<SapFaceReport> list = (List<SapFaceReport>) obj;
			for (SapFaceReport report : list) {
//				byte[] encodeBase64 = Base64.decodeBase64(new String(report.getImage()));
//				
//				FileOutputStream out = new FileOutputStream("/Users/daniel/Desktop/ffe6.jpeg");
//				out.write(encodeBase64);
//				out.close();
//				System.out.println("*****" + new String(report.getImage()));
				System.out.println("*****" + report.getCamera_id());
				System.out.println("*****" + report.getPerson_id());
				System.out.println("*****" + report.getImageDownloadUrl());
			}
		}

	}
}
