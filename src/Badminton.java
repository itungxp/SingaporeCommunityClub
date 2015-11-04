
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class Badminton {

	private static final String DATE = "25-11-2014";
	private static final String VIEW_MODE = "2"; // 1 - day, 2 - week
	private static final String RESULT_PER_PAGE = "500";
	private static final String CURRENT_PAGE = "1";
	private static final Integer TIMEOUT = 100000;
	private static final String[] froms = {};// {"400349"}; // "550258","410116", 
	private static final List<String> skip = new ArrayList<String>(){
		private static final long serialVersionUID = 4209895225705571540L;
		{
			add("AYER RAJAH CC");
			add("CANBERRA THE JELUTUNG CC");
			add("WOODLANDS CC");
			add("ZHENGHUA CC");
			add("WEST COAST CC"); 
			add("BUKIT PANJANG CC"); 
			add("TAMAN JURONG CC");
			add("JURONG SPRING CC");
			add("SENJA-CASHEW CC");
			add("WEST COAST CC");
			add("FUCHUN CC");
			add("PASIR RIS ELIAS CC");
			add("BOON LAY CC");
			add("MARSILING CC");
			add("SEMBAWANG CC");
			add("CANBERRA CC");
			add("HONG KAH NORTH CC");
			add("ACE THE PLACE CC");
			add("NANYANG CC");
			add("LENG KEE CC");
			add("JURONG GREEN CC");
			add("KEBUN BARU CC");
		}
	};
	
	private static final String ONE_PA_URL = "https://one.pa.gov.sg/CRMSPortal/CRMSPortal.portal?_nfpb=true&_st=&_windowLabel=CRMSPortal_1&_urlType=action&wlpCRMSPortal_1_action=RBMFacilityPublicBooking&_pageLabel=CRMSPortal_page_1";
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		System.out.println("======================== Badminton Course Avaiable ================");
		
		System.setProperty("https.proxyHost", "proxy.inc");
		System.setProperty("https.proxyPort", "8080");
	
		Document doc = Jsoup
			.connect(ONE_PA_URL)
			.header("Content-Type", "application/x-www-form-urlencoded")
			.header("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
			.header("Accept-Encoding", "gzip,deflate,sdch")
			.header("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6")
			.header("Connection", "keep-alive")
			.header("Content-Length", "2632")
			.header("Content-Type", "application/x-www-form-urlencoded")
			.header("Host", "one.pa.gov.sg")
			.header("Origin", "https://one.pa.gov.sg")
			.header("Referer", ONE_PA_URL)
			.header("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.69 Safari/537.36")
			.timeout(TIMEOUT)		
	
			.data("searchDate", DATE)
			.data("searchResultPerPage", RESULT_PER_PAGE).data("pageNo", CURRENT_PAGE)
			.data("searchResource", "9").data("indSearchBy", "1")
			.data("viewMode", VIEW_MODE).data("task", "")
			.data("idProduct", "").data("idProfile", "")
			.data("unitNum", "").data("resourceDesc", "")
			.data("entityName", "").data("indBookingBasis", "")
			.data("indPublishPublic", "").data("txVenue", "")
			.data("indPayCounter", "").data("idInternalBu", "")
			.data("cdResourceType", "").data("indIndemnityReq", "")
			.data("CWT", "").data("CWT", "").data("searchLocation", "")
			.data("searchPostalCode", "").data("btnSearch.x", "32")
			.data("btnSearch.y", "11").data("CWT", "")
			.data("cdNationality", "SG")
			.data("identificationType", "001")
			.data("NRIC", "")
			.data("txRulesAndReg", "")
			.data("_chkAccept", "")
			.post();
		
		for (Element course : doc.getElementsByAttributeValue("name", "btnCheckVacancy")) {
			if(course.parent().parent().child(5).text().equals("Available Online")){
				StringTokenizer tokenizer = new StringTokenizer(course.attr("onclick"), "'");
				List<String> arrs = new ArrayList<String>(); 
				while (tokenizer.hasMoreElements()) {
					tokenizer.nextToken();
					if(tokenizer.hasMoreElements()){
						arrs.add(tokenizer.nextToken());
					}
				}
				// System.out.println(arrs);
				if(skip.contains(arrs.get(4))){
					continue;
				}
				
				try{
					Document subDoc = Jsoup
							.connect(ONE_PA_URL)
							.header("Content-Type", "application/x-www-form-urlencoded")
							.header("Accept",
									"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
									.header("Accept-Encoding", "gzip,deflate,sdch")
									.header("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6")
									.header("Connection", "keep-alive")
//					.header("Content-Length", "441")
									.header("Content-Type", "application/x-www-form-urlencoded")
									.header("Host", "one.pa.gov.sg")
									.header("Origin", "https://one.pa.gov.sg")
									.header("Referer", ONE_PA_URL)
									.header("User-Agent",
											"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.69 Safari/537.36")
											.timeout(TIMEOUT)
											.data("task", "")
											.data("searchResultPerPage", RESULT_PER_PAGE).data("pageNo", CURRENT_PAGE)
											.data("searchResource", "9").data("indSearchBy", "1")
											.data("viewMode", VIEW_MODE)
											.data("CWT", "").data("CWT", "").data("searchLocation", "")
											.data("searchPostalCode", "")
											.data("btnCheckVacancy.x", "38")
											.data("btnCheckVacancy.y", "13")
											.data("CWT", "")
											.data("cdNationality", "SG")
											.data("identificationType", "001")
											.data("NRIC", "")
											.data("txRulesAndReg", "")
											.data("_chkAccept", "")
											.data("searchDate", DATE)
											.data("idProduct", arrs.get(0))
											.data("idProfile", arrs.get(1))
											.data("indBookingBasis", arrs.get(2))
											.data("resourceDesc", arrs.get(3))
											.data("entityName", arrs.get(4))
											.data("txVenue", arrs.get(4))
											.data("indPayCounter", arrs.get(5))
											.data("indPublishPublic", arrs.get(6))
											.data("idInternalBu", arrs.get(7))
											.data("unitNum", arrs.get(8))
											.data("cdResourceType", arrs.get(9))
											.data("indIndemnityReq", arrs.get(10))
											.post();
					
					Elements elements =  subDoc.getElementsByAttributeValueStarting("name", "lstAllRbmBooking");
					if (elements != null && !elements.isEmpty()) {
						boolean isWeekendAvaiable = false;
						Map<String, List<String>> map = new HashMap<>();
						for (Element slot : elements) {
							Node tr = slot.parent().parent();
							if (!slot.hasAttr("disabled")) {
								int index = 0;
								for (Node td : tr.childNodes()) {
									if (td.toString().equals(slot.parent().toString())) {
										String day = VIEW_MODE == "1" ? DATE : tr.childNode(index - 2).toString();
										String time = slot.parent().parent().childNode(1).childNode(0).toString();
//										System.out.println(day + " :: " + time);
										if(day.contains("sunday") || day.contains("saturday")){
											isWeekendAvaiable = true;
										}
										
										List<String> times = map.get(day) == null ? new ArrayList<String>() : map.get(day);
										if(times.isEmpty()){
											times.add(time);
										}
										else {
											// To get the 2 hours period only
											String[] timePeriod = time.split("-");
											if(Integer.valueOf(timePeriod[1].substring(0, 2)) - Integer.valueOf(timePeriod[0].substring(0, 2)) < 2){
												for(String _time : times){
													String[] _timePeriod = _time.split("-");
													if(timePeriod[0].trim().equals(_timePeriod[1].trim())){
														times.add(time);
														break;
													}
												}
											}
											else {
												times.add(time);
											}
										}
										
										map.put(day, times);
										break;
									}
									index++;
								}
							}
						}
						
						if(isWeekendAvaiable){
						}
						
						for(String day : map.keySet()){
							if(day.contains("sunday") || day.contains("saturday")){
								if(map.get(day).size() == 1){
									String[] timePeriod = map.get(day).get(0).split("-");
									if(Integer.valueOf(timePeriod[1].substring(0, 2)) - Integer.valueOf(timePeriod[0].substring(0, 2)) < 2){
										continue;
									}
								}
								
								System.out.println("\n\n"+day+"=========== " + arrs.get(4) + " - Course : "+ arrs.get(8) +" ==========================");
								for(String time : map.get(day)){
									System.out.print(time + " , ");
								}
								System.out.println();
//								System.out.println("https://www.google.com.sg/maps/place/" + arrs.get(4).replace(" ", "+"));
								System.out.println("http://gothere.sg/maps#q:" + arrs.get(4).replace(" ", "%20"));
								// Distance
								try{
									for(String from : froms){
										String googleAPI = "http://maps.googleapis.com/maps/api/distancematrix/json?origins=Singapore+"+from+"&destinations="+arrs.get(4).replace(" CC", "").replaceAll(" ", "+")+"+Singapore&sensor=false&mode=walking";
										URL u = new URL(googleAPI);
										HttpURLConnection c = (HttpURLConnection) u.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.starhubsg.sh.inc", 8080)));
										c.setConnectTimeout(TIMEOUT);
										c.setReadTimeout(TIMEOUT);
										JsonReader jr = Json.createReader(c.getInputStream());
										JsonObject obj = jr.readObject();
										for(JsonObject result : obj.getJsonArray("rows").getValuesAs(JsonObject.class)){
											for(JsonObject element : result.getJsonArray("elements").getValuesAs(JsonObject.class)){
												System.out.println("============================ From "+from+" to "+arrs.get(4)+": "+element.getJsonObject("distance").getString("text"));
												break;
											}
										}
									}
								}
								catch(Exception e){e.printStackTrace();}
							}
						}
					}
				}
				catch(Exception e){
					//System.err.println("\n\n Exception With : " + arrs.get(4) + " - Course : "+ arrs.get(8) +" ===========================");
					//System.err.println(arrs);
				}
			}
			
		} 

	}

}
