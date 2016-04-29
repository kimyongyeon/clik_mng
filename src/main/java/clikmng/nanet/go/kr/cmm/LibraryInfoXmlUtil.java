package clikmng.nanet.go.kr.cmm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class LibraryInfoXmlUtil {
	private static final LibraryInfoXmlUtil INSTANCE = new LibraryInfoXmlUtil();
	private static Document doc;
	private static List<Element> colElem;
	
	private LibraryInfoXmlUtil(){
		
		SAXBuilder sb = new SAXBuilder();
		String hlXMLPath = getClass().getResource("").getPath() + "../../../conf/op/code/LibraryInfoCode.xml";
		try{
			doc = sb.build(hlXMLPath);
			Element el = doc.getRootElement();
			colElem = el.getChildren("INFO");
		}catch(IOException ie){
			System.out.println("doc is not loding!!");
			colElem = new ArrayList<Element>();
		}catch(JDOMException je){
			System.out.println("DOM parsing Error!!");
			colElem = new ArrayList<Element>();
		}catch(Exception e){
			System.out.println("parsing Error!!");
			colElem = new ArrayList<Element>();
		}
	}

	public static final LibraryInfoXmlUtil getInstance() {
		return INSTANCE;
	}

	/**
	 * selectBox html생성.
	 * @param col 셀렉트 박스의 컬럼명. DB 컬럼명과 동일 
	 * @return select Box HTML
	 */
	public static String getSelect(String col, HttpServletRequest request){
		return getSelect(col, false, "", request);
	}
	
	/**
	 * selectBox html생성.
	 * @param col 셀렉트 박스의 컬럼명. DB 컬럼명과 동일
	 * @param defaultValue 전체 옵션여부. 전체옵션은 ""로 자동을 들어간다.
	 * @return select Box HTML
	 */
	public static String getSelect(String col, boolean defaultValue, HttpServletRequest request){
		return getSelect(col, defaultValue, "", request);
	}

	public static String getSelect(String col,  String className, HttpServletRequest request){
		return getSelect(col, false, className, request);
	}
	
	public static String getSelect(String col, Map map){
		return getSelect(col, false, "", map);
	}
	
	public static String getSelect(String col, boolean defaultValue, Map map){
		return getSelect(col, defaultValue, "", map);
	}

	public static String getSelect(String col,  String className, Map map){
		return getSelect(col, false, className, map);
	}
	
	/**
	 * selectBox html생성.
	 * @param col 셀렉트 박스의 컬럼명. DB 컬럼명과 동일
	 * @param defaultValue 전체 옵션여부. 전체옵션은 ""로 자동을 들어간다. 
	 * @param className 적용할 클래스 명  
	 * @return select Box HTML
	 */
	public static String getSelect(String col, boolean defaultValue, String className, HttpServletRequest request){
		Element target = null;
		String result = "";
		
		for(int i=0; i<colElem.size(); i++){
			target = colElem.get(i);
			if(target.getAttributeValue("col").equals(col)) break;
			else target = null;
		}

		String selectValue = CommonStringUtil.nullConvert((String)request.getParameter(col));
		if(target != null) result = makeHtml(target, defaultValue ,className, selectValue);
		
		else System.out.println(col + " is not exist!" );
		return result;
	}

	public static String getSelect(String col, boolean defaultValue, String className, Map map){
		Element target = null;
		String selectValue="";
		String result = "";
		
		for(int i=0; i<colElem.size(); i++){
			target = colElem.get(i);
			if(target.getAttributeValue("col").equals(col)) break;
			else target = null;
		}
		
		if(map!=null && !map.isEmpty()) selectValue = CommonStringUtil.nullConvert((String)map.get(col));
		
		if(target != null) result = makeHtml(target, defaultValue ,className, selectValue);
		
		else System.out.println(col + " is not exist!" );
		return result;
	}
	
	private static String makeHtml(Element el,boolean defaultValue, String className, String selectValue){
		if(className == null || "".equals(className)) className = "widthA";
		
		String colName = el.getAttributeValue("col");
		String colSummary = el.getAttributeValue("summary");

		List<Element> dataList = el.getChildren();
		StringBuffer sb = new StringBuffer();
		if(dataList!=null){
			//2012-08-06 권인애 : 기관종류 카테고리분류 변경에 따른 내용수정
			if(colName.equals("org_div2")){
				sb.append("\t").append("<select name=\"").append(colName).append("\" title=\"").append(colSummary).append("\" class=\"").append(className).append("\" onchange=\"setDiv2()\">").append("\n");
				sb.append("\t").append("\t").append("<option value=\"0\">선택하세요</option>").append("\n");
			}
			else{
				sb.append("\t").append("<select name=\"").append(colName).append("\" title=\"").append(colSummary).append("\" class=\"").append(className).append("\">").append("\n");
			}
			if(defaultValue)
				sb.append("\t").append("\t").append("<option value=\"\">전체</option>").append("\n");
				
			for(int i=0; i<dataList.size(); i++){
				Element data = dataList.get(i);
				String value = data.getAttributeValue("value");
				String text = data.getText();
				String selected = "";
				if(selectValue != null && selectValue.equals(value))
					selected = " selected=\"selected\" ";
				if(i !=0 && colName.equals("org_div")){
					sb.append("\t").append("\t").append("<option value=\"").append(value).append("\"").append(selected).append(" disabled=\"disabled\">").append(text).append("</option>").append("\n");
				}
				else{
					sb.append("\t").append("\t").append("<option value=\"").append(value).append("\"").append(selected).append(">").append(text).append("</option>").append("\n");
				}
			}
			sb.append("\t").append("</select>").append("\n");;
		}
		return sb.toString();
	
	}
	
	public static String getSummary(String col){
		Element target = null;
		String result = "";
		
		for(int i=0; i<colElem.size(); i++){
			target = colElem.get(i);
			if(target.getAttributeValue("col").equals(col)) break;
			else target = null;
		}
		
		if(target != null) result = target.getAttributeValue("summary"); 
			
		return result;
	}

	public static void main(String args[]){
		Map map = new HashMap();
		map.put("agree_yn", "D");
		System.out.println(LibraryInfoXmlUtil.getInstance().getSelect("agree_yn",true,"widthD",map));
	}
}
