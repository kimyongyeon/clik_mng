package clikmng.nanet.go.kr.mdm.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.mdm.model.MdmPolicyInfoVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;

public class MdmEduManualText extends AbstractView {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
										HttpServletRequest httpservletrequest,
										HttpServletResponse response) throws Exception {
		
		MdmSearchVO 				searchVO		=	(MdmSearchVO)map.get("searchVO");
		List<MdmPolicyInfoVO>	resultList		=	(List<MdmPolicyInfoVO>)map.get("resultList");
		
		try {
			// 파일명
			String txtFile = "교육메뉴얼_메타데이터";
			StringBuilder txtFileNm = new StringBuilder();
			
			txtFileNm.append(URLEncoder.encode(txtFile, "UTF-8"));
			txtFileNm.append("(");		
			
			if(searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false)
			{
				txtFileNm.append(searchVO.getSchDt1());
			}
			if( (searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false) ||
				(searchVO.getSchDt2() != null && "".equals(searchVO.getSchDt2()) == false) )
			{
				txtFileNm.append("~");			
			}
			if(searchVO.getSchDt2() != null && "".equals(searchVO.getSchDt2()) == false)
			{
				txtFileNm.append(searchVO.getSchDt2());
			}
			
			txtFileNm.append(")");
			txtFileNm.append(".txt");
			
			response.setContentType("text/plain; charset=UTF-8");
		    response.setHeader("Content-Disposition","attachment;filename="+txtFileNm+"");
			
			StringBuffer sb = new StringBuffer();
//			sb.append("일련번호\t기관명\t게시판명\t자료유형\t제목\t작성자\t첨부파일유무\t등록일\t수집일\t삭제\t중복\t게시\t문서번호\t수집URL\t서비스URL\r\n");
			sb.append("일련번호\t기관명\t게시판명\t자료유형\t제목\t작성자\t등록일\t수집일\t삭제\t중복\t게시\t문서번호\t수집URL\t서비스URL\r\n");
			
			MdmPolicyInfoVO vo = null;
			
			for( int i = 0 ; i < resultList.size(); i++ )
			{
				vo = resultList.get(i);
				
				sb.append((i+1));
				sb.append("\t");
				sb.append(vo.getSEEDNM()!=null ? vo.getSEEDNM() : "");
				sb.append("\t");				
				sb.append(vo.getSEEDNAME()!=null ? vo.getSEEDNAME() : "");
				sb.append("\t");
				sb.append(vo.getSITENM()!=null ? vo.getSITENM() : "");
				sb.append("\t");
				sb.append(vo.getTITLE()!=null ? vo.getTITLE() : "");
				sb.append("\t");
				sb.append(vo.getWRITER()!=null ? vo.getWRITER() : "");
//				sb.append("\t");
//				if(vo.getFILECNT() > 0) {
//					String value = "";
//					if(vo.getFILESUCCCNT() > 0 ) {
//						value += "변환성공(" + vo.getFILESUCCCNT() + ")";
//					}
//					if(vo.getFILEFLRCNT() > 0 ) {
//						value += value.length() != 0 ? " / " : "";
//						value += "변환실패(" + vo.getFILEFLRCNT() + ")";
//					}
//					if((vo.getFILECNT() - vo.getFILESUCCCNT() - vo.getFILEFLRCNT()) > 0 ) {
//						value += value.length() != 0 ? " / " : "";
//						value += "변환대기(" + (vo.getFILECNT() - vo.getFILESUCCCNT() - vo.getFILEFLRCNT()) + ")";
//					}
//					sb.append(value);
//				}
				sb.append("\t");
//				sb.append(CommonUtil.formatDate(vo.getCDATE()!=null ? vo.getCDATE() : "","-"));
				sb.append(vo.getCDATE()!=null ? vo.getCDATE() : "");
				sb.append("\t");
				sb.append(CommonUtil.formatDate(vo.getREGDATE()!=null ? vo.getREGDATE() : "","-"));
				sb.append("\t");
				sb.append(vo.getCUD_CODE().equals("D") ? "삭제" : "");
				sb.append("\t");
				sb.append(vo.getDUPCNT() > 1 ? "중복(" + vo.getDUPCNT() + ")" : "");
				sb.append("\t");
				if(vo.getISVIEW() != null && vo.getISVIEW().equals("N")){
					sb.append("미게시");
				}else{
					sb.append("게시");
				}
				sb.append("\t");
				sb.append(vo.getOUTBBS_CN());
				sb.append("\t");
				sb.append(vo.getURL());
				sb.append("\t");
				sb.append("http://clik.nanet.go.kr/potal/search/searchView.do?collection=manual&DOCID="+vo.getOUTBBS_CN());
				sb.append("\r\n");
				
			}
				
			String mag = new String(sb.toString().getBytes("UTF-8"), "ISO-8859-1");
			
		    ServletOutputStream out = response.getOutputStream();
		    out.println(mag);
		    out.flush();
		    out.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
}