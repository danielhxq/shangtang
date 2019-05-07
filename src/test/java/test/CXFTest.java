package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.shangtang.controller.FaceReportStatistic;
import com.shangtang.controller.SapFaceReport;

public class CXFTest {
	public static void main(String[] args) throws Exception {
		test1();

		for(String s : l) {
			new Run(s).run();
		}
//		test3();

//		ExecutorService es = Executors.newFixedThreadPool(1);
//		es.execute(null);
//		es.execute(new Run("1D56127F861347EEAFF4288646EDF3D6"));
//		es.execute(new Run("3961B830C7694206B9C47387BF03FFA9"));
//		es.execute(new Run("9B9991C3F6044AE0916067FD1A80E111"));
//		es.execute(new Run("A7ACCA31B6D944678740931B207EB6D9"));
//		es.execute(new Run("C56151F9ECE34195B4FFAFF9C998EEF1"));
//		es.execute(new Run("E081A957062C4A0F9BFCA1F8E53049C0"));
//		es.execute(new Run("E770ECAB48174E95B3DE484F49764AC1"));
	}
	
	private static List<String> ll = new ArrayList<String>();
	static {
		ll.add("FBC1947E52BC42F5B3C17B07DC76EE82");
		ll.add("0BDA236E85004A7E8BC8D0640BAFDAFC");
	}

	public static class Run {
		String group_id;

		public Run(String group_id) {
			this.group_id = group_id;
		}

		public void run() {
			try {
				// TODO Auto-generated method stub
				long start = System.currentTimeMillis();
				;
				JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
				org.apache.cxf.endpoint.Client client = dcf
						.createClient("http://101.230.217.197:9091/soap/facereport?wsdl");
				for (int i = 0; i < 30; i++) {
					XMLGregorianCalendar xgcal;

					xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
					xgcal.setYear(2019);
					xgcal.setMonth(4);
					xgcal.setDay(22);
					XMLGregorianCalendar xgcal1;
					xgcal1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
					xgcal.setYear(2019); 
					xgcal1.setMonth(4);
					xgcal1.setDay(24);
					int size = 0;// 7540688D852E4F99AD6D154D9E989FB7
					// 1D56127F861347EEAFF4288646EDF3D6
					Object[] objects = client.invoke("getFaceReportEntitiesByPage", group_id, xgcal, xgcal1, i);

					for (Object obj : objects) { 
						List<SapFaceReport> list = (List<SapFaceReport>) obj;
						size = list.size();
//						for(SapFaceReport r : list) {
//							System.out.println(r);
//						}
						System.out.println("group_id=" + group_id + ", day=" + i + ", size=" + size);

					}
					if (size < 3000) {
						break;
					}

				}
				System.out.println("group_id=" + group_id + ", time=" + (System.currentTimeMillis() - start));
			} catch (DatatypeConfigurationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void test1() throws Exception {

	}

	public static void test2() throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf.createClient("http://localhost:9090/soap/facereport?wsdl");
		long start = System.currentTimeMillis();
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		xgcal.setDay(20);
		Object[] objects = client.invoke("getFaceReportEntitiesByPage", "7540688D852E4F99AD6D154D9E989FB7", xgcal, 2);

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

		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf.createClient("http://101.230.217.197:9090/soap/facereport?wsdl");
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		xgcal.setDay(11);
		xgcal.setMonth(9);
		GregorianCalendar gcal1 = new GregorianCalendar();
		XMLGregorianCalendar xgcal1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal1);
		xgcal1.setDay(11);
		xgcal1.setMonth(12);
		System.out.println(xgcal.toString());
		long start = System.currentTimeMillis();
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
	
	private static List<String> l = new ArrayList<>();
	
	static {
		l.add("0BDA236E85004A7E8BC8D0640BAFDAFC");
		l.add("0D8A0C86E06D4806AE029ECDC78D4630");
		l.add("1D56127F861347EEAFF4288646EDF3D6");
		l.add("1F9E291BAD1F4CABBCDC0F83DC9308B3");
		l.add("21AC837DC0C240BC815772BBDEB2F765");
		l.add("21B8B29E4C054DEA9F4D869663B5508D");
		l.add("24B7D168F0934F639A84E8A3803DA274");
		l.add("27572AA302AA4BA5AA1A70F55B69A6CB");
		l.add("2DB9C352CE59481F8C6B7778109D12B8");
		l.add("2FEE9185B3DE40D683CA274634B01783");
		l.add("30B95E59E6B34F409F9AA84A8B90B1BD");
		l.add("3961B830C7694206B9C47387BF03FFA9");
		l.add("3C6524D77EAF4766A3BB0886D4D50EC9");
		l.add("4172D430DC4D4E199FDE3FDC3A6C441C");
		l.add("47074B8F7DD040F2A9D0ED3925F2405B");
		l.add("4BF6B9281D6143409A39BFF427CF5891");
		l.add("4D03933A82F343A5822A4121D68BA236");
		l.add("5767CE41CBD941F089E95CA759A51615");
		l.add("5A3F2C2EE8B24A4389DB3491A5469461");
		l.add("5AAF31634F744010A52FE2A776FA2370");
		l.add("64B00B98993C4785AD0B2F0C4C1833AC");
		l.add("68530CBAE0394A69B9715B38A0EA162B");
		l.add("712DBC72A8254066A0EA303EB6627987");
		l.add("719CD834FCB44EB18AE8E6773DD47BB8");
		l.add("7540688D852E4F99AD6D154D9E989FB7");
		l.add("7ACEFAEDB2074FD4AB263EC9AC563B7D");
		l.add("8A45EEF8F0E9431595D95E5613768178");
		l.add("8DFB31F866EE4045BA415A86DC90B06C");
		l.add("8E39C31EEADA4EF9813B8A5980BD4F51");
		l.add("94C32C949AB3482EAC1FEB0AE626A971");
		l.add("95645DE8A92C4F4184F7C87742644D9A");
		l.add("9B9991C3F6044AE0916067FD1A80E111");
		l.add("9C1516B8C661452A8DA5E0D4D366DDC5");
		l.add("9FE7E27FC96A41D7B2DDDB2F8ECA553A");
		l.add("A7ACCA31B6D944678740931B207EB6D9");
		l.add("B23ECC5DF5564101A2E5FB64546D7903");
		l.add("B4993927622046D881A4A8C275B0201F");
		l.add("B8FA6B75FC89424183A72B23DBE4255E");
		l.add("B95D82B5DCFB4E66BDDF021DDE27F57B");
		l.add("BFDB54C46789460CA91A04500BE493B5");
		l.add("C05460B6B8B34F14BD24F6C5999C8D5B");
		l.add("C37143C7B1E54B2FB27C4B157B16DF4C");
		l.add("C3F7A436F5FF4AA3A9A3C992ACA3C85A");
		l.add("C56151F9ECE34195B4FFAFF9C998EEF1");
		l.add("D3EB78643EDC4C439B66F369466F05AD");
		l.add("D94FB31D71964AC382F8D74661F2788B");
		l.add("E081A957062C4A0F9BFCA1F8E53049C0");
		l.add("E770ECAB48174E95B3DE484F49764AC1");
		l.add("EB731C02AA7844A387654C9DC5CB6569");
		l.add("FBC1947E52BC42F5B3C17B07DC76EE82");
	}
}
